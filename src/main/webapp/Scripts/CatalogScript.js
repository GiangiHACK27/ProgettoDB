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
	//Associate an event to the form that updates the catalog each time a value is changed
	let form = document.querySelector('form');
		form.addEventListener('change', function(){
			updateCatalog(1);
		});
	//Associate an event to the form that updates the catalog each time a value is changed
	
	//Associate an event to the form that updates the catalog each time the form is submitted
	form.addEventListener('submit', function(event) { event.preventDefault(); updateCatalog(1); } );
	//Associate an event to the form that updates the catalog each time the form is submitted

	//format the range value to dollars
	$('#rangeOutput').html(formatPrice(parseInt($('#rangeOutput').html())))
	//format the range value to dollars
	
	//load the catalog when the document 
	updateCatalog(1);
})

	function updateCatalog(page){
		var data = $('form').serializeArray(); //convert form to array
		data.push({name: "page", value: page})
		var url="/GamingWorldShop/GetCatalogGameObjects"
		$.get(url, data, function(responseData){
			let giochi = responseData;
			if(giochi.games.length == 0) {
				$('#gameListSection').html("<div class=gameDiv><p class=gameTitle>"+
			 				"No results found for your search"+
			 			"</p>"+	
			 		"</div>");
				
			}
			else{
				$('#gameListSection').html("");
				giochi.games.forEach(game=>addGame(game));
			}
			gameCount = responseData.gamesCount;
			console.log(gameCount);
			if(gameCount > 0 ){
				$('#gameListSection').append("<div class=pageDiv>")
				for(let i = 1; i<gameCount/10+1; i++){
					if(i == page){
						$('#gameListSection').append("<button type=button class='selectedPageButton pageButton'>"+i+"</button>")

					} else{
						$('#gameListSection').append("<button type=button class=pageButton onclick='updateCatalog("+i+")'>"+i+"</button>")
					}
				}
				$('#gameListSection').append("</div>")
				
			}

			
		})
	}
	
	function addGame(game){
		$('#gameListSection').append("<div class=gameDiv><p class=gameImage>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +"><img src='RetrieveGameImageServlet?gameId="+game.id +"&role=BANNER' alt='game logo'> </a><span class=releaseDate>"+game.releaseDate +"</span>"+	
			 			"</p>"+
			 			"<p class=gamePrice>"+
			 				"<span class= price>"+USDollar.format(game.price/100) +"</span><br>"+
			 			"</p>"+
			 			"<p class=gameTitle>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +">"+game.name +"</a><br><span class=description>"+game.shortDescription+"</span"+
			 			"</p>"+	
			 		"</div>");
	}
