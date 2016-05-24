/*******Package*******/
package webserver.webexception.jsexception;

import webserver.JSRequestType;

public class NoCommandException extends JSException {

	public static final long serialVersionUID = 11L; 
	
	
	/*******Constructors*******/
    public NoCommandException(){

    }

    public NoCommandException(String message){
        super(message);
    }

    public NoCommandException(Throwable cause){

        super(cause);

    }

    public NoCommandException(String message, Throwable cause) {
        super(message, cause);
    }
	
    public NoCommandException(String message, JSRequestType command){
    	super(message, command);
    }
	
}
