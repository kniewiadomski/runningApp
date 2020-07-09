var constantForm = document.getElementsByClassName("constant");
var distanceIntervalForm = document.getElementsByClassName("distanceInterval");
var timeIntervalForm = document.getElementsByClassName("timeInterval");
var formElements = document.getElementsByClassName("formElement");

function changeType() {	
	hideFormElements()
    if (document.getElementById("typeSelect").value == "CONSTANT"){
    	showElements(constantForm)
    }     
    else if (document.getElementById("typeSelect").value == "DISTANCE_INTERVAL"){
    	showElements(distanceIntervalForm)
    }
    else if (document.getElementById("typeSelect").value == "TIME_INTERVAL"){
    	showElements(timeIntervalForm)
    }    
}

function hideFormElements(){
	for(var i = 0; i < formElements.length; i++){
		formElements[i].parentElement.style.display = "none";
		formElements[i].value = '';
    }
}

function showElements(list){
	for(var i = 0; i < list.length; i++){
		list[i].parentElement.style.display = "block";
    }
}

function initial(){
	for(var i = 0; i < formElements.length; i++){
		formElements[i].parentElement.style.display = "none";
    }
    if (document.getElementById("typeSelect").value == "CONSTANT"){
    	showElements(constantForm)
    }     
    else if (document.getElementById("typeSelect").value == "DISTANCE_INTERVAL"){
    	showElements(distanceIntervalForm)
    }
    else if (document.getElementById("typeSelect").value == "TIME_INTERVAL"){
    	showElements(timeIntervalForm)
    }    
}