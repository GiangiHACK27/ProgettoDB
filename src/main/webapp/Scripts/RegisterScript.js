const nameOrLastnameErrorMessage = "This field should contain only letters";
const emailErrorMessage = "The email field should be in the form username@domain.ext";
const phoneErrorMessage = "The number field should be in the form ###-#######";
const passwordErrorMessage= "Your password must contain between 8 and 30 characters, at least one uppercase letter, one lowercase letter, one number and one special character like @$!%*?&"



function validateFormElem(formElem, span, errorMessage) {
	if(formElem.checkValidity()){
		if(span.innerHTML == errorMessage){
			formElem.classList.remove("error");
			span.style.color = "black";
			span.innerHTML = "";
		}
		return true;
	}
	formElem.classList.add("error");
	span.style.color = "red";
	if (formElem.validity.valueMissing){
	} else {
		span.innerHTML = errorMessage;
	}
	return false;
}


function validate() {
	let valid = true;	
	let form = document.getElementById("inputForm");
	
	let span = document.getElementById("errorDiv");
	if(!validateFormElem(form.email, span, emailErrorMessage)){
		valid = false;
	} 

	if (!validateFormElem(form.password, span, paswordErrorMessage)){
		valid = false;
	}
	return valid;
}

function submitForm(e) { 
    let formValid = validate();

    if(formValid !== true) {
      e.preventDefault();
      return false;
    }
}

document.getElementById("submitButton").addEventListener("click", submitForm);
