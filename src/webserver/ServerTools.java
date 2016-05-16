/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import webserver.webexception.*;

public class ServerTools {
	
	/*******Constants*******/
	public final static int defaultHeaderBufferSize = 1024;		//Default header buffer size, in bytes
	public final static int defaultBodyBufferSize = 1024;		//Default body buffer size, in bytes
	
	
	public static HTTPMessage parseHTTPMessage(Socket socket, int headerBufferSize, int bodyBufferSize) throws Exception{
		
		System.out.println("Attempting to parse");
		
		//Declare all parts of message to be parsed
		InetAddress address;
		int port;
		String[] headers;
		byte[] body = new byte[1024];
		String command;
		int contentLength;
		boolean isError;
		HTTPMessage message;
		
		
		System.out.println("Finding IP address");
		//Find IP address of client
		address = socket.getLocalAddress();
		
		System.out.println("Finding Port");
		//Find port the client is using
		port = socket.getPort();
		
		System.out.println("Extracting headers");
		//Extract headers
		headers = ParseTools.extractHTTPHeaders(socket.getInputStream(), 1024);
		
		System.out.println("Parsing command");
		//Find the command type of the message
		command = ParseTools.findCommand(headers);
		
		System.out.println("Parsing content-length");
		//Determine Content-Length
		contentLength = ParseTools.findContentLength(headers, command);
		
		System.out.println("Reading body");
		//Read body of message
		body = ParseTools.readBody(body, socket.getInputStream(), contentLength);
		
		System.out.println("Error?");
		//Determine if error
		isError = false;
		
		System.out.println("Creating HTTP message");
		//Create HTTPMessage
		message = new HTTPMessage(address, port, headers, body, command, contentLength, isError);
		
		return message;
	}
	
	public static HTTPMessage parseHTTPMessage(Socket socket) throws Exception{
		
		return ServerTools.parseHTTPMessage(socket, ServerTools.defaultHeaderBufferSize, ServerTools.defaultBodyBufferSize);
		
	}
}
