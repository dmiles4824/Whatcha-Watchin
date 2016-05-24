/*******Package*******/
package webserver;

/*******Imports*******/
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import webserver.webexception.*;


/**
 * Provides server tools specific to parsing an HTTP message
 * @author Brian
 *
 */
public class ParseTools extends ServerTools {
	
	/*******Static Methods*******/
	
	/**
	 * Extracts all headers from an input stream holding an HTTP Message
	 * @param is
	 * @return
	 * @throws WebException
	 */
	public static String[] extractHTTPHeaders(InputStream is, byte[] headerBuffer) throws WebException{
		
		//Read headers into buffer
		headerBuffer = ParseTools.readUntilHeader(headerBuffer, is);
		
		//Read entire header buffer into a String
		String headerString = ParseTools.readHeaderString(headerBuffer);
		
		//Parse header String into a String array with each individual header
		String[] headerArray = ParseTools.parseHeaders(headerString);
		
		return headerArray;
	}
	
	/**
	 * Identifies whether the last four characters of the buffer are \r\n\r\n
	 * This would indicate the header is finished and we should stop reading from
	 * the buffer
	 * @param buffer
	 * @return
	 */
	protected static boolean endOfHeader(byte[] buffer, int lastValue) {
		
		if(lastValue >= 3 && lastValue < buffer.length) {									//If less than four values in buffer, can't have reached end
			if(		buffer[lastValue    ] == '\n' &&
					buffer[lastValue - 1] == '\r' &&
					buffer[lastValue - 2] == '\n' &&
					buffer[lastValue - 3] == '\r'){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a byte array with the header filled in. Will return null if not successfully read.
	 * @param buffer the empty buffer to fill the 
	 * @param is an input stream containing an HTTP message
	 * @return a byte buffer containing the HTTP header
	 * @throws WebException IO error on reading header into buffer
	 */
	protected static byte[] readUntilHeader(byte[] buffer, InputStream is) throws WebException{
		
		int numRead = 0;
		int startIndex = 0;
		int maxIntake = 1;
		
		//Loop conditions: Stop when buffer is filled without reaching header, the socket is closed, or the end of header is reached
		while(startIndex < buffer.length && numRead != -1 && !endOfHeader(buffer, startIndex - 1)) {
			
			try{
				numRead = is.read(buffer, startIndex, maxIntake);					//read one additional value into the buffer in the correct position
			}	
			//Couldn't read byte
			catch(IOException e) {
				throw new HeaderIOException("IO error in reading HTTP message headers");
			}
			startIndex++;
			
		}
		
		//Socket was closed during read
		if(numRead == -1){
			throw new SocketClosedException("Client socket closed before header could be read");
		}
		//Buffer filled
		else if(startIndex >= buffer.length){
			throw new BufferOverflow("Header length exceeded buffer space");
		}
		
		//Buffer successfully filled
		return buffer;
	}
	
	/**
	 * Takes the buffer containing just the header and reads it into a string
	 * @param buffer
	 * @return string holding the whole buffer
	 */
	protected static String readHeaderString(byte[] buffer){
		
		int i = 0;
		
		//Find last bit in header
		while(i < buffer.length && !endOfHeader(buffer, i-1)){				
			i++;
		}
		
		//Read first i characters into a string. This string is now the whole header section
		return new String(buffer, 0, i);		
	}

	/**
	 * Takes the string holding the whole header section and returns
	 * a string array with each entry containing the String of a single
	 * header line. Each header contains the end of line characters.
	 * The last entry will be a blank line signaling the end of the 
	 * header section. Will return null if invalid whole header.
	 * @param wholeHeader
	 * @return String array of headers
	 */
	protected static String[] parseHeaders(String wholeHeader){
		
		String[] headers = null;
		
		//Don't evaluate if wholeheader can't possibly be real
		if(wholeHeader != null && wholeHeader.length() >= 4){			
		
			ArrayList<String> headersList = new ArrayList<String>();
			StringBuilder currentLine = new StringBuilder();
			
			
			/**
			 * Add each character in order to a string builder. Each time an
			 * end of line character is detected, take whatever is in the 
			 * stringbuilder and add it as an entry to the array of headers.
			 * Will create two character blank line at the end of the array
			 */
			currentLine.append(wholeHeader.charAt(0));
			for(int i = 1; i < wholeHeader.length(); i++){
				currentLine.append(wholeHeader.charAt(i));								//Add character to StringBuilder
				if(		currentLine.length() > 1 && 									//If 	current line is long enough to contain a header
						currentLine.charAt(currentLine.length() - 1) == '\n' &&			//		and the last two characters indicate end of line
						currentLine.charAt(currentLine.length() - 2) == '\r'){
					headersList.add(currentLine.toString());							//Add current line to list of headers
					currentLine = new StringBuilder();									//Blank out the current line
				}
			}
			headers = headersList.toArray(new String[0]);
		}
		
		return headers;
	}

	/**
	 * Determines whether a given array of headers contains a request line
	 * Only returns true if it is a get or post method
	 * @param headers
	 * @return 
	 */
	protected static String findCommand(String[] headers){
		
		String command = null;
		
		if(headers != null && headers.length > 0){
			
			String firstLine = headers[0];
			
			if(		firstLine.startsWith("GET") ||
					firstLine.startsWith("get") ||
					firstLine.startsWith("Get")){
				command = "GET";
			}
			else if(firstLine.startsWith("POST") ||
					firstLine.startsWith("post") ||
					firstLine.startsWith("Post")){
				command = "POST";
			}
			
		}
		return command;
	}

	/**
	 * Determines whether the given list of headers contains a valid Content-Length
	 * header.
	 * @param headers
	 * @return
	 */
	protected static int findContentLength(String[] headers, String command) throws WebException{
		
		int contentLength = -1;
		
		if(headers != null && headers.length > 0){
			
			String currentLine;
			String first15Char;
			String remainder;
			
			//Look through all headers for content length
			for(int i = 0; i < headers.length && contentLength == -1; i++){
				
				currentLine = headers[i];
																							//In order to have content-length header...
				if(currentLine.length() > 18){												//Line must be at least 15 characters long
					first15Char = currentLine.substring(0,15);
					remainder = currentLine.substring(16, currentLine.length() - 2);
					remainder.replaceAll("\\s+","");
					if(		first15Char.equalsIgnoreCase("Content-Length:")){				//Must start with correct prefix
						
						try{
							if(Long.parseLong(remainder) >= 0){										//Rest of line must convert into long > 0
								contentLength = Integer.parseInt(remainder);
							}
						}
						catch(NumberFormatException e){
							throw new MissingContentLengthException("Invalid Content-Length format");
						}
					}
				}
			}
			
			if(contentLength == -1){
				if(command.equalsIgnoreCase("GET")){
					contentLength = 0;
				}
				else {
					throw new MissingContentLengthException("No content length given, can't read message body");
				}
			}
			
		}
		return contentLength;
	}

	/**
	 * Having read the headers, we know how many bytes we are expecting to be
	 * in the body. We now try to read that many bytes from the socket. If we fill the buffer
	 * or timeout waiting for that many bytes, we have failed so return null. 
	 * Otherwise, return buffer containing message body. 
	 * @param bodyBuffer
	 * @param is
	 * @param contentLength
	 * @return message body as byte[] buffer
	 */
	protected static byte[] readBody(byte[] bodyBuffer, InputStream is, long contentLength) throws WebException{
		
		int numRead = 0;
		int startIndex = 0;
		int maxIntake = 1;
		
		//Loop conditions: Stop when buffer is filled without reaching contentLength, we timeout, or contentLength is reached
		while(startIndex < bodyBuffer.length && startIndex < contentLength) {
			
			try {
				numRead = is.read(bodyBuffer, startIndex, maxIntake);					//read one additional value into the buffer in the correct position	
			}
			catch(IOException e){
				throw new HeaderIOException("IO exception reading HTTP message body");	//If IO error, throw exception
			}
				
			if(numRead == -1){							//If end of stream reached
				throw new SocketClosedException("End of stream reached while reading message body");
			}
			else{										//Byte was successfully read
				startIndex++;							//Move index to insert next value up by one
			}
		}
	
		//If we left loop because we successfully read everything
		if(startIndex >= bodyBuffer.length){
			throw new BufferOverflow("Buffer overflow while reading HTTP message body");
		}
		
		return bodyBuffer;
	}
	
	/**
	 * Extracts URL from request line
	 * @param requestLine
	 * @return
	 * @throws MalformedURLException
	 */
	protected static String parseURL(String requestLine) {
		
		Scanner scanner;
		ArrayList<String> words = new ArrayList<String>();
		
		//Determine number of "words" in requestLine
		scanner = new Scanner(requestLine);
		while(scanner.hasNext()){
			words.add(scanner.next());
		}
		scanner.close();
		
		//If request has three "words"
		if(words.size() == 3){
			return words.get(1);
		}
		return "/";
	}
	
	/**
	 * Extracts version from request line
	 * @param requestLine
	 * @return
	 * @throws MalformedURLException
	 */
	protected static String parseVersion(String requestLine) {
		
		Scanner scanner;
		ArrayList<String> words = new ArrayList<String>();
		
		//Determine number of "words" in requestLine
		scanner = new Scanner(requestLine);
		while(scanner.hasNext()){
			words.add(scanner.next());
		}
		scanner.close();
		
		//If request has three "words"
		if(words.size() == 3){
			return words.get(2);
		}
		return "HTTP/1.1";
	}
	
	/**
	 * Reads all bytes from a file exactly as they are.
	 * @param path
	 * @return
	 * @throws IOException
	 */
	protected static byte[] readBytesFromFile(Path path) throws WebException{
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(path); 
		}
		catch (IOException e){
			throw new HTMLReadException("Could not read " + path);
		}
		return encoded;
	}

	/**
	 * Takes a buffer array of a fixed length and returns a byte
	 * array of the first length items.
	 * @param buffer
	 * @param length
	 * @return
	 */
	protected static byte[] trimByteArray(byte[] buffer, int length) {
		
		byte[] trimmed = null;
		
		if(buffer != null){
			
			trimmed = new byte[length];
			
			for(int i = 0; i < buffer.length && i < length; i++){				
				trimmed[i] = buffer[i];
			}
			
		}
		return trimmed;
	}
	

	/**
	 * Merges two byte arrays into one.
	 * @param headerBytes
	 * @param bodyBytes
	 * @return
	 */
	protected static byte[] combineByteArrays(byte[] a, byte[] b){
		
		byte[] whole = null;
		
		if(a != null && b != null) {
			
			whole = new byte[a.length + b.length];
			
			for(int i = 0; i < a.length; i++){
				whole[i] = a[i];
			}
			for(int i = 0; i < b.length; i++){
				whole[i + a.length] = b[i];
			}
		}
		return whole;
	}
	
}


