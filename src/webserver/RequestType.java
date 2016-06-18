/*******Package*******/
package webserver;


/*******Imports*******/

public enum RequestType {
	
	/*******Request types*******/
	
	INDEX_REQ			("text/html"),
	URL_REQ				("text/javascript"),
	JS_REQ				("text/plain"),
	UNKNOWN_URL_REQ		("text/plain"),
	OTHER_REQ			("text/plan");

	/*******Member fields*******/
	
	private final String textEncoding;
	
	
	/*******Constructors*******/
	
	RequestType(String textEncoding) {
		this.textEncoding = textEncoding;
	}
	
	
	/*******Get/Set Methods*******/
	
	public String getTextEncoding() {
		return textEncoding;
	}
}

