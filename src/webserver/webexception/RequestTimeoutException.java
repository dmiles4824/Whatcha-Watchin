package webserver.webexception;

import webserver.webexception.WebException;

public class RequestTimeoutException extends WebException {
	
	public static final long serialVersionUID = 1L; 
	
	
	/*******Constructors*******/
	
	public RequestTimeoutException() {
		
	}
	
	public RequestTimeoutException(String string) {
		super(string);
	}
	
	public RequestTimeoutException(Throwable cause) {
		super(cause);
	}
	
	public RequestTimeoutException(String string, Throwable cause) {
		super(string, cause);
	}
	
}
