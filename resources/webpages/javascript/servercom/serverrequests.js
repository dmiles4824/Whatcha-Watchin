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

var capitalize = function(text){
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/", true);
	request.send("capitalize(" + text + ")");
	request.addEventListener("readystatechange", function(){ processCommand(request);}, false);

	
}