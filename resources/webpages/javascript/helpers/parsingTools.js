var parseJSResponse = function(message) {
	
	
	console.log("message: " + message);
	
	var commandIndex = message.indexOf("\n");
	console.log("commandIndex: " + commandIndex);
	
	var command = message.substring(0, commandIndex);
	console.log("command: " + command);
	
	var remainder = message.substring(commandIndex+1, message.length);
	console.log("remainder1: " + remainder);
	
	var errorIndex = remainder.indexOf("\n");
	console.log("errorIndex: " + errorIndex);
	
	var error = remainder.substring(0, errorIndex);
	console.log("error: " + error);
	
	var remainder = remainder.substring(errorIndex+1, remainder.length);
	console.log("message: " + remainder);
	
	return new JSResponse(command, error, remainder);

}