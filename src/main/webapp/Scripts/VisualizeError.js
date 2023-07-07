var errorMessages = {
	fname: "The name can contain only characters",
	state: "The state is a pair of char",
	zip: "ZipCode is a number of 5 digit",
	cname: "The name on card can contain only characters",
	cvv: "CVV is a number of 3 digit"
}

function checkField(field) {	
	if($("#" + field)[0].checkValidity()) {
		if(errorMessages[field] == $("#errorMessage").html()) {
			$("#errorMessage").html("");
		}
		
		return true;
	}
	
	$("#errorMessage").html(errorMessages[field]);
	
	return false;
}

function checkForm(e) {
	
	for(f in errorMessages) {
		let i = f;
		if(! checkField(i)) {
			e.preventDefault();
			return false;
		}
	}
	
	return true;
}

//On load of the page set the function to check validity
$(document).ready(function() {
	
	//Add to form the check form function
	$("#submitButton").on("click", checkForm);
	//Add to form the check form function
	
	//Add to all the input of the form, a callback to on change event
	for(f in errorMessages) {
		let i = f;
		$("#" + i).on("change", function() { checkField(i); })
	}
	//Add to all the input of the form, a callback to on change event
	
	//Clear error box messages
	$("#errorMessage").html("");
	//Clear error box messages
});
//On load of the page set the function to check validity
