
/*******Package*******/

package webserver;


/*******Imports*******/

import java.io.*;
import java.net.*;

public class ConnectionManager {
	
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
			msg = ServerTools.parseHTTPMessage()
		}
		catch(Exception e){					//Unrecognized exception
			
		}
	}
	
	
	
}
