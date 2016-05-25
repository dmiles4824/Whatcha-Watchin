/**
 * Process:
 * 
 * in index.js, you call a command held by commands.js (or wherever you're 
 * holding your general processing commands. These are functions that just
 * do basically anything you need to do client side.) Those functions may
 * require network access. If so, they call a prepare method in servercom.servercommands
 * 
 * Prepare method:
 * To the prepare method we send the following:
 * 		serverrequest command 
 * 		arguments to serverrequest as an array
 * 		action function
 * 		arguments to action function as an array
 * Then, the prepare method calls the serverrequest method and sets an event listener with the given action function
 * 
 * serverrequest methods:
 * The serverrequest commands are all the types of commands that can be sent to the
 * server. They take an array of Strings as arguments. These arguments are used to
 * format JSRequests to the server sent over HTTP. Then, the serverrequest method
 * actually sends the HTTP request and returns.
 * 
 * event listener:
 * when the response is fully downloaded, the event listener catches this state change.
 * The listener parses the incoming response, creating a response object.
 * The listener now executes the action method it was sent by the prepare method, sending
 * it the response object. Should handle HTTP errors (i.e. not throw a bad message back into
 * the JS logic)
 * 
 * action method:
 * the action method receives a fully featured response object as well as the array of 
 * arguments from whatever method called prepare(). The response object
 * knows if it is an error, and contains error feedback if it is an error. If it is 
 * not an error, it contains the data of the server response. As such, the action 
 * method is responsible for JSError handling, as what is done about errors may differ
 * based on what the user is trying to do in the specific action method. 
 * 
 * 
 * WHAT THE JS CLIENT SHOULD CONTAIN
 * per page js file: 
 * 		these files provide direct user interaction
 * some set up of client-side logic js files:
 * 		methods called by per page files after the per page files have decided what user input requires the client to do
 * 		any other helper methods needed to respond to user input
 * 		includes any necessary calls to prepare()
 * set of action methods:
 * 		methods called using by prepare() that tell the client what to do with a JSResponse
 * 		contain error handling
 * 
 */



var makeAlert = function() {
	alert("Attempting to change text");
}


var update = function(e, sourceElement, targetElement) {
	
	if(keyPress(e)){
		
		//Save input text
		var inputText = readText(sourceElement);
		
		//Write text to output and clear input
		appendText(targetElement, "You said: " + inputText);
		writeText(sourceElement, "");
		
		//Send capitalize text and write it
		sendText(inputText);
		
		return false;
	}
	return true;
}