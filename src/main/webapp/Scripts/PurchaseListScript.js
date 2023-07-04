//On load of the page load the content
$(document).ready(function() {
	//set max date as current date
	document.getElementById('maxDate').valueAsDate = new Date();
	//set max date as current date
	
	$("form").on("change", function() {
		update();
	});
	
	$("form").on("submit", function(event) {
		event.preventDefault();
		update();
	});
	
	update();
});
//On load of the page load the content

//Function to update the page
function update() {
	//Retrieve parameter from the form
	var data = $("form").serializeArray();
	console.log(data);
	//Retrieve parameter from the form
	
	//Retrieve purchases from the db
	var url="/GamingWorldShop/admin/SearchPurchasesServlet";
	
	$.get(url, data, function buildTable(responseData) {
		$('table tbody').empty();
		responseData.purchases.forEach(purchase=>buildRow(purchase));
	});
	//Retrieve purchases from the db
}
//Function to update the page

//Function to build the table from the response data
function buildTable(responseData) {
	responseData.purchases.forEach(purchase=>buildRow(purchase));
}

function buildRow(purchase) {	
	//insert purchase into table body using javasript. Index -1 indicates to append at the end of the table
	document.getElementById("tableBody").insertRow(-1).innerHTML = "<td>" + purchase.id + "</td> <td>" + purchase.username +
	"</td><td>" + purchase.gameId + "</td><td>" + formatPrice(purchase.price) + "</td> <td>" + purchase.datePurchased + "</td>"
	//insert purchase into table body using javasript. Index -1 indicates to append at the end of the table
}
//Function to build the table from the response data

//function to format price
let USDollar = new Intl.NumberFormat('en-US', {
	    style: 'currency',
	    currency: 'USD',
	});
function formatPrice(price) {
	return USDollar.format(price/100);
};
//function to format price