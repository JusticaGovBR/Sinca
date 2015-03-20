function formatDate(campo){
	if(campo.value.length == 2){
		campo.value += "/";
	}else if(campo.value.length == 5){
		campo.value += "/";
	}else if(campo.value.length > 10){
		return false;
	}
}

/*
 * Somente permite campos numéricos sem sinal
 * Utilizado no evento onKeyPress dos componentes inputText
 */
function numericNoSignal($this, e){
	var tecla;
	// Internet Explorer
	if (document.all) 
		tecla = event.keyCode;
	// Outros Browsers
	else 
		tecla = e.which;	

	if(tecla > 47 && tecla < 58){
		return true;
	} else {
		return false;
	}
}