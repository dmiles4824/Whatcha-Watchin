/*******Package*******/
package webserver;


/*******Imports*******/
import webserver.js.*;
import webserver.webexception.jsexception.*;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * This class extends ServerTools to provide that class with the tools it needs to 
 * process Javascript requests.
 * @author Brian
 *
 */
public class JSTools extends ServerTools {
	
	public static JSRequest parseJSRequest(String requestString) throws JSException{
		
		JSRequest request;
		String commandString;
		JSRequestType command;
		ArrayList<String> arguments = new ArrayList<String>();
		
		
		int firstIndex = requestString.indexOf('(');
		int lastIndex = requestString.lastIndexOf(')');
		
		
		//If no open parenthesis is found, can't parse command
		if(firstIndex == -1) {
			throw new NoCommandException("No command given");
		}
		
		//If no close parenthesis found, can't parse command
		else if(lastIndex == -1){
			throw new MalformedJSCommandException("No closing parenthesis");
		}
		
		//If a closing parenthesis is not the last character, problem
		else if(lastIndex != requestString.length()-1){
			throw new MalformedJSCommandException("Characters after ending )"); 
		}
		
		//If the command has spaces, invalid command syntax
		else if( (commandString = requestString.substring(0, firstIndex)).indexOf(' ') != -1 ){
			throw new MalformedJSCommandException("Invalid command syntax");
		}
		
		//Else, parse the message
		else {
			
			//Read all characters between outer open and close parentheses
			String args = requestString.substring(firstIndex+1, lastIndex);			
			
			//Fancy regex to tokenize as CSV accounting for quotes
	        String[] tokens = args.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	        arguments = new ArrayList<String>(Arrays.asList(tokens));
			
		}
		
		//Determine command
		command = JSTools.parseJSRequestType(commandString);
		
		//Form request
		request = new JSRequest(command, arguments);
		
		return request;
	}
	
	public static JSRequestType parseJSRequestType(String command){
		
		JSRequestType jsRequestType;
		
		if(command.equalsIgnoreCase("capitalize")){
			jsRequestType = JSRequestType.CAPITALIZE_JSREQ;
		}
		else if(command.equalsIgnoreCase("echo")){
			jsRequestType = JSRequestType.ECHO_JSREQ;
		}
		else if(command.equalsIgnoreCase("help")){
			jsRequestType = JSRequestType.HELP_JSREQ;
		}
		else if(command.equalsIgnoreCase("hello")){
			jsRequestType = JSRequestType.HELLO_JSREQ;
		}
		else {
			jsRequestType = JSRequestType.UNKNOWN_JSREQ;
		}
		
		return jsRequestType;
	}

	public static JSResponse capitalize(JSRequest request){
		
		JSResponse response;
		
		ArrayList<String> args = request.getArguments();
		
		if(args.size() == 1){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String dateString = dateFormat.format(date);
			
			response = new JSResponse(request.getCommand(), "Received at " + dateString + ". Capitalized: " + args.get(0).toUpperCase());
		}
		else{
			response = JSResponse.jsError(request.getCommand(), new MalformedJSCommandException("Improper arguments"));
		}
		
		return response;
	}
	
	public static JSResponse echo(JSRequest request){
		
		JSResponse response;
		
		ArrayList<String> args = request.getArguments();
		
		if(args.size() == 1){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String dateString = dateFormat.format(date);
			
			response = new JSResponse(request.getCommand(), "Received at " + dateString + ". Echo: " + args.get(0));
		}
		else{
			response = JSResponse.jsError(request.getCommand(), new MalformedJSCommandException("Improper arguments"));
		}
		
		return response;
	}
	
	public static JSResponse hello(JSRequest request){
		
		JSResponse response;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		response = new JSResponse(request.getCommand(), "Received at " + dateString + ". You smell nice, client");
		
		return response;
	}

	public static JSResponse help(JSRequest request){
		
		JSResponse response;
		
		//Replace this with some config file data
		StringBuilder builder = new StringBuilder();
		builder.append("Welcome to Whatcha-Watchin! The following are a list of valid Javascript commands. Don't forget to use quotes for strings!!! \n"); 
		
		//Retrieve all declared methods
		Method[] allMethods = JSTools.class.getDeclaredMethods();
		
		//If it has a JSResponse return type, it must be a command, so we will describe it
		for(Method m : allMethods){
			if(m.getReturnType().equals(JSResponse.class)){
				builder.append(m.getName() + "()\n");
			}
			
		}
		
		//Format response
		response = new JSResponse(request.getCommand(), builder.toString());
				
		return response;
	}


}
