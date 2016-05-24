package webserver.webexception.jsexception;

import webserver.webexception.WebException;

public class JSException extends WebException {
	
	public static final long serialVersionUID = 0L; 
	
	
	/*******Constructors*******/
    public JSException(){

    }

    public JSException(String message){
        super(message);
    }

    public JSException(Throwable cause){

        super(cause);

    }

    public JSException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
