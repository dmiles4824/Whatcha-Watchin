/*******Package*******/

package webserver.webexception;



/******Imports*******/
import webserver.HTTPResponse;
import webserver.ConnectionManager;

/**
 * Error handling for connection manager
 * @author Brian
 *
 */
public class ExceptionHandler {
	
	/**
	 * Method that hands incoming exception, and builds the appropriate HTTPResponse
	 * @param e
	 * @return
	 */
	public static HTTPResponse handleWebException(WebException e){
		
		HTTPResponse response;
		
		//Malformed request
		if(e instanceof MalformedHTTPRequestException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 400, e.getMessage());
		}
		
		//Timed out waiting for client to send data
		else if(e instanceof RequestTimeoutException){		//Initial request timed out
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 408, e.getMessage());
		}
		
		//Could not extract InputStream from client socket
		else if(e instanceof InputStreamUnavailableException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, e.getMessage());
		}
		
		//Could not read request message headers
		else if(e instanceof HeaderIOException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 400, e.getMessage());
		}
		
		//Socket closed before message could be read
		else if(e instanceof SocketClosedException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 408, e.getMessage());
		}
		
		//Buffer overflow
		else if(e instanceof BufferOverflow){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 507, e.getMessage());
		}
		
		//Missing content length on a message type that requires it
		else if(e instanceof MissingContentLengthException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 411, e.getMessage());
		}
		
		//Couldn't read resource file
		else if(e instanceof HTMLReadException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 404, e.getMessage());
		}		
		
		//Couldn't send response
		else if(e instanceof ResponseIOException){
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, e.getMessage());
		}		
		
		else{					//Unrecognized WebException
			response = HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, e.getMessage());
		}
		
		return response;
	}
	
	public static HTTPResponse handleException(Exception e){
		
		return HTTPResponse.error(ConnectionManager.defaultHTTPVersion, 500, e.getMessage());
		
	}
	
}
