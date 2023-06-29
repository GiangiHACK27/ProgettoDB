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