var prepare = function (serverRequest, serverArgs, actionMethod, actionArgs) {
	
	//Run serverrequest
	var request = serverRequest(serverArgs); 	
	
	//Set event listener
	request.addEventListener("readystatechange", function(){ processCommand(request, actionMethod, actionArgs); }, false);
	
}


var processCommand = function(request, actionMethod, actionArgs){
	
	//Check if HTTP response has been fully downloaded
	if(request.readyState == 4){
		
		//Check if all good
		if(request.status == 200){
			
			//Add the response object to actionArgs
			actionArgs.unshift(parseJSResponse(request.responseText));
			
			//Run whatever action method
			actionMethod(actionArgs);
			
		}
		
		//Response indicates error
		else {
			
			//At the moment, just do nothing
			console.log("Received error");
			
		}
		
	}
	
}