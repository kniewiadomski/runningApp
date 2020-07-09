var i = 0;

function changeState(el) {	
	
	if(i == 0){
		el.innerHTML = "Ukryj filtr";
		i = 1;
	}
	else{
		el.innerHTML = "Pokaż filtr";
		i = 0;
	}
}