function makeAlert() {
	alert("Attempting to change text");
}

function changeText(e) {
	if(e.keyCode == 13 || e.which == 13){
		var targetElement = document.getElementById("print");
		var sourceElement = document.getElementById("input");
		
		targetElement.innerHTML = targetElement.innerHTML + "\nYou said: " + sourceElement.value;
		targetElement.scrollTop = targetElement.scrollHeight;
		
		
		sourceElement.value = "";
		
		return false;
	}
	return true;
}