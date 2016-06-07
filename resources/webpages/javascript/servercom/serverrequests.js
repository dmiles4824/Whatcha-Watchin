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

var capitalize = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("capitalize(" + args[0] + ")");
	
	return request;
}

var echo = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("echo(" + args[0] + ")");
	
	return request;
}

var help = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("help()");
	
	return request;
}

var hello = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("hello()");
	
	return request;
}

var addUser = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("addUser(" + args[0] + "," + args[1] + ")");
	
	return request;
}

var removeUser = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("removeUser(" + args[0] + ")");
	
	return request;
}

var addGroup = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("addGroup(" + args[0] + ")");
	
	return request;
}

var removeGroup = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("removeGroup(" + args[0] + ")");
	
	return request;
}

var addMovie = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("addMove(" + args[0] + "," + args[1] + ")");
	
	return request;
}

var removeMovie = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("removeMovie(" + args[0] + ")");
	
	return request;
}

var getUsersGroups = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("getUsersGroups(" + args[0] + ")");
	
	return request;
}

var addUserToGroup = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("capitalize(" + args[0] + "," + args[1] + ")");
	
	return request;
}

var removeUserFromGroup = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("removeUserFromGroup(" + args[0] + "," + args[1] + ")");
	
	return request;
}

var getUsersInGroup = function(args) {
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/" true);
	request.send("getUsersInGroup(" + args[0] + ")");
	
	return request;
}