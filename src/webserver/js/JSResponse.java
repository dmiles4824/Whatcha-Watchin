package webserver.js;

import webserver.JSRequestType;
import webserver.ParseTools;
import webserver.webexception.jsexception.JSException;
import webserver.webexception.jsexception.NoErrorException;

public class JSResponse {
	
	/*******Member fields*******/
	private String responseString;
	private JSRequestType command;
	private JSException error;
	private String status;
	
	
	/*******Constructors*******/
	
	public JSResponse(JSRequestType command, String responseString, JSException error, String status){		
		this.command = command;
		this.responseString = responseString;
		this.error = error;
		this.status = status;
	}
	
	public JSResponse(JSRequestType command, String responseString, String status){		
		this(command, responseString, new NoErrorException(), status);
	}
	
	/*******Get/Set methods*******/
	
	//s
	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	public JSRequestType getCommand() {
		return command;
	}

	public void setCommand(JSRequestType command) {
		this.command = command;
	}
	
	public JSException getError() {
		return error;
	}

	public void setError(JSException error) {
		this.error = error;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	//e
	
	/*******Member methods*******/
	
	public byte[] getResponseBytes(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(this.getCommand().getCommandString() + '\n');
		builder.append(ParseTools.getLastWordInPackageName(this.getError().getName()) + "\n");
		builder.append(this.getStatus() + "\n");
		builder.append(this.getResponseString());
		
		String totalString = builder.toString();
		byte[] totalBytes = totalString.getBytes();
				
		return totalBytes;
	}
	
	
	/*******Static methods*******/
	
	public static JSResponse jsError(JSRequestType command, JSException error){
		return new JSResponse(command, error.getMessage(), error, "error");
	}
	
}
