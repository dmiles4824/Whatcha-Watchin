package webserver.js;

import webserver.JSRequestType;

public class JSResponse {
	
	/*******Member fields*******/
	private String responseString;
	private JSRequestType command;
	private boolean isError;
	
	
	/*******Constructors*******/
	public JSResponse(JSRequestType command, String responseString, boolean isError){		
		this.command = command;
		this.responseString = responseString;
		this.isError = isError;
	}
	
	public JSResponse(JSRequestType command, String responseString){		
		this.command = command;
		this.responseString = responseString;
		this.isError = false;
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

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
	//e
	
	/*******Member methods*******/
	
	public byte[] getResponseBytes(){
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(this.getCommand().getCommandString() + '\n');
		builder.append(this.isError() + "\n");
		builder.append(this.getResponseString());
		
		String totalString = builder.toString();
		byte[] totalBytes = totalString.getBytes();
				
		return totalBytes;
	}
	
	
	/*******Static methods*******/
	
	public static JSResponse jsError(JSRequestType command, String errorMessage){
		return new JSResponse(command, errorMessage, true);
	}
	
}
