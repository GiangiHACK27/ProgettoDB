//function to format price
let USDollar = new Intl.NumberFormat('en-US', {
	    style: 'currency',
	    currency: 'USD',
	});
function formatPrice(price) {
	return USDollar.format(price/100);
};
//function to format price

//Convert price of game in dollars
$(document).ready(function() {
	var stringPrice = document.getElementById("buyButton").innerHTML;
	document.getElementById("buyButton").innerHTML = formatPrice(stringPrice);
});
//Convert price of game in dollars

//Function to change schede requirement
function changeSchedeReq(id) {
	schedes = document.getElementsByClassName("reqSchede");
	for(schede in schedes) {
		schedes[schede].style = "display: none";
	}
	
	document.getElementById(id+"Schede").style = "display: block";
}
//Function to change schede requirement

//Function for add to cart button
function addToCart(id) {	
	$.ajaxSetup({ type: "GET", timeout : 10000 })
	
	$.get("/GamingWorldShop/AddToCartServlet?gameId="+id+"&category=cart");
	
	str = $('#cartItemCount').html();
	str = str.replace(/\s/g, '');
	
	if(str == "") {
		console.log("ciao");
		$('#cartItemCount').html("1");
	} else {
		$('#cartItemCount').html(parseInt(str) + 1);	
	}
	
	$('#addToCartButton').css("display", "none");
}
//Function for add to cart button