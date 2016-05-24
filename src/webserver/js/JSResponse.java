package webserver.js;

public class JSResponse {
	
	/*******Member fields*******/
	private String responseString;
	private boolean isError;
	
	
	/*******Constructors*******/
	public JSResponse(String responseString, boolean isError){
		this.responseString = responseString;
		this.isError = isError;
	}
	
 	public JSResponse(String responseString){
		this(responseString, false);
	}

	
	/*******Get/Set methods*******/
	
	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	/*******Member methods*******/
	
	public byte[] getResponseBytes(){
		return responseString.getBytes();
	}
	
	
	/*******Static methods*******/
	
	public static JSResponse jsError(String errorMessage){
		return new JSResponse(errorMessage, true);
	}
	
}
