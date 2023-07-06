//function to format price
let USDollar = new Intl.NumberFormat('en-US', {
	    style: 'currency',
	    currency: 'USD',
	});
function formatPrice(price) {
	return USDollar.format(price/100);
};
//function to format price

$(document).ready(function() {
	var stringPrice = document.getElementById("price").innerHTML;
	document.getElementById("price").innerHTML = formatPrice(stringPrice);
});