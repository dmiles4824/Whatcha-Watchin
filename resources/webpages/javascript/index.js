var makeAlert = function() {
	alert("Attempting to change text");
}

var keyPress = function(e){
	if(e.keyCode == 13 || e.which == 13) {
		return true;
	}
	else {
		return false;
	}
}

var update = function(e, sourceElement, targetElement) {
	if(keyPress(e)){
		
		targetElement.innerHTML = targetElement.innerHTML + "\nYou said: " + sourceElement.value;
		targetElement.scrollTop = targetElement.scrollHeight;
		
		
		sourceElement.value = "";
		
		return false;
	}
	return true;
}