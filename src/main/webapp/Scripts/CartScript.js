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


function updateCart(gameId){
	$.get("/DeleteFromCartServlet?gameId="+gameId+"&category=cart")
}

$(".removeButton a").on("click", function(){
	$.get("/GamingWorldShop/DeleteFromCartServlet?gameId="+$(this).attr("id")+"&category=cart");
	$(this).closest(".gameDiv").remove();
	if($('#gameSection').children().length == 0){
		console.log($('#gameSection').children().length == 0);
		location.reload();
	}
} )
