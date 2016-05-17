/*******Package*******/
package webserver.webexception;


public class HTMLReadException extends WebException{
	
	public static final long serialVersionUID = 7L; 
	
	
	/*******Constructors*******/
    public HTMLReadException(){

    }

    public HTMLReadException(String message){
        super(message);
    }

    public HTMLReadException(Throwable cause){

        super(cause);

    }

    public HTMLReadException(String message, Throwable cause) {
        super(message, cause);
    }

}
