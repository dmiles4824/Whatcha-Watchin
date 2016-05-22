package webserver.webexception;

public class InputStreamUnavailableException extends WebException {

	public static final long serialVersionUID = 9L; 
	
	
	/*******Constructors*******/
    public InputStreamUnavailableException(){

    }

    public InputStreamUnavailableException(String message){
        super(message);
    }

    public InputStreamUnavailableException(Throwable cause){

        super(cause);

    }

    public InputStreamUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
