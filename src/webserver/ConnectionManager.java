
/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import webserver.webexception.*;

public class ConnectionManager implements Runnable{
	
	/*******Constants*******/
	public final static long requestTimeoutMillis = 100000; 
	public final static int minimumHTTPByteLength = 16;
	
	//Brian-RP
	public final static String webPageAddress = "/home/pi/Documents/Whatcha-Watchin/resources/webpages/index.html";
	
	//Brian-LT
	//public final static String webPageAddress = "D:/Documents/Projects/Watcha-Watchin/Whatcha-Watchin/resources/webpages/index.html";
	
	/*******Member Fields*******/
	
	private Socket clientSocket;
	
	
	/*******Get/Set Methods*******/
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	
	/*******Constructors*******/
	public ConnectionManager(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	
	/*******Member Methods*******/
	
	/**
	 * Method called when ConnectionManager thread created to handle a new connection
	 * (after authentication). It's job is basically to ensure that the client browser
	 * successfully downloads the applet.
	 */
	public void run(){
		
		HTTPRequest msgIn;
		HTTPResponse msgOut;
		RequestType requestType;
		
		try {
			
			long startTime = System.currentTimeMillis();
			
			//Wait until either request timeout, or there is data available on the client socket
			while(		System.currentTimeMillis() - startTime < ConnectionManager.requestTimeoutMillis 
						&& getClientSocket().getInputStream().available() <= 0) {
				System.out.println("Expected bytes available: " + getClientSocket().getInputStream().available());
				Thread.sleep(100);
			}
			
			//If data available, read HTTPMessage from socket
			if(getClientSocket().getInputStream().available() >= ConnectionManager.minimumHTTPByteLength){
				
				//Attempt to read incoming HTTP message
				msgIn = ServerTools.parseHTTPRequest(this.getClientSocket());
				
				
				//View parsed message
				System.out.println("Succesful parse");
				
				System.out.println(msgIn.toString());
				
				//Identify request type
				requestType = ServerTools.parseRequestType(msgIn);
				
				//Respond to request
				switch(requestType){
				
				case INDEX_REQ:
					msgOut = ServerTools.formHTMLResponse(webPageAddress);
					System.out.println("Message to send:");
					System.out.println(msgOut.toString());
					ServerTools.sendHTTPMessage(msgOut, getClientSocket());
					break;
				
				case OTHER_REQ:
					break;
				
				default:
					break;
									
				}
				
				//Close client socket
				getClientSocket().close();
				
			}
			
			//Errors from reading the message
			
			//Message was too short
			else if(getClientSocket().getInputStream().available() < minimumHTTPByteLength) {
				throw new MalformedHTTPRequestException("HTTP request method did not meet minimum length");
			}
			
			//Else, request must have timed out
			else {
				throw new RequestTimeoutException("HTTP request timed out.");
			}
		}
		
		
//		catch(RequestTimeoutException e){		//Initial request timed out
//			
//		}
//		catch(WebException e){					//Unrecognized WebException
//			
//		}
		catch(Exception e){						//Unrecognized Exception
			e.printStackTrace();
		}
	}
	
	
	
}
