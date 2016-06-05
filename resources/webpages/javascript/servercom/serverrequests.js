/**
 * 
 * @args[0] the text to send
 * 
 */
var sendText = function(args){
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/", true);
	request.send(args[0]);
	
	return request;
}

var getUsersGroups = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/", true);
	request.send("getUsersGroups(" + args[0] + ")");
	
	return request;
	
}