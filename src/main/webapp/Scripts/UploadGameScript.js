$(document).ready(function() {
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
  var section = document.getElementById(sectionId);
  var nameValueFields = section.getElementsByClassName("nameValueFields")[0];
  var newRow = document.createElement("div");
  newRow.className = "nameValueRow";
  newRow.innerHTML = `
    <input type="text" name="${sectionId}[name][]" placeholder="Hardware type">
    <input type="text" name="${sectionId}[value][]" placeholder="Value">
    <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
  `;
  nameValueFields.appendChild(newRow);
}
//add a name-value couple into system requirements

//remove a name-value couple from system requirements
function removeNameValueRow(button) {
  var row = button.parentNode;
  row.parentNode.removeChild(row);
}
//remove a name-value couple from system requirements


//convert number input to dollars
function convertToDecimal(input) {
  var value = input.value;
  var decimalValue = value; // Assuming the user enters the amount in cents

  // Update the value attribute of the input element with the converted decimal value (in dollars)
  input.value = parseFloat(decimalValue).toFixed(2);
}
//convert number input to dollars
