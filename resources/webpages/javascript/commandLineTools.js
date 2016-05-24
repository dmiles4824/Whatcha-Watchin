/**
 * Determines if the user pressed enter.
 */
var keyPress = function(e){
	if(e.keyCode == 13 || e.which == 13) {
		return true;
	}
	else {
		return false;
	}
}

/**
 * Echoes back whatever is in the source element to the target element. Blanks out
 * the source element.
 * @author Brian Clark
 */
var echo = function(sourceElement, targetElement){
	
	targetElement.innerHTML = targetElement.innerHTML + "\nYou said: " + sourceElement.value;
	targetElement.scrollTop = targetElement.scrollHeight;
	
	sourceElement.value = "";
	
}

/**
 * Writes the text to the element
 * @author Brian Clark
 */
var writeText = function(element, text){
	
	element.value = text;
}

/**
 * Appends the text as a new line
 */
var appendText = function(element, text){
	
	element.value += "\n" + text;
}

/**
 * Reads the text of an element
 */
var readText = function(element){
	return element.value;
}

var updateText = function(sourceElement, targetElement, text){
	
	writeText(sourceElement, "");
	appendText(targetElement, text);
	
}
