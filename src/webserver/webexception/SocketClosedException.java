/*******Package*******/
package webserver.webexception;


public class SocketClosedException extends WebException{
	
	public static final long serialVersionUID = 4L; 
	
	
	/*******Constructors*******/
    public SocketClosedException(){

    }

    public SocketClosedException(String message){
        super(message);
    }

    public SocketClosedException(Throwable cause){

        super(cause);

    }

    public SocketClosedException(String message, Throwable cause) {
        super(message, cause);
    }

}
