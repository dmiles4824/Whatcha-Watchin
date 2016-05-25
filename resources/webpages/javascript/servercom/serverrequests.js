var sendText = function(text){
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/", true);
	request.send(text);
	request.addEventListener("readystatechange", function(){ processCommand(request); }, false);
	 
}

var capitalize = function(text){
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "/", true);
	request.send("capitalize(" + text + ")");
	request.addEventListener("readystatechange", function(){ processCommand(request);}, false);

	
}


var processCommand = function(e){
	
	var targetElement = document.getElementById("print");
	
	//Check if HTTP response has been fully downloaded
	if(e.readyState == 4){
		
		//Check if all good
		if(e.status == 200){
			
			console.log("Received response");
			appendText(targetElement, "Response: " + e.responseText);
			
		}
		
		//Response indicates error
		else {
			console.log("Received error");
			appendText(targetElement, "Error!: " + e.responseText);
		}
		
	}
	
}