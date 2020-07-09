var quantityForm = document.getElementsByClassName("quantity");
var distanceForm = document.getElementsByClassName("distance");
var totalDistanceForm = document.getElementsByClassName("totalDistance");
var timeOnDistanceForm = document.getElementsByClassName("timeOnDistance");
var formElements = document.getElementsByClassName("formElement");

function changeType() {	
	hideFormElements()
    if (document.getElementById("typeSelect").value == "QUANTITY"){
    	showElements(quantityForm);
    }     
    else if (document.getElementById("typeSelect").value == "DISTANCE"){
    	showElements(distanceForm);
    }
    else if (document.getElementById("typeSelect").value == "TOTAL_DISTANCE"){
    	showElements(totalDistanceForm);
    }    
    else if (document.getElementById("typeSelect").value == "TIME_ON_DISTANCE"){
    	showElements(timeOnDistanceForm);
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
    if (document.getElementById("typeSelect").value == "QUANTITY"){
    	showElements(quantityForm);
    }     
    else if (document.getElementById("typeSelect").value == "TOTAL_DISTANCE"){
    	showElements(totalDistanceForm);
    }
    else if (document.getElementById("typeSelect").value == "DISTANCE"){
    	showElements(distanceForm);
    }    
    else if (document.getElementById("typeSelect").value == "TIME_ON_DISTANCE"){
    	showElements(timeOnDistanceForm);
    }  
}