/*******Package*******/
package webserver.webexception;


public class HeaderIOException extends WebException{
	
	public static final long serialVersionUID = 2L; 
	
	
	/*******Constructors*******/
    public HeaderIOException(){

    }

    public HeaderIOException(String message){
        super(message);
    }

    public HeaderIOException(Throwable cause){

        super(cause);

    }

    public HeaderIOException(String message, Throwable cause) {
        super(message, cause);
    }

}