package webserver.webexception;

public class ResponseIOException extends WebException {

	public static final long serialVersionUID = 8L; 
	
	/*******Constructors*******/
    public ResponseIOException(){

    }

    public ResponseIOException(String message){
        super(message);
    }

    public ResponseIOException(Throwable cause){

        super(cause);

    }

    public ResponseIOException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
