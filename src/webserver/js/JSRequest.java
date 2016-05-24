/*******Package*******/
package webserver.js;


/*******Imports*******/
import java.util.ArrayList;

public class JSRequest{
	
	/*******Member fields********/
	private String command;
	private ArrayList<String> arguments;
	
	
	/*******Constructors*******/
	public JSRequest(String command, ArrayList<String> arguments){
		this.command = command;
		this.arguments = arguments;
		
	}
	
	
	/*******Get/Set methods*******/
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public ArrayList<String> getArguments() {
		return arguments;
	}
	public void setArguments(ArrayList<String> arguments) {
		this.arguments = arguments;
	}
	
	
	
}
