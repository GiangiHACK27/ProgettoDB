var errorMessages = {
	fname: "The name can contain only characters separeted by a space",
	state: "The state is a pair of char",
	zip: "ZipCode is a number of 5 digit",
	cname: "The name on card can contain only characters separeted by a spaces",
	cvv: "CVV is a number of 3 digit",
	ccnum: "Card number follow this pattern: dddd-dddd-dddd-dddd",
	email: "Email must follow this pattern: name@domain.com"
}

//Function to check validity of a field
function checkField(field) {
	//In case the value is valid	
	if($("#" + field)[0].checkValidity()) {
		if(errorMessages[field] == $("#errorMessage").html()) {
			$("#errorMessage").html("");
		}
		
		return true;
	}
	//In case the value is valid
	
	//In case the value isn't valid, visualize error
	$("#errorMessage").html(errorMessages[field]);
	//In case the value isn't valid, visualize error
	
	return false;
}
//Function to check validity of a field

//Function to check validity of the form
function checkForm(e) {

	//Check if all the values of the fields are valid	
	for(let f in errorMessages) {
		let i = f;
		//In case the field hasn't a valid value
		if(! checkField(i)) {
			//Prevent submitting of the form
			e.preventDefault();
			//Prevent submitting of the form
			
			return false;
		}
		//In case the field hasn't a valid value
	}
	//Check if all the values of the fields are valid
	
	return true;
}
//Function to check validity of the form

//Function to assist on writing of card number
var oldLength = 0;
function assistCardNumber() {
			
	let textContent = document.getElementById("ccnum").value;
	
	if(oldLength < textContent.length) {
		if(textContent.length == 4 || textContent.length == 9 || textContent.length == 14)
			document.getElementById("ccnum").value = document.getElementById("ccnum").value + "-";
	}
		
	oldLength = textContent.length;
}
//Function to assist on writing of card number

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
	
	$("#ccnum").on("input", assistCardNumber);
	
	//Clear error box messages
	$("#errorMessage").html("");
	//Clear error box messages
});
//On load of the page set the function to check validity