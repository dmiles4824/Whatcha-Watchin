
/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import webserver.webexception.*;

public class ConnectionManager implements Runnable{
	
	/*******Constants*******/
	public final long requestTimeoutMillis = 10000; 
	public final int minimumHTTPByteLength = 16;
	
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
		
		HTTPMessage msg;
		
		
		try {
			
			long startTime = System.currentTimeMillis();
			
			//Wait until either request timeout, or there is data available on the client socket
			while(		System.currentTimeMillis() - startTime < requestTimeoutMillis 
						&& getClientSocket().getInputStream().available() < minimumHTTPByteLength) {
				Thread.sleep(100);
			}
			
			//If data available, read HTTPMessage from socket
			if(getClientSocket().getInputStream().available() >= minimumHTTPByteLength){
				msg = ServerTools.parseHTTPMessage(clientSocket);
			}
			
			//Otherwise, request must have timed out
			else {
				throw new RequestTimeoutException("Request timed out.");
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
