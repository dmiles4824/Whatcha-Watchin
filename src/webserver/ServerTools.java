/*******Package*******/

package webserver;


import java.io.IOException;

/*******Imports*******/

import java.net.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import webserver.webexception.ResponseIOException;
import webserver.webexception.WebException;

public class ServerTools {
	
	/*******Constants*******/
	public final static int defaultHeaderBufferSize = 1024;		//Default header buffer size, in bytes
	public final static int defaultBodyBufferSize = 1024;		//Default body buffer size, in bytes
	
	
	public static HTTPRequest parseHTTPRequest(Socket socket, int headerBufferSize, int bodyBufferSize) throws Exception{
		
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
		
		
		System.out.println("	Extracting headers");
		//Extract headers
		headers = ParseTools.extractHTTPHeaders(socket.getInputStream(), headerBuffer);
		
		System.out.println("	Parsing command");
		//Find the command type of the message
		command = ParseTools.findCommand(headers);
		
		System.out.println("	Parsing URL");
		//Find the requested URL
		url = ParseTools.parseURL(headers[0]);
		
		System.out.println("	Parsing content-length");
		//Determine Content-Length
		contentLength = ParseTools.findContentLength(headers, command);
		
		System.out.println("	Reading body");
		//Read body of message
		body = ParseTools.readBody(body, socket.getInputStream(), contentLength);
		
		System.out.println("	Error?");
		//Determine if error
		isError = false;
		
		System.out.println("	Parsing version");
		//Determine version
		version = ParseTools.parseVersion(headers[0]);
		
		System.out.println("	Creating HTTP message");
		//Create HTTPMessage
		request = new HTTPRequest(headers, body, command, contentLength, url, isError, version);
		
		return request;
	}
	
	public static HTTPRequest parseHTTPRequest(Socket socket) throws Exception{
		
		return ServerTools.parseHTTPRequest(socket, ServerTools.defaultHeaderBufferSize, ServerTools.defaultBodyBufferSize);
		
	}
	
	public static HTTPResponse formHTMLResponse(String htmlLocation) throws WebException{
		
		//Body retrieval
		byte[] body = ParseTools.readBytesFromFile(Paths.get(htmlLocation));
		
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
		String contentType = "Content-Type: text/html\r\n";
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
		
		byte[] headerBytes = ParseTools.stringArrayToByteArray(msg.getHeaders());
		byte[] bodyBytes = msg.getMessage();
		
		byte[] allBytes = ParseTools.combineByteArrays(headerBytes, bodyBytes);
		
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
		
		return requestType;
	}
}
