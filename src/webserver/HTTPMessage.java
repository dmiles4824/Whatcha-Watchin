
/*******Package********/
package webserver;


/*******Imports*******/


public class HTTPMessage {

	/*******Member fields*******/
	private String[] headers;
	private byte[] message;
	private int contentLength;
	private boolean isError;
	private String version;
	
	/*******Constructors*******/
	public HTTPMessage(byte[] message, boolean isError) {
		this.message = message;
		this.isError = isError;
		
	}
	
	public HTTPMessage(String[] headers, byte[] message, boolean isError) {
		
		this(message, isError);
		this.headers = headers;
		
	}
	
	public HTTPMessage(String[] headers, byte[] message, int contentLength, boolean isError, String version){
		
		this(headers, message, isError);
		this.message = message;
		this.contentLength = contentLength;
		this.isError = isError;
		this.version = version;
	}
	
	
	/*******Get/Set Methods*******/

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

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Message: \r\n" );
		
		builder.append("	Headers: " + "\r\n");
		for(int i = 0; i < this.getHeaders().length; i++){
			builder.append("		" + this.getHeaders()[i]);
		}		
		builder.append("	Message: " + "Who really cares, amirite\r\n"); 
		builder.append("	Content-Length: " + this.getContentLength() + "\r\n");
		builder.append("	isError: " + this.isError() + "\r\n"); 
		builder.append("	Version: " + this.getVersion() + "\r\n");
		
		return builder.toString();
	}
	
	
	/*******Static methods*******/
	
	/**
	 * Creates an HTTPMessage object representing the proper error.
	 * @param version the HTTP version being used
	 * @param code error code
	 * @param status status message
	 * @param address address to send error to
	 * @param port port to send error to
	 * @return a fully formed HTTP message, ready to send
	 */
	public static HTTPMessage error(String version, int code, String status) {
		
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
		
		
		HTTPMessage message = new HTTPMessage(headers, byteMessage, true);	
				
		return message;
	}
	
	
}
