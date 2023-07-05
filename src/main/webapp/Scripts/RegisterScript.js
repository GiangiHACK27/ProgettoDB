const nameOrLastnameErrorMessage = "This field should contain only letters";
const emailErrorMessage = "The email field should be in the form username@domain.ext";
const passwordErrorMessage= "Your password must contain between 8 and 30 characters, at least one uppercase letter, one lowercase letter, one number and one special character like @$!%*?&"
const usernameErrorMessage = "Username can contain at most 25 characters"
const alreadyTaken = " is already taken";


function validateFormElem(formElem, span, errorMessage) {
	//In case of pattern matching
	if(formElem.checkValidity()){
		
		//Remove error for pattern mismatching
		var doc = new DOMParser().parseFromString(span.innerHTML, "text/html")
		formElem.classList.remove("error");
		if(errorMessage != null && doc.documentElement.textContent == errorMessage) {
			formElem.classList.remove("error");
			span.style.color = "black";
			span.innerHTML = "";
		}
		//Remove error for pattern mismatching

		var q;
		
		//In case we are checking for email o username validity, check if it is already taken
		if(formElem.name == "email" || formElem.name == "username") {
			$.ajaxSetup({ type: "GET", timeout : 10000 });
			
			console.log(formElem.value);
			
			$.get({ url:  "/GamingWorldShop/" + formElem.name + "AlreadyTakenServlet?" + formElem.name + "=" + formElem.value, success: function(data) {
				q = data;	
			},
			async: false
			})
			
			console.log(q.result);
			
			//In case the username or email are already used
			if(q.result == true) {
				formElem.classList.remove("error");
				span.style.color = "black";
				span.innerHTML = "";
				
				formElem.classList.add("error");
				span.style.color = "red";
				span.innerHTML = formElem.name + alreadyTaken;
				return false;
			}
			//In case the username or email are already used
			
			//In case the username or email are valid, clean the error
			if(span.innerHTML = formElem.name + alreadyTaken) {
				formElem.classList.remove("error");
				span.style.color = "black";
				span.innerHTML = "";
			}
			//In case the username or email are valid, clean the error
		}
		//In case we are checking for email o username validity, check if it is already taken
		
		return true;
	}
	//In case of pattern matching
	
	//In case of patter mismatching
	formElem.classList.add("error");
	span.style.color = "red";
	if (formElem.validity.valueMissing){
	} else {
		span.innerHTML = errorMessage;
	}
	//In case of patter mismatching
	
	return false;
}


function validate() {
	let valid = true;	
	let form = document.getElementById("inputForm");
	
	let span = document.getElementById("errorDiv");
	if(!validateFormElem(form.email, span, emailErrorMessage)){
		valid = false;
	} 

	if (!validateFormElem(form.password, span, passwordErrorMessage)){
		valid = false;
	}
	return valid;
}

function submitForm(e) { 
    let formValid = validate();

	//submit form only if it's valid
    if(formValid !== true) {
      e.preventDefault();
      return false;
    }
    //submit form only if it's valid
}

//add listener to submit button to submit form if it's valid
document.getElementById("submitButton").addEventListener("click", submitForm);
//add listener to submit button to submit form if it's valid

