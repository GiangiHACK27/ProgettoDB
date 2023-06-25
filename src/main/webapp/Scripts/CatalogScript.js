let USDollar = new Intl.NumberFormat('en-US', {
	    style: 'currency',
	    currency: 'USD',
	});
$(document).ready(function() {

	function updateCatalog(){
		var data = $('form').serialize();
		var url="/GamingWorldShop/GetCatalogGameObjects"
		$.get(url, data, function(responseData){
			let giochi = responseData;
			if(giochi.games.length == 0){
				$('#gameListSection').html("<div class=gameDiv><p class=gameTitle>"+
			 				"No results found for your search"+
			 			"</p>"+	
			 		"</div>");
				
			}
			$('#gameListSection').html("");
			giochi.games.forEach(game=>addGame(game));
		})
	}
	
	function submitForm(e) { 
	    e.preventDefault();
	    updateCatalog();
	    return false;
	}

	function addGame(game){
		$('#gameListSection').append("<div class=gameDiv><p class=gameImage>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +"><img src='RetrieveGameImageServlet?gameId="+game.id +"&role=BANNER' alt='game logo'> </a>"+	
			 			"</p>"+
			 			"<p class=gamePrice>"+
			 				"<span class= price>"+USDollar.format(game.price/100) +"</span><br>"+
			 			"</p>"+
			 			"<p class=gameTitle>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +">"+game.name +"</a><br><span class=description>"+game.shortDescription+"</span"+
			 			"</p>"+	
			 		"</div>");
	}
	
	document.getElementById("submitButton").addEventListener("click", submitForm);
	$('#rangeOutput').html(formatPrice(parseInt($('#rangeOutput').html())))
	updateCatalog();

})
	function formatPrice(price){
		return USDollar.format(price/100);
	};
