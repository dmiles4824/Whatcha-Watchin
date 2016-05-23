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
		
		//Send text to server
		sendText(inputText);
		
		
		return false;
	}
	return true;
}