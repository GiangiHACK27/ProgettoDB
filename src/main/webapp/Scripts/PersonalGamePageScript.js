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
	console.log("CRISTO");
	var stringPrice = document.getElementById("buyButton").innerHTML;
	document.getElementById("buyButton").innerHTML = formatPrice(stringPrice);
});
//Convert price of game in dollars

function changeSchedeReq(id) {
	schedes = document.getElementsByClassName("reqSchede");
	for(schede in schedes) {
		schedes[schede].style = "display: none";
	}
	
	document.getElementById(id+"Schede").style = "display: block";
}