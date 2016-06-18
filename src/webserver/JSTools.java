/*******Package*******/

package webserver;


/*******Imports*******/
import webserver.js.*;
import webserver.webexception.jsexception.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import sqlserver.SQLQueries;
import sqlserver.SQLQueryWrapper;
import sqlserver.sqlobjects.SQLDatabase;


/**
 * This class extends ServerTools to provide that class with the tools it needs to 
 * process Javascript requests.
 * @author Brian
 *
 */
public class JSTools extends ServerTools {
	
	/*******Constants*******/
	private final static String defaultUsername = "javabrianroot";
	private final static String defaultPassword = "biscotti";
	
	
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
			
			if(args.length() != 0) {
				//Fancy regex to tokenize as CSV accounting for quotes
				String[] tokens = args.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				arguments = new ArrayList<String>(Arrays.asList(tokens));
			}
			
		}
		
		//Determine command
		command = JSTools.parseJSRequestType(commandString);
		
		if(arguments.size() != command.getNumParam()){
			throw new MalformedJSCommandException("Improper number of arguments");
		}
		
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
		else if(command.equalsIgnoreCase("addUser")){
			jsRequestType = JSRequestType.ADDUSER_JSREQ;
		}
		else if(command.equalsIgnoreCase("removeUser")){
			jsRequestType = JSRequestType.REMOVEUSER_JSREQ;
		}
		else if(command.equalsIgnoreCase("addGroup")){
			jsRequestType = JSRequestType.ADDGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("removeGroup")){
			jsRequestType = JSRequestType.REMOVEGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("addMovie")){
			jsRequestType = JSRequestType.ADDMOVIE_JSREQ;
		}
		else if(command.equalsIgnoreCase("removeMovie")){
			jsRequestType = JSRequestType.REMOVEMOVIE_JSREQ;
		}
		else if(command.equalsIgnoreCase("getUsersGroups")){
			jsRequestType = JSRequestType.GETUSERSGROUPS_JSREQ;
		}
		else if(command.equalsIgnoreCase("addUserToGroup")){
			jsRequestType = JSRequestType.ADDUSERTOGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("removeUserFromGroup")){
			jsRequestType = JSRequestType.REMOVEUSERFROMGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("getUsersInGroup")){
			jsRequestType = JSRequestType.GETUSERSINGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("likeInGroup")){
			jsRequestType = JSRequestType.LIKEINGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("unlikeInGroup")){
			jsRequestType = JSRequestType.UNLIKEINGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("getUsersLikesInGroup")){
			jsRequestType = JSRequestType.GETUSERSLIKESINGROUP_JSREQ;
		}
		else if(command.equalsIgnoreCase("getAllLikesInGroup")){
			jsRequestType = JSRequestType.GETALLLIKESINGROUP_JSREQ;
		}
		else {
			jsRequestType = JSRequestType.UNKNOWN_JSREQ;
		}
		
		return jsRequestType;
	}

	public static JSResponse capitalize(JSRequest request){
		
		//Declarations
		JSResponse response;
		JSRequestType command = request.getCommand();
		String output;
		String status;
		
		//Arguments
		String input = request.getArguments().get(0);
		
		//Calculations
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		output = "Received at " + dateString + ". Capitalized: " + input.toUpperCase();
		status = "OK";
		
		response = new JSResponse(command, output, status); 
		
		return response;
	}
	
	public static JSResponse echo(JSRequest request){
		
		//Declarations
		JSResponse response;
		JSRequestType command = request.getCommand();
		String output;
		String status;
		
		//Arguments
		String input = request.getArguments().get(0);
		
		//Calculations
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		output = "Received at " + dateString + ". Echo: " + input;
		status = "OK";
		
		response = new JSResponse(command, output, status); 
		
		return response;
		
	}
	
	public static JSResponse hello(JSRequest request){
		
		//Declarations
		JSResponse response;
		JSRequestType command = request.getCommand();
		String output;
		String status;
		
		//Calculations
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		
		output = "Received at " + dateString + ". You smell nice, client";
		status = "OK";
		
		response = new JSResponse(command, output, status); 
		
		return response;
	}

	public static JSResponse help(JSRequest request){
		
		JSResponse response;
		
		//Replace this with some config file data
		StringBuilder builder = new StringBuilder();
		builder.append("Welcome to Whatcha-Watchin! The following are a list of valid Javascript commands. Don't forget to use quotes for strings!!! \n"); 
		
		//If it has a JSResponse return type, it must be a command, so we will describe it
		for(JSRequestType req : JSRequestType.values()){
			if(req != JSRequestType.UNKNOWN_JSREQ){
				builder.append(req.getCommandString());
				builder.append("(");
				for(int i = 0; i < req.getParamTypes().length - 1; i++){
					builder.append(req.getParamTypes()[i] + ", ");
				}
				builder.append(req.getParamTypes()[req.getParamTypes().length - 1]);
				builder.append(")\n");
			}
		}
		
		//Format response
		response = new JSResponse(request.getCommand(), builder.toString(), "OK");
				
		return response;
	}
	
	public static JSResponse addUser(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		String username = request.getArguments().get(0);
		String password = request.getArguments().get(1);
		
		//Build wrapper
		SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
		
		//Logic
		
		if(wrapper.handleFind(SQLQueries.findUser(username))){
			status = "UserAlreadyExists";
		}
		else {
			if(wrapper.handleUpdate(SQLQueries.addUser(username, password))){
				status =  "OK";
				stringResponse = "User " + username + " succesfully added.";
			}
			else {
				status = "AddError";
			}
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse removeUser(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		String username = request.getArguments().get(0);
		
		//Build wrapper
		SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
		
		//Logic
		
		if(!wrapper.handleFind(SQLQueries.findUser(username))){
			status = "NoSuchUser";
		}
		else {
			if(wrapper.handleUpdate(SQLQueries.removeUser(username))){
				status = "OK";
				stringResponse = "User " + username + " succesfully removed";
			}
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse addGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		String group_name = request.getArguments().get(0);
		
		//Build wrapper
		SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
		
		//Logic
		
		if(wrapper.handleUpdate(SQLQueries.addGroup(group_name))){
			status =  "OK";
			stringResponse = "Group " + group_name + " succesfully added.";
		}
		else {
			status = "AddError";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse removeGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		try {
			int group_id = Integer.parseInt(request.getArguments().get(0));
			
			//Build wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchUser";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.removeGroup(group_id))){
					status = "OK";
					stringResponse = "Group " + wrapper.handleSingle(SQLQueries.getGroupName(group_id)) + " succesfully removed.";
				}
			}
		}
		catch(NumberFormatException e) {
			status = "BadArgFormat";
		}
			
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse addMovie(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		try {
			String title = request.getArguments().get(0);
			int year = Integer.parseInt(request.getArguments().get(1));
			
			//Build wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			if(wrapper.handleFind(SQLQueries.findMovie(title, year))){
				status = "MovieAlreadyExists";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.addMovie(title, year))){
					status =  "OK";
					stringResponse = "Movie" + title + ", " + year + " succesfully added.";
				}
				else {
					status = "AddError";
				}
			}
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse removeMovie(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		try {
			String title = request.getArguments().get(0);
			int year = Integer.parseInt(request.getArguments().get(1));
			
			//Build wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findMovie(title, year))){
				status = "NoSuchMovie";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.removeMovie(title, year))){
					status = "OK";
					stringResponse = "Movie " + title + ", " + year + " succesfully removed.";
				}
				else {
					status = "RemoveError";
				}
			}
		}
		catch(NumberFormatException e) {
			status = "BadArgFormat";
		}
			
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse getUsersGroups(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		String username = request.getArguments().get(0);
		
		//Create wrapper
		SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
		
		System.out.println("wrapper made");
		
		//Logic
		
		//If user exists
		if(wrapper.handleFind(SQLQueries.findUser(username))){
			
			System.out.println("user found");
			
			ArrayList<String> list = wrapper.handle1D(SQLQueries.getUsersGroups(username));					//Retrieve lists of groups
			
			System.out.println("query run");
			
			if(list.size() == 0){
				
				System.out.println("no groups");
				
				status = "NoGroups";
			}
			else {
				
				System.out.println("success on execute");
				
				status = "OK";
				stringResponse = ParseTools.breakdownArrayListByLine(list);				//Turn into string form
				
				System.out.println("success on format");
			}
		}
		else {
			
			System.out.println("no such user");
			
			status = "NoSuchUser";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}

	public static JSResponse addUserToGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		try {
			String username = request.getArguments().get(0);
			int group_id = Integer.parseInt(request.getArguments().get(1));
			
			//Create wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findUser(username))){
				status = "NoSuchUser";
			}
			else if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.addMember(username, group_id))){
					status = "OK";
					stringResponse = username + " successfully added to " + wrapper.handleSingle(SQLQueries.getGroupName(group_id));
				}
				else {
					status = "AddError";
				}
			}
			
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse removeUserFromGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		try {
			String username = request.getArguments().get(0);
			int group_id = Integer.parseInt(request.getArguments().get(1));
			
			//Create wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findUser(username))){
				status = "NoSuchUser";
			}
			else if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else if(!wrapper.handleFind(SQLQueries.findMember(username, group_id))){
				status = "NoSuchUserInGroup";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.removeMember(username, group_id))){
					status = "OK";
					stringResponse = username + " successfully removed from " + wrapper.handleSingle(SQLQueries.getGroupName(group_id));
				}
				else {
					status = "AddError";
				}
			}
			
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse getUsersInGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		try {
			int group_id = Integer.parseInt(request.getArguments().get(0));
			
			//Create wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			
			if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else {
				ArrayList<String> list = wrapper.handle1D(SQLQueries.getUsersInGroup(group_id));				
				if(list.size() == 0){
					status = "NoUsers";
				}
				else {
					status = "OK";
					stringResponse = ParseTools.breakdownArrayListByLine(list);
				}
			}
			
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse likeInGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		try {
			String username = request.getArguments().get(0);
			int group_id = Integer.parseInt(request.getArguments().get(1));
			String title = request.getArguments().get(2);
			int year = Integer.parseInt(request.getArguments().get(3));
			
			//Build wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			if(!wrapper.handleFind(SQLQueries.findUser(username))){
				status = "NoSuchUser";
			}
			else if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else if(!wrapper.handleFind(SQLQueries.findMovie(title, year))){
				status = "NoSuchMovie";
			}			
			else {
				if(wrapper.handleUpdate(SQLQueries.addLikeInGroup(username, group_id, title, year))){
					status =  "OK";
					stringResponse = username + " now likes (" + title + ", " + year + ") in " + wrapper.handleSingle(SQLQueries.getGroupName(group_id));
				}
				else {
					status = "AddError";
				}
			}
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse unlikeInGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments
		try {
			String username = request.getArguments().get(0);
			int group_id = Integer.parseInt(request.getArguments().get(1));
			String title = request.getArguments().get(2);
			int year = Integer.parseInt(request.getArguments().get(3));
			
			//Build wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			if(!wrapper.handleFind(SQLQueries.findLikeInGroup(username, group_id, title, year))){
				status = "NoSuchEntry";
			}
			else {
				if(wrapper.handleUpdate(SQLQueries.removeLikeInGroup(username, group_id, title, year))){
					status =  "OK";
					stringResponse = username + " no longer likes (" + title + ", " + year + ") in " + wrapper.handleSingle(SQLQueries.getGroupName(group_id));
				}
				else {
					status = "RemoveError";
				}
			}
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse getUsersLikesInGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		try {
			String username = request.getArguments().get(0);
			int group_id = Integer.parseInt(request.getArguments().get(1));
			
			//Create wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findUser(username))){
				status = "NoSuchUser";
			}
			else if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else {
				ArrayList<ArrayList<String>> list = wrapper.handleMultiple(SQLQueries.getUsersLikesInGroup(username, group_id));				
				if(list.size() == 0){
					status = "NoLikesInGroup";
				}
				else {
					status = "OK";
					stringResponse = ParseTools.breakdownDoubleArrayListByLine(list);
				}
			}
			
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
	
	public static JSResponse getAllLikesInGroup(JSRequest request) throws SQLException{
		
		//Declarations
		JSResponse response;
		String stringResponse = "";
		String status = "";
		JSRequestType command = request.getCommand();
		
		//Arguments - assume correct number
		try {
			int group_id = Integer.parseInt(request.getArguments().get(0));
			
			//Create wrapper
			SQLQueryWrapper wrapper = new SQLQueryWrapper(SQLDatabase.newWWDatabase(), defaultUsername, defaultPassword);
			
			//Logic
			
			if(!wrapper.handleFind(SQLQueries.findGroup(group_id))){
				status = "NoSuchGroup";
			}
			else {
				ArrayList<ArrayList<String>> list = wrapper.handleMultiple(SQLQueries.getAllLikesInGroup(group_id));				
				if(list.size() == 0){
					status = "NoLikesInGroup";
				}
				else {
					status = "OK";
					stringResponse = ParseTools.breakdownDoubleArrayListByLine(list);
				}
			}
			
		}
		catch(NumberFormatException e){
			status = "BadArgFormat";
		}
		
		response = new JSResponse(command, stringResponse, status);
		
		return response;
	}
}
