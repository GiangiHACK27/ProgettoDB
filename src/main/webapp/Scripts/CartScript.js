let USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});

//when document is ready
window.addEventListener('load', function () {
	//format prices
	var classes = document.getElementsByClassName("price")
	for(var i = 0; i<classes.length; i++){
		classes[i].innerHTML = USDollar.format(classes[i].innerHTML/100);
	}
	//format prices
	
	calculateTotalPrice();
})
//when document is ready

$.ajaxSetup({ type: "GET", timeout : 10000 })

/*function updateCart(gameId){
	$.get("/DeleteFromCartServlet?gameId="+gameId+"&category=cart")
}*/

$(".removeButton a").on("click", function(){
	$.get("/GamingWorldShop/DeleteFromCartServlet?gameId="+$(this).attr("id")+"&category=cart");
	
	$(this).closest(".gameDiv").remove();
	
	if($('#gameSection').children().length == 0){
		$('#cartItemCount').html("");
		$('main').html("<section id=gameListContents> <h1 id=gameListTitle>Your shopping cart</h1> <p id=emptyGameListTitle>Your cart is now empty. <a href=/GamingWorldShop/Catalog.jsp>Let's fix that!</a></p></section>");
	}
	else{
		let newCartItemCount = parseInt($('#cartItemCount').html())-1;
		$('#cartItemCount').html(""+newCartItemCount);
		calculateTotalPrice();
	}
} )

$("#emptyCartButton a").on("click", function(){
	$('#cartItemCount').html("");
	$.get("/GamingWorldShop/EmptyCartServlet?category=cart");
	$('main').html("<section id=gameListContents> <h1 id=gameListTitle>Your shopping cart</h1> <p id=emptyGameListTitle>Your cart is now empty. <a href=/GamingWorldShop/Catalog.jsp>Let's fix that!</a></p></section>");

})

//sets total price of items in cart
function calculateTotalPrice(){
	//total price starts at 0
	let total = 0;
	//total price starts at 0
	
	//get all price divs
	var classes = document.getElementsByClassName("price")
	//get all price divs
	
	for(var i = 0; i<classes.length; i++){
		//format div to only read number (and skip dollar sign)
		let string = classes[i].innerHTML.replace(/,/g, ''); //replace thousand commas with white space otherwise they can't be parsed
		total += parseInt(string.substring(1, string.length)*100);
		//format div to only read number (and skip dollar sign)
	}
	
	//insert total price in DOM
	$('#totalPrice').html("");
	$('#totalPrice').append("Total price: "+USDollar.format(total/100));
	//insert total price in DOM
}
//sets total price of items in cart