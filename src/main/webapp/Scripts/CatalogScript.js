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
	
	//load the catalog when the document is ready 
	updateCatalog(1);
	//load the catalog when the document is ready 
})

	function updateCatalog(page){
		//convert form to array
		var data = $('form').serializeArray(); 
		//convert form to array
		
		//add the page value to the data to send to server
		data.push({name: "page", value: page})
		//add the page value to the data to send to server
		
		//Send GET response to servlet to retrieve games
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
			
			//add pagination based on number of games found
			gameCount = responseData.gamesCount;
			if(gameCount > 0 ){
				$('#gameListSection').append("<div class=pageDiv>")
				for(let i = 1; i<gameCount/10+1; i++){//i is going to count from 1 to the total number of pages
					if(i == page){
						//the button referring to the current page is non-selectable
						$('#gameListSection').append("<button type=button class='selectedPageButton pageButton'>"+i+"</button>")
						//the button referring to the current page is non-selectable
					} else{
						$('#gameListSection').append("<button type=button class=pageButton onclick='updateCatalog("+i+")'>"+i+"</button>")
					}
				}
				$('#gameListSection').append("</div>")	
			}
			//add pagination based on number of games found	
		})
		//Send GET response to servlet to retrieve games
	}
	
	function addGame(game){
		//add a div containing all game information to the list
		$('#gameListSection').append("<div class=gameDiv><p class=gameImage>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +"><img src='RetrieveGameImageServlet?gameId="+game.id +"&role=BANNER' alt='game logo'> </a><span class=releaseDate>"+game.releaseDate +"</span>"+	
			 			"</p>"+
			 			"<p class=gamePrice>"+
			 				"<span class= price>"+USDollar.format(game.price/100) +"</span><br>"+
			 			"</p>"+
			 			"<p class=gameTitle>"+
			 				"<a href=PersonalGamePage.jsp?gameId="+game.id +"> <span class=emptySpan></span>"+game.name +"</a><br><span class=description>"+game.shortDescription+"</span"+
			 			"</p>"+	
			 		"</div>");
		//add a div containing all game information to the list
	}
	
