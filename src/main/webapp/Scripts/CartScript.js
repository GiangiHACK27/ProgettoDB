let USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});

window.addEventListener('load', function () {
  var classes = document.getElementsByClassName("price")
  for(var i = 0; i<classes.length; i++){
	  classes[i].innerHTML = USDollar.format(classes[i].innerHTML/100);
  }
})

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
	}
} )

$("#emptyCartButton a").on("click", function(){
	$('#cartItemCount').html("");
	$.get("/GamingWorldShop/EmptyCartServlet?category=cart");
	$('main').html("<section id=gameListContents> <h1 id=gameListTitle>Your shopping cart</h1> <p id=emptyGameListTitle>Your cart is now empty. <a href=/GamingWorldShop/Catalog.jsp>Let's fix that!</a></p></section>");

})