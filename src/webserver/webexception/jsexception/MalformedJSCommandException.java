package webserver.webexception.jsexception;

import webserver.JSRequestType;

public class MalformedJSCommandException extends JSException {

	public static final long serialVersionUID = 13L; 
	
	
	/*******Constructors*******/
    public MalformedJSCommandException(){

    }

    public MalformedJSCommandException(String message){
        super(message);
    }

    public MalformedJSCommandException(Throwable cause){

        super(cause);

    }

    public MalformedJSCommandException(String message, Throwable cause) {
        super(message, cause);
    }
	
    public MalformedJSCommandException(String message, JSRequestType command){
    	super(message, command);
    }

    
}
