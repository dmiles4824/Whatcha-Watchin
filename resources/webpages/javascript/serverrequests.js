var sendText = function(text){
	
	var request = new XMLHttpRequest();
	
	request.open('POST', "127.0.0.1/", true);
	request.send(text);
	request.addEventListener("readystatechange", processSendText, true);
	 
}

var processSendText = function(e){
	
	//Check if HTTP response has been fully downloaded
	if(request.readyState == 4){
		
		//Check if all good
		if(request.status == 200){
			
			appendText(getElementById("print"), "Response: " + request.responseText);
			
		}
		
		//Response indicates error
		else {
			appendText(getElementById("print"), "Error!: " + request.responseText);
		}
		
	}
	
}