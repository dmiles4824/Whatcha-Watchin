/*******Package*******/

package webserver;


import java.io.IOException;
import java.io.InputStream;

/*******Imports*******/

import java.net.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import webserver.js.JSRequest;
import webserver.js.JSResponse;
import webserver.webexception.InputStreamUnavailableException;
import webserver.webexception.ResponseIOException;
import webserver.webexception.WebException;
import webserver.webexception.jsexception.JSException;

/**
 * The primary helper class for the java server
 * @author Brian
 *
 */
public class ServerTools {
	
	/*******Constants*******/
	public final static int defaultHeaderBufferSize = 1024;		//Default header buffer size, in bytes
	public final static int defaultBodyBufferSize = 1024;		//Default body buffer size, in bytes
	
	
	public static HTTPRequest parseHTTPRequest(Socket socket, int headerBufferSize, int bodyBufferSize) throws WebException{
		
		System.out.println("Attempting to parse");
		
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
	
	public static HTTPRequest parseHTTPRequest(Socket socket) throws Exception{
		
		return ServerTools.parseHTTPRequest(socket, ServerTools.defaultHeaderBufferSize, ServerTools.defaultBodyBufferSize);
		
	}
	
	public static HTTPResponse formResponse(String messageLocation, String contentType) throws WebException{
		
		byte[] body = ParseTools.readBytesFromFile(Paths.get(messageLocation));
		
		return formResponse(body, contentType);
		
	}
	
	public static HTTPResponse formResponse(byte[] message, String contentTypeString) throws WebException{
		
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
		
		//Headers
		String statusLine = version + " " + code + " "  + status + "\r\n";
		String date = "Date: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + "\r\n";
		String contentType = "Content-Type: " + contentTypeString + "\r\n";
		String contentLength = "Content-Length: " + contentL + "\r\n";
		String connection = "Connection: close\r\n";
		String end = "\r\n";
		
		ArrayList<String> headerList = new ArrayList<String>();
		
		headerList.add(statusLine);
		headerList.add(date);
		headerList.add(contentType);
		headerList.add(contentLength);
		headerList.add(connection);
		headerList.add(end);
		
		String[] headers = headerList.toArray(new String[0]);
		
		HTTPResponse response = new HTTPResponse(headers, body, status, contentL, code, isError, version);
		
		return response;
	}

	
	public static void sendHTTPMessage(HTTPMessage msg, Socket sock) throws WebException{
		
		byte[] allBytes = ParseTools.combineByteArrays(msg.getHeaderBytes(), msg.getMessage());
		
		try {
			sock.getOutputStream().write(allBytes);
		}
		catch(IOException e){
			throw new ResponseIOException("IO error writing back to client");
		}
		
	}
	
	/**
	 * Reads an HTTPRequest and determines what the purpose of the request is.
	 * @param msg the HTTPRequest to be parsed
	 * @return a RequestType specifying the purpose of the request
	 */
	public static RequestType parseRequestType(HTTPRequest msg) {
		
		RequestType requestType = RequestType.OTHER_REQ;
		
		if(msg.getCommand().equalsIgnoreCase("GET") && msg.getUrl().equalsIgnoreCase("/")){
			requestType = RequestType.INDEX_REQ;
		}
		
		else if(msg.getCommand().equalsIgnoreCase("GET")){
			requestType = RequestType.URL_REQ;
		}
		
		else if(msg.getCommand().equalsIgnoreCase("POST")){
			requestType = RequestType.JS_REQ;
		}
		
		return requestType;
	}

	
	public static HTTPResponse handleHTMLRequest(HTTPRequest request, String textEncoding) throws WebException{
		
		HTTPResponse response;
		
		String messageLocation = System.getProperty("user.dir") + "/resources/webpages/" + request.getUrl();
		
		response = ServerTools.formResponse(messageLocation, textEncoding);
		
		return response;
	}
	
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
		
		//In the end, we have to make a response
		finally{
			
			//Create HTTPResponse from JS response
			response = formResponse(jsResponse.getResponseBytes(), textEncoding);
			
		}
		
		return response;
	}

}