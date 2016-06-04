package webserver.webexception.jsexception;

import webserver.JSRequestType;

public class UnderlyingErrorException extends JSException {

	private static final long serialVersionUID = 14L;

	public UnderlyingErrorException(){

    }

    public UnderlyingErrorException(String message){
        super(message);
    }

    public UnderlyingErrorException(Throwable cause){

        super(cause);

    }

    public UnderlyingErrorException(String message, Throwable cause) {
        super(message, cause);
    }
	
    public UnderlyingErrorException(String message, JSRequestType command){
    	super(message, command);
    }
	
}
