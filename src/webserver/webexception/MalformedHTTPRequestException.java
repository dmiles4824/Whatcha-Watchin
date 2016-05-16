/*******Package*******/
package webserver.webexception;


public class MalformedHTTPRequestException extends WebException{
	
	public static final long serialVersionUID = 6L; 
	
	
	/*******Constructors*******/
    public MalformedHTTPRequestException(){

    }

    public MalformedHTTPRequestException(String message){
        super(message);
    }

    public MalformedHTTPRequestException(Throwable cause){

        super(cause);

    }

    public MalformedHTTPRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
