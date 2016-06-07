/**
 * @arg[0] the JSResponse object received from server
 * @arg[1] the target element
 */
var writeTextFromServer = function(args) {
	
	var response = args[0];
	var element = args[1];
	
	var output = 	  "Status: " + response.status
					+ "\n"
					+ "Response: "
					+ "\n"
					+ response.message;
	
	appendText(element, output);
	
	
}