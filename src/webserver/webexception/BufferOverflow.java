/*******Package*******/
package webserver.webexception;


public class BufferOverflow extends WebException{
	
	public static final long serialVersionUID = 3L; 
	
	
	/*******Constructors*******/
    public BufferOverflow(){

    }

    public BufferOverflow(String message){
        super(message);
    }

    public BufferOverflow(Throwable cause){

        super(cause);

    }

    public BufferOverflow(String message, Throwable cause) {
        super(message, cause);
    }

}
