/*******Package*******/
package webserver.webexception;


public class WebException extends Exception{
	
	public static final long serialVersionUID = 0L; 
	
	
	/*******Constructors*******/
    public WebException(){

    }

    public WebException(String message){
        super(message);
    }

    public WebException(Throwable cause){

        super(cause);

    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

}
