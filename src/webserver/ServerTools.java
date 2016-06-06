/*******Package*******/

package webserver;

/*******Imports*******/
import java.io.IOException;
import java.io.InputStream;

import java.net.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import webserver.js.JSRequest;
import webserver.js.JSResponse;
import webserver.webexception.HTMLReadException;
import webserver.webexception.InputStreamUnavailableException;
import webserver.webexception.ResponseIOException;
import webserver.webexception.WebException;
import webserver.webexception.jsexception.JSException;
import webserver.webexception.jsexception.UnderlyingErrorException;

/**
 * The primary helper class for the java server. ConnectionManager's job is to take care
 * of a connection from start to finish. To do that, there are discrete tasks that must
 * be accomplished, such as parsing the request into a request object, deciding what type
 * of request it is, forming a response, sending the response, and so on. This class 
 * primarily provides static helper methods to accomplish those tasks
 * @author Brian Clark
 * 
 */
public class ServerTools {
	
	/*******Constants*******/
	public final static int defaultHeaderBufferSize = 1024;		//Default header buffer size, in bytes
	public final static int defaultBodyBufferSize = 1024;		//Default body buffer size, in bytes
	
	/**
	 * This method extracts an HTTPRequest object out of a Socket. Ideally, the socket
	 * has data waiting. If it does not, this method will simply wait for a timeout period
	 * and then throw a RequestTimeoutException if no data becomes available. 
	 * 
	 * If the size of the headers or body exceeds the given size of the respective buffer,
	 * the method will throw the appropriate WebException.
	 * 
	 * This method makes liberal use of the helper methods provided by the ParseTools class to do
	 * the actual HTTP parsing.
	 * 
	 * This will throw an InputStreamUnavailableException if it can't extract the InputStream 
	 * from the socket it is given. It will also throw any of the other WebExceptions thrown up
	 * to it from one of the ParseTools parsing methods.
	 * 
	 * 
	 * @param socket Socket to extract an HTTPRequest from.
	 * @param headerBufferSize size of byte buffer to attempt to read headers into
	 * @param bodyBufferSize size of byte buffer to attempt to read body into
	 * @return a fully formed HTTPRequest method representing the request from the client on the other end of the socket
	 * @throws WebException see description
	 */
	public static HTTPRequest parseHTTPRequest(Socket socket, int headerBufferSize, int bodyBufferSize) throws WebException{
		
		//Declare all parts of message to be parsed
		String[] headers;
		byte[] body = new byte[bodyBufferSize];
		byte[] headerBuffer = new byte[headerBufferSize];
		String command;
		int contentLength;
		String url;
		boolean isError;
		String version;
		HTTPRequest request;
		
		//Extract data stream
		InputStream is;
		try {
			is = socket.getInputStream();
		}
		catch(IOException e){
			throw new InputStreamUnavailableException("Could not extract input stream from socket");
		}
			
		//Extract headers
		headers = ParseTools.extractHTTPHeaders(is, headerBuffer);
		
		//Find the command type of the message
		command = ParseTools.findCommand(headers);
		
		//Find the requested URL
		url = ParseTools.parseURL(headers[0]);
		
		//Determine Content-Length
		contentLength = ParseTools.findContentLength(headers, command);
		
		//Read body of message
		body = ParseTools.trimByteArray(ParseTools.readBody(body, is, contentLength), contentLength);
		
		//Determine if error
		isError = false;
		
		//Determine version
		version = ParseTools.parseVersion(headers[0]);
		
		//Create HTTPMessage
		request = new HTTPRequest(headers, body, command, contentLength, url, isError, version);
		
		return request;
	}
	
	/**
	 * Same as parseHTTPRequest(Socket, int, int), but uses the default values for the two buffers.
	 * 
	 * @see parseHTTPRequest(Socket, int, int)
	 * 
	 * @param socket the socket to extract an HTTPRequest from
	 * @return a fully formed HTTPRequest
	 * @throws Exception
	 */
	public static HTTPRequest parseHTTPRequest(Socket socket) throws WebException{
		
		//Send the default values for the header and body buffer sizes
		return ServerTools.parseHTTPRequest(socket, ServerTools.defaultHeaderBufferSize, ServerTools.defaultBodyBufferSize);
		
	}
	
	/**
	 * Forms a valid HTTPResponse method containing the contents of the file at the given
	 * location. The file will be read as bytes. 
	 * 
	 * By default, this method is creating an "OK" response. Thus, the HTTP code will be 
	 * 200, and the status will be "OK", and the isError field will be false. A connection
	 * header will be inserted instructing the recipient that the connection is being closed.
	 * 
	 * The following headers are automatically included:
	 * 
	 * HTTP/1.1 200 OK\r\n
	 * Date: yyyy.MM.dd.HH.mm.ss\r\n
	 * Content-Type: whatever\r\n
	 * Connection: close\r\n
	 * \r\n
	 * 
	 * The HTTPResponse will, of course, not be addressed in any way. It just contains the 
	 * data and format to be sent. To actually deliver, it must be fed into a socket.
	 * 
	 * @param messageLocation a String detailing the file location of the file to be sent in bytes
	 * @param contentType the encoding of the message ("text/plain", "text/javascript", "text/html", etc.)
	 * @return a valid HTTPResponse object, ready to be sent
	 * @throws HTMLReadException unable to read file 
	 */
	public static HTTPResponse formResponse(String messageLocation, String contentType) throws HTMLReadException{
		
		//Read the file into a byte array
		byte[] body = ParseTools.readBytesFromFile(Paths.get(messageLocation));
		
		//Send that byte array to formResponse(byte[], String)
		return formResponse(body, contentType);
		
	}
	
	/**
	 * Forms a valid HTTPResponse method containing the given byte array.
	 * 
	 * By default, this method is creating an "OK" response. Thus, the HTTP code will be 
	 * 200, and the status will be "OK", and the isError field will be false. A connection
	 * header will be inserted instructing the recipient that the connection is being closed.
	 * 
	 * The following headers are automatically included:
	 * 
	 * HTTP/1.1 200 OK\r\n
	 * Date: yyyy.MM.dd.HH.mm.ss\r\n
	 * Content-Type: whatever\r\n
	 * Connection: close\r\n
	 * \r\n
	 * 
	 * The HTTPResponse will, of course, not be addressed in any way. It just contains the 
	 * data and format to be sent. To actually deliver, it must be fed into a socket.
	 * 
	 * @param message a byte array holding the desired message
	 * @param contentType the encoding of the message ("text/plain", "text/javascript", "text/html", etc.)
	 * @return a valid HTTPResponse object, ready to be sent
	 */
	public static HTTPResponse formResponse(byte[] message, String contentTypeString){
		
		//Body retrieval
		byte[] body = message;
		
		//contentLength
		int contentL = body.length;
		
		//Repsonse code
		int code = 200;
		
		//Response status
		String status = "OK";
		
		//Response HTTP version
		String version = "HTTP/1.1";
		
		//Response is error?
		boolean isError = false;
		
		//Form all String headers
		String statusLine = version + " " + code + " "  + status + "\r\n";
		String date = "Date: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + "\r\n";
		String contentType = "Content-Type: " + contentTypeString + "\r\n";
		String contentLength = "Content-Length: " + contentL + "\r\n";
		String connection = "Connection: close\r\n";
		String end = "\r\n";
		
		//Add each header to an array
		ArrayList<String> headerList = new ArrayList<String>();
		
		headerList.add(statusLine);
		headerList.add(date);
		headerList.add(contentType);
		headerList.add(contentLength);
		headerList.add(connection);
		headerList.add(end);
		
		String[] headers = headerList.toArray(new String[0]);
		
		//form HTTPResponse object
		HTTPResponse response = new HTTPResponse(headers, body, status, contentL, code, isError, version);
		
		return response;
	}

	/**
	 * Method to actually send an HTTPMessage. This method simply acts as a data funnel:
	 * it shoves the given HTTPMessage into the given socket.
	 * 
	 * @param msg the HTTPMessage to be sent
	 * @param sock the socket to send it through
	 * @throws ResponseIOException if there is an error retrieving or sending through the socket's OutputStream
	 */
	public static void sendHTTPMessage(HTTPMessage msg, Socket sock) throws ResponseIOException{
		
		//Combine header and body into single byte array
		byte[] allBytes = ParseTools.combineByteArrays(msg.getHeaderBytes(), msg.getMessage());
		
		try {
			
			//Write combined message to the socket
			sock.getOutputStream().write(allBytes);
		}
		
		//Error retrieving or sending through socket OutputStream
		catch(IOException e){
			throw new ResponseIOException("IO error writing back to client");
		}
		
	}
	
	/**
	 * Determines the request type of an HTTP message. Examples of request types would be if 
	 * a given request is attempting to retrieve one of the site pages, or the HTTP message 
	 * is encapsulating a request from a javascript client. If the method can't understand the 
	 * purpose of the request, it will label it OTHER_REQ.
	 * 
	 * @param msg the HTTPRequest to be parsed
	 * @return a RequestType specifying the purpose of the request
	 */
	public static RequestType parseRequestType(HTTPRequest msg) {
		
		RequestType requestType = RequestType.OTHER_REQ;
		
		//A blank request should return the index
		if(msg.getCommand().equalsIgnoreCase("GET") && msg.getUrl().equalsIgnoreCase("/")){
			requestType = RequestType.INDEX_REQ;
		}
		
		//The request is searching for a page
		else if(msg.getCommand().equalsIgnoreCase("GET")){
			requestType = RequestType.URL_REQ;
		}
		
		//A javascript-sourced client request
		else if(msg.getCommand().equalsIgnoreCase("POST")){
			requestType = RequestType.JS_REQ;
		}
		
		return requestType;
	}

	/**
	 * Method to handle an HTML/other page request.
	 * 
	 * An HTTPRequest asking for a page can be sent to this method. This method will handle
	 * forming the appropriate HTTPResponse. The file location is relative to where the 
	 * main class was run from, so watch out. Also, it will only look in the /resources/webpages/
	 * folder for the given files. This is taken as the root of the web files.
	 * 
	 * @param request the HTTP request to be handled
	 * @param textEncoding the desired encoding of the response ("text/plain", "text/html", "text/javascript", etc...)
	 * @return a fully formed HTTPResponse that responds appropriately to the given request 
	 * @throws HTMLReadException
	 */
	public static HTTPResponse handleHTMLRequest(HTTPRequest request, String textEncoding) throws HTMLReadException{
		
		HTTPResponse response;
		
		//Create the String URL for the desired file. Assumes that all page requests are rooted at Whatcha-Watching/resources/webpages/
		//and that the main class was run from the main Whatcha-Watchin directory
		String messageLocation = System.getProperty("user.dir") + "/resources/webpages/" + request.getUrl();
		
		//Actually form the appropriate response
		response = ServerTools.formResponse(messageLocation, textEncoding);
		
		return response;
	}

	/**
	 * Method to fully handle an incoming javascript-sourced request, by creating an HTTPResponse 
	 * encapsulating a javascript response.
	 * 
	 * If an HTTPRequest has been judged to be a request coming from a client side
	 * javacript application, this method will handle all aspects of responding to that
	 * request, leading up to the formation of the appropriate HTTPResponse.
	 * 
	 * This HTTPResponse will have as its body the appropriate js response object.
	 * 
	 * If, while parsing or evaluating the js request, the server encounters a js error, that
	 * will be encapsulated inside the js response in the ultimate HTTPResponse object's body.
	 * i.e., the webserver will treat the situation as a valid HTTP exchange, and the error 
	 * reporting will be entirely inside the JS message.
	 * 
	 * @param request the HTTPRequest to be handled (contains js request)
	 * @param textEncoding the desired encoding of the response ("text/plain", "text/html", "text/javascript", etc...)
	 * @return a fully formed HTTPResponse responding to the given request
	 * @throws WebException i mean hopefully not
	 */
	public static HTTPResponse handleJSRequest(HTTPRequest request, String textEncoding) throws WebException{
		
		JSRequest jsRequest;
		JSResponse jsResponse = null;
		HTTPResponse response = null;
		
		try{
		
			//Parse Javascript request
			jsRequest = JSTools.parseJSRequest(request.getBodyString()); 
			
			//Handle each type of request
			switch(jsRequest.getCommand()){
			
			case CAPITALIZE_JSREQ:
				jsResponse = JSTools.capitalize(jsRequest);
				break;
			
			case ECHO_JSREQ:
				jsResponse = JSTools.echo(jsRequest);
				break;
				
			case HELLO_JSREQ:
				jsResponse = JSTools.hello(jsRequest);
				break;
				
			case HELP_JSREQ:
				jsResponse = JSTools.help(jsRequest);
				break;
				
			case GETUSERSGROUPS_JSREQ:
				jsResponse = JSTools.getUsersGroups(jsRequest);
				break;
				
			case ADDUSER_JSREQ:
				jsResponse = JSTools.addUser(jsRequest);
				break;
				
			case REMOVEUSER_JSREQ:
				jsResponse = JSTools.removeUser(jsRequest);
				break;
				
			case UNKNOWN_JSREQ:
				jsResponse = JSResponse.jsError(jsRequest.getCommand(), new JSException("Unknown JS error"));
				break;
			
			default :
				jsResponse = JSResponse.jsError(jsRequest.getCommand(), new JSException("Unknown JS error"));
				break;
			
			}
		}
		
		//Handle JS specific exceptions. These are treated by the server as valid requests and responses
		catch(JSException e){
			jsResponse = JSResponse.jsError(e.getCommand(), e);
		}
		
		catch(SQLException e){
			jsResponse = JSResponse.jsError(JSRequestType.UNKNOWN_JSREQ, new UnderlyingErrorException("SQL Access Error"));
		}
		
		//In the end, we have to make a response
		finally{
			
			//Create HTTPResponse from JS response
			response = formResponse(jsResponse.getResponseBytes(), textEncoding);
			
		}
		
		return response;
	}

}