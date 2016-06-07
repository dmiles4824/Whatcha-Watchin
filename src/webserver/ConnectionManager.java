
/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import webserver.webexception.*;
import java.io.IOException;


public class ConnectionManager implements Runnable{
	
	/*******Constants*******/
	//s 
	public final static long requestTimeoutMillis = 10000; 
	public final static int minimumHTTPByteLength = 16;
	public final static String defaultHTTPVersion = "HTTP/1.1";
	
	//Brian-RP
	public final static String indexAddress = "/home/pi/Documents/Whatcha-Watchin/resources/webpages/index.html";
	
	//Brian-LT
	//public final static String indexAddress = "D:/Documents/Projects/Watcha-Watchin/Whatcha-Watchin/resources/webpages/index.html";
	//e
	
	/*******Member Fields*******/
	
	//Socket connected to requesting person
	private Socket clientSocket;
	
	
	/*******Get/Set Methods*******/
	
	//s
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	//e
	
	/*******Constructors*******/
	
	//s
	/**
	 * Single argument constructor.
	 * @param clientSocket Java stream socket to requesting process
	 */
	public ConnectionManager(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	//e
	
	/*******Member Methods*******/
	
	/**
	 * Method called when ConnectionManager thread created to handle a new connection
	 * (after authentication). This method should ensure that a request is received,
	 * understood, and fully responded to. It manages the connection from birth through
	 * death
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
			//s
			while(		
						//Determines whether the elapsed time exceeds a preset constant
						System.currentTimeMillis() - startTime < ConnectionManager.requestTimeoutMillis 
						
						//Checks if number of available bytes exceeds minimum to start parsing an HTTP message, 
						//because we have to get at least Content-Length to know if we need to keep waiting for a body
						&& getClientSocket().getInputStream().available() <= 0) {
				Thread.sleep(100);
			}
			//e
			
			//If data available, read HTTPMessage from socket
			//s
			if(getClientSocket().getInputStream().available() >= ConnectionManager.minimumHTTPByteLength){
				
				//Attempt to read incoming HTTP message
				msgIn = ServerTools.parseHTTPRequest(this.getClientSocket());
				
				//View connection info
				
				//Identify request type
				requestType = ServerTools.parseRequestType(msgIn);
				
				//Respond to request. Switch on RequestType
				switch(requestType){
				
				//Send index.html to the client socket
				case INDEX_REQ:
					msgOut = ServerTools.formResponse(System.getProperty("user.dir") + "/resources/webpages/index.html", requestType.getTextEncoding());
					break;
				
				//Send index.html to the client socket
				case URL_REQ:
					msgOut = ServerTools.handleHTMLRequest(msgIn, requestType.getTextEncoding());
					break;
				
				//Request is from a client Javascript instance
				case JS_REQ:
					msgOut = ServerTools.handleJSRequest(msgIn, requestType.getTextEncoding());
					break;
					
				//Valid HTTP message, but unknown URL
				case UNKNOWN_URL_REQ:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 404, "Not Found");
					break;
				
				//Valid HTTP message, unknown problem
				case OTHER_REQ:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, "Unknown error");
					break;
				
				//Who knows
				default:
					msgOut = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, "Unknown error");
					break;
				}
			}
			//e
			
			//Else, request must have timed out
			//s
			else {
				throw new RequestTimeoutException("HTTP request timed out.");
			}
			//e
		}
		
		
		
		//Handle errors from receiving or reading the message
		//s
		catch(WebException e){
			msgOut = ExceptionHandler.handleWebException(e);
		}
		catch(Exception e){
			msgOut = ExceptionHandler.handleException(e);
		}
		//e
		
		
		//Send the message to the client socket
		//s
		try {
			//Send message 
			ServerTools.sendHTTPMessage(msgOut, getClientSocket());
			
			//Close client socket
			getClientSocket().close();
			
			//View info

		}
		
		//These errors can't be resolved with sending a msg to client, because
		//they are the errors encountered when trying to send a message back
		//Just print to console, and figure it out
		
		//Attempted to close socket, found error
		catch(IOException e){
			System.out.println("Could not close socket");
			e.printStackTrace();
		}
		
		//Clear all on error sending response
		catch(WebException e){
			System.out.println("Could not respond to client.");
			e.printStackTrace();
		}
		//e
	}
}
