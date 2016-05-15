/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import webserver.webexception.*;

public class ServerTools {
	
	public static HTTPMessage parseHTTPMessage(Socket socket) throws WebException{
		
		System.out.println("Attempting to parse");
		
		//Declare all parts of message to be parsed
		InetAddress address;
		int port;
		String[] headers;
		byte[] message;
		boolean isError;
		
		//Find IP address of client
		address = socket.getLocalAddress();
		
		//Find port the client is using
		port = socket.getPort();
		
		//Extract headers
		headers = ParseTools.extractHTTPHeaders(socket.getInputStream());
		
		return null;
	}
	
	
}
