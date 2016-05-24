package webserver.webexception.jsexception;

import webserver.JSRequestType;
import webserver.webexception.WebException;

public class JSException extends WebException {
	
	/*******Constants*******/
	
	public static final long serialVersionUID = 0L;
	
	
	/*******Member fields*******/
	
	private JSRequestType command;
		
	
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
    
    public JSException(String message, JSRequestType command){
    	super(message);
    	this.command = command;
    }

    
    /*******Get/Set methods*******/
    
	public JSRequestType getCommand() {
		return command;
	}

	public void setCommand(JSRequestType command) {
		this.command = command;
	}
	
}
