/*******Package*******/
package webserver.webexception;


public class MissingContentLengthException extends WebException{
	
	public static final long serialVersionUID = 5L; 
	
	
	/*******Constructors*******/
    public MissingContentLengthException(){

    }

    public MissingContentLengthException(String message){
        super(message);
    }

    public MissingContentLengthException(Throwable cause){

        super(cause);

    }

    public MissingContentLengthException(String message, Throwable cause) {
        super(message, cause);
    }

}