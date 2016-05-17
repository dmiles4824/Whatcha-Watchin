package webserver;

public class HTTPRequest extends HTTPMessage {

	/*******Member fields*******/
	private String url;
	private String command;
	
	
	/*******Constructors*******/
	
	public HTTPRequest(String[] headers, byte[] message, String command, int contentLength, String url, boolean isError, String version){
		
		super(headers, message, contentLength, isError, version);
		this.command = command;
		this.url = url;
		
	}
	
	
	/*******Get/Set*******/
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		builder.append("	Command: " + this.getCommand() + "\r\n");
		builder.append("	Content-Length: " + this.getContentLength() + "\r\n");
		builder.append("	isError: " + this.isError() + "\r\n"); 
		builder.append("	Version: " + this.getVersion() + "\r\n");
		
		return builder.toString();
	}
	
}
