
/*******Package*******/

package webserver;


import java.io.IOException;

/*******Imports*******/

import java.net.*;

import webserver.webexception.*;

public class ConnectionManager implements Runnable{
	
	/*******Constants*******/
	public final static long requestTimeoutMillis = 100000; 
	public final static int minimumHTTPByteLength = 16;
	public final static String defaultHTTPVersion = "HTTP/1.1";
	
	//Brian-RP
	public final static String indexAddress = "/home/pi/Documents/Whatcha-Watchin/resources/webpages/index.html";
	
	//Brian-LT
	//public final static String indexAddress = "D:/Documents/Projects/Watcha-Watchin/Whatcha-Watchin/resources/webpages/index.html";
	
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
		
		//Declare variables
		
		HTTPRequest msgIn;				//The message read from the client socket
		HTTPResponse msgOut;			//The message to form and send to the client socket
		RequestType requestType;		//The type of the request - tells us what to send back
		
		try {
			
			//Record current system time, to test for timeout
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
				
				//View connection info
				System.out.println("	Client IP: " + this.getClientSocket().getLocalAddress().toString());
				System.out.println("	First line of request: " + msgIn.getHeaders()[0]);
				
				//Identify request type
				requestType = ServerTools.parseRequestType(msgIn);
				System.out.println(requestType.toString());
				
				//Respond to request
				switch(requestType){
				
				//Send index.html to the client socket
				case INDEX_REQ:
					msgOut = ServerTools.formHTMLResponse(System.getProperty("user.dir") + "/resources/webpages/index.html");
					break;
				
				//Send index.html to the client socket
				case URL_REQ:
					System.out.println(" URL requested: " + msgIn.getUrl());
					msgOut = ServerTools.formHTMLResponse(System.getProperty("user.dir") + "/resources/webpages/" + msgIn.getUrl());
					break;
				
				//Valid HTTP message, but unknown URL
				case UNKNOWN_URL_REQ:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 404, "Not Found");
					break;
				
				//Valid HTTP message, unknown problem
				case OTHER_REQ:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, "Unknown error");
					break;
				
				default:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, "Unknown error");
					break;
				}
			}
			
			//Errors from receiving the message
			
			//Message was too short
			else if(getClientSocket().getInputStream().available() < minimumHTTPByteLength) {
				throw new MalformedHTTPRequestException("HTTP request method did not meet minimum length");
			}
			
			//Else, request must have timed out
			else {
				throw new RequestTimeoutException("HTTP request timed out.");
			}
		}
		
		
		
		//Handle errors from receiving or reading the message
		catch(WebException e){
			msgOut = ExceptionHandler.handleWebException(e);
		}
		catch(Exception e){
			msgOut = ExceptionHandler.handleException(e);
		}
		
		
		
		
		//Send the message to the client socket
		try {
			//Send message 
			ServerTools.sendHTTPMessage(msgOut, getClientSocket());
			
			//Close client socket
			getClientSocket().close();
			
			//
			System.out.println("Succesful response");
			System.out.println("	Error?: " + msgOut.isError());
			System.out.println("	First line of response: " + msgOut.getHeaders()[0]);
		}
		catch(IOException e){
			System.out.println("Could not close socket");
			e.printStackTrace();
		}
		catch(WebException e){
			System.out.println("Could not respond to client.");
			e.printStackTrace();
		}
	}
}
