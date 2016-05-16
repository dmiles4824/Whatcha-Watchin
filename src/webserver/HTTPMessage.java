
/*******Package********/
package webserver;

/*******Imports*******/
import java.net.*;


public class HTTPMessage {

	/*******Member fields*******/
	private InetAddress address;
	private int port;
	private String[] headers;
	private byte[] message;
	private String command;
	private int contentLength;
	private boolean isError;
	
	/*******Constructors*******/
	public HTTPMessage(InetAddress address, int port, byte[] message, boolean isError) {
		
		this.address = address;
		this.port = port;
		this.message = message;
		this.isError = isError;
		
	}
	
	public HTTPMessage(InetAddress address, int port, String[] headers, byte[] message, boolean isError) {
		
		this(address, port, message, isError);
		this.headers = headers;
		
	}
	
	public HTTPMessage(InetAddress address, int port, String[] headers, byte[] message, String command, int contentLength, boolean isError){
		
		this(address, port, headers, message, isError);
		this.message = message;
		this.command = command;
		this.contentLength = contentLength;
		this.isError = isError;
		
	}
	
	
	/*******Get/Set Methods*******/
	
	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}
	
	public boolean isError() {
		return isError;
	}
	
	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	
	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	/**
	 * Creates an HTTPMessage object representing the proper error.
	 * @param version the HTTP version being used
	 * @param code error code
	 * @param status status message
	 * @param address address to send error to
	 * @param port port to send error to
	 * @return a fully formed HTTP message, ready to send
	 */
	public static HTTPMessage error(String version, int code, String status, InetAddress address, int port) {
		
		StringBuilder builder = new StringBuilder();
		
		String[] headers = new String[4];
		headers[0] = version + " " + code + " " + status + "\r\n";
		headers[1] = "Connection: close\r\n";
		headers[2] = "Content-length: 0\r\n";
		headers[3] = "\r\n";
		
		for(String s : headers){
			builder.append(s);
		}
		
		byte[] byteMessage = builder.toString().getBytes();
		
		
		HTTPMessage message = new HTTPMessage(address, port, headers, byteMessage, true);	
				
		return message;
	}
	
	
}
