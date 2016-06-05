/**
 * @arg[0] the JSResponse object received from server
 * @arg[1] the target element
 */
var writeTextFromServer = function(args) {
	
	var response = args[0];
	var element = args[1];
	
	appendText(element, response.message);
	
	
}

var writeUsersGroups = function(args) {
	
	var response = args[0];
	var element = args[1];
	
	append(element, response.message);
	
}