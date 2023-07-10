const errorMessages = {
	price: "Value must be greater than zero and less than 10000",
	name: "Name must be an alphanumeric sequence of characters with lenght between 1 and 30 characters",
	publisher: "Publisher must be an alphanumeric sequence of characters with lenght between 1 and 30 characters"
}

const requirementsError = "It's not possible to have same name for requirement of the same OS"

$(document).ready(function() {
	
	//convert price
	convertToDecimal(document.getElementById('price'))
	//convert price
	
	//This function removes the 'required' tag from checkboxes when at least one is selected, and adds it when they're all unselected
    let requiredCheckboxes = $(':checkbox[name="categories"]');
    requiredCheckboxes.change(function(){
        if(requiredCheckboxes.is(':checked')) {
            requiredCheckboxes.removeAttr('required');
        } else {
            requiredCheckboxes.attr('required', 'required');
        }
    });
    //This function removes the 'required' tag from checkboxes when at least one is selected, and adds it when they're all unselected
    
    //Add to form the check form function
	$("#submitButton").on("click", checkForm);
	//Add to form the check form function
	
	//Add to all the input of the form, a callback to on change event
	for(let f in errorMessages) {
		let i = f;
		$("#" + i).on("change", function() { checkField(i); })
	}
	//Add to all the input of the form, a callback to on change event
})

//add a name-value couple into system requirements
function addNameValueRow(sectionId) {
  let section = document.getElementById(sectionId);
  let nameValueFields = section.getElementsByClassName("nameValueFields")[0];
  let newRow = document.createElement("div");
  newRow.className = "nameValueRow";
  newRow.innerHTML = `
    <input class="${sectionId}nameReq" type="text" required name="${sectionId}[name][]" placeholder="Hardware type" maxlength="30">
    <input type="text" required name="${sectionId}[value][]" placeholder="Value" maxlength="30">
    <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
  `;
  nameValueFields.appendChild(newRow);
}
//add a name-value couple into system requirements

//remove a name-value couple from system requirements
function removeNameValueRow(button) {
  let row = button.parentNode;
  row.parentNode.removeChild(row);
}
//remove a name-value couple from system requirements


//convert number input to dollars
function convertToDecimal(input) {
  let value = input.value;
  let decimalValue = value; // Assuming the user enters the amount in cents

  // Update the value attribute of the input element with the converted decimal value (in dollars)
  input.value = parseFloat(decimalValue).toFixed(2);
}
//convert number input to dollars

//Function to check validity of a field
function checkField(field) {
	//In case the value is valid	
	if($("#" + field)[0].checkValidity()) {
		
		$("#" + field).removeClass("error");
		
		if(errorMessages[field] == $("#errorMessage").html()) {
			$("#errorMessage").html("");
		}
		
		return true;
	}
	//In case the value is valid
	
	//In case the value isn't valid, visualize error
	$("#errorMessage").html(errorMessages[field]);
	$("#" + field).addClass("error");
	//In case the value isn't valid, visualize error
	
	return false;
}
//Function to check validity of a field

//Function to check validity of the form
function checkForm(e) {
	
	//Clear the error message box
	$("#errorMessage").html("");
	//Clear the error message box

	//Check if for the same platform there is requirements with same name
	let platforms = ["windows", "mac", "linux"];
	for(let p in platforms) {
		let platform = platforms[p];
		
		$(`.${platform}nameReq`).each(function(i, o){
			let value = o.value.trim();
			
			$(`.${platform}nameReq`).each(function(i2, o2) {
				let v = o2.value.replace(/^\s+/g, '');
				let v2 = value.replace(/^\s+/g, '');
				v.replace(/\s+$/g, '');
				v2.replace(/\s+$/g, ''); 
				if(i != i2 && value != "" && v == v2) {
					$("#errorMessage").html(requirementsError);
					e.preventDefault();
					return false;
				}
			});
		})
		
	}
	//Check if for the same platform there is requirements with same name

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
