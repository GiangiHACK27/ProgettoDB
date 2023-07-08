$(document).ready(function() {
	//set max date as current date
	document.getElementById('releaseDate').valueAsDate = new Date();
	//set max date as current date
	
	//This function removes the 'required' tag from checkboxes when at least one is selected, and adds it when they're all unselected
    var requiredCheckboxes = $(':checkbox[required]');
    requiredCheckboxes.change(function(){
        if(requiredCheckboxes.is(':checked')) {
            requiredCheckboxes.removeAttr('required');
        } else {
            requiredCheckboxes.attr('required', 'required');
        }
    });
    //This function removes the 'required' tag from checkboxes when at least one is selected, and adds it when they're all unselected
    
})

//add a name-value couple into system requirements
function addNameValueRow(sectionId) {
  let section = document.getElementById(sectionId);
  let nameValueFields = section.getElementsByClassName("nameValueFields")[0];
  let newRow = document.createElement("div");
  newRow.className = "nameValueRow";
  newRow.innerHTML = `
    <input type="text" required name="${sectionId}[name][]" placeholder="Hardware type" maxlength="30">
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
