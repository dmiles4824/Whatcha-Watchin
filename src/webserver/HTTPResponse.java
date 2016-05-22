package webserver;


public class HTTPResponse extends HTTPMessage {

	/*******Member fields*******/
	private String status;
	private int code;
	
	
	/*******Constructors*******/
	
	public HTTPResponse(String[] headers, byte[] message, String status, int contentLength, int code, boolean isError, String version){
		
		super(headers, message, contentLength, isError, version);
		this.status= status;
		this.code= code;
		
	}
	
	
	/*******Get/Set*******/
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	/*******Member methods*******/
	
	@Override
	public String toString(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("Message: \r\n" );
		
		builder.append("	Headers: " + "\r\n");
		for(int i = 0; i < this.getHeaders().length; i++){
			builder.append("		" + this.getHeaders()[i]);
		}		
		builder.append("	Message: " + "Who really cares, amirite\r\n"); 
		builder.append("	Status: " + this.getStatus() + "\r\n");
		builder.append("	Content-Length: " + this.getContentLength() + "\r\n");
		builder.append("	Code: " + this.getCode() + "\r\n");
		builder.append("	isError: " + this.isError() + "\r\n"); 
		builder.append("	Version: " + this.getVersion() + "\r\n");
		
		return builder.toString();
	}

	/**
	 * Creates an HTTPResponse object representing the proper error.
	 * @param version the HTTP version being used
	 * @param code error code
	 * @param status status message
	 * @param address address to send error to
	 * @param port port to send error to
	 * @return a fully formed HTTP message, ready to send
	 */
	public static HTTPResponse error(String version, int code, String status) {
		
		byte[] bodyBytes = new byte[0];
		int contentL = bodyBytes.length;
		boolean isError = true;
		
		String[] headers = new String[4];
		headers[0] = version + " " + code + " " + status + "\r\n";
		headers[1] = "Connection: close\r\n";
		headers[2] = "Content-length: " + contentL + "\r\n";
		headers[3] = "\r\n";
		
		HTTPResponse message = new HTTPResponse(headers, bodyBytes, status, contentL, code, isError, version);	
				
		return message;
	}

	
}
