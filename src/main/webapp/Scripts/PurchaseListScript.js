//On load of the page load the content
$(document).ready(function() {
	
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
	$("table").append("<tr>");
	
	$("table").append("<td>" + purchase.id + "</td>");
	$("table").append("<td>" + purchase.username + "</td>");
	$("table").append("<td>" + purchase.gameId + "</td>");
	$("table").append("<td>" + purchase.price + "</td>");
	$("table").append("<td>" + purchase.datePurchased + "</td>");
		
	$("table").append("</tr>");
}
//Function to build the table from the response data