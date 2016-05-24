/*******Package*******/
package webserver;


/*******Imports*******/
import webserver.js.*;
import webserver.webexception.jsexception.*;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
//import java.util.NoSuchElementException;
//import java.util.StringTokenizer;

import com.opencsv.CSVReader;

/**
 * This class extends ServerTools to provide that class with the tools it needs to 
 * process Javascript requests.
 * @author Brian
 *
 */
public class JSTools extends ServerTools {
	
	public static JSRequest parseJSRequest(String requestString) throws JSException{
		
		JSRequest request;
		String command;
		ArrayList<String> arguments = new ArrayList<String>();
		
		
		int firstIndex = requestString.indexOf('(');
		int lastIndex = requestString.lastIndexOf(')');
		
		
		
		if(firstIndex == -1) {
			throw new NoCommandException("No command given");
		}
		else if(lastIndex != requestString.length()-1){
			System.out.println(lastIndex);
			System.out.println(requestString.length()-1);
			throw new JSException("Characters after ending )"); 
		}
		else {
			command = requestString.substring(0, firstIndex);
			
			String args = requestString.substring(firstIndex+1, lastIndex);			
			
	        String[] tokens = args.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	        arguments = new ArrayList<String>(Arrays.asList(tokens));
			
		}
		
		request = new JSRequest(command, arguments);
		
		return request;
	}
	
	public static JSRequestType parseJSRequestType(JSRequest request){
		
		JSRequestType jsRequestType;
		
		if(request.getCommand().equalsIgnoreCase("capitalize")){
			jsRequestType = JSRequestType.CAPITALIZE_JSREQ;
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
			
			response = new JSResponse("Received at " + dateString + ". Capitalized: " + args.get(0).toUpperCase());
		}
		else{
			response = JSResponse.jsError("Improper arguments");
		}
		
		return response;
	}
	
}
