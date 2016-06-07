var parseJSResponse = function(message) {
	
	var commandIndex = message.indexOf("\n");
	
	var command = message.substring(0, commandIndex);
	
	var remainder = message.substring(commandIndex+1, message.length);
	
	var errorIndex = remainder.indexOf("\n");
	
	var error = remainder.substring(0, errorIndex);
	
	remainder = remainder.substring(errorIndex+1, remainder.length);
	
	var statusIndex = remainder.indexOf("\n");
	
	var status = remainder.substring(0, statusIndex);
	
	remainder = remainder.substring(statusIndex+1, remainder.length);
	
	return new JSResponse(command, error, status, remainder);

}