/*******Package*******/
package webserver.js;


/*******Imports*******/
import java.util.ArrayList;
import webserver.JSRequestType;

public class JSRequest{
	
	/*******Member fields********/
	private JSRequestType command;
	private ArrayList<String> arguments;
	
	
	/*******Constructors*******/
	public JSRequest(JSRequestType command, ArrayList<String> arguments){
		this.command = command;
		this.arguments = arguments;
		
	}
	
	
	/*******Get/Set methods*******/
	
	public JSRequestType getCommand() {
		return command;
	}
	public void setCommand(JSRequestType command) {
		this.command = command;
	}
	public ArrayList<String> getArguments() {
		return arguments;
	}
	public void setArguments(ArrayList<String> arguments) {
		this.arguments = arguments;
	}
	
	
	
}
