<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import ="java.util.List, model.Game, model.Purchase, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">

		<link rel="stylesheet" href="../CSS/BaseStyle.css"> 
		<link rel="stylesheet" href="../CSS/GameListStyle.css">	
		<link rel="stylesheet" href="../CSS/GameLibrary.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src="../Scripts/GameLibrary.js" defer></script>
		
		<title>Game Library</title>
	</head>

	<body>
		
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>
		
		<jsp:include page="RetrieveGamePurchasedServlet"></jsp:include>
				
		<section class=main>
			<main>
				
			<section id=gameListContents>
				<h1 id=gameListTitle>Game Library</h1>
			
			 	<%  
			 	List<AbstractMap.SimpleEntry<Game, Purchase>> gamePurchased = (List<AbstractMap.SimpleEntry<Game, Purchase>>)request.getAttribute("gamePurchased");
			 	
					 if(gamePurchased.isEmpty()) { %>
			 		<p id=emptyGameListTitle>Your Library is empty. <a href=${pageContext.request.contextPath}/Catalog.jsp>Let's fix that!</a></p>
			 	<%} else { %>
			 
			 	<section id=gameSection>
			 	<%for (AbstractMap.SimpleEntry<Game, Purchase> entry : gamePurchased) { 
			 		Game currentGame = (Game)entry.getKey();
			 		Purchase purchase = (Purchase)entry.getValue();
			 	%>
			 		<div class=gameDiv>
			 			<p class=gameImage>
			 				<a href=${pageContext.request.contextPath}/PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><img src="${pageContext.request.contextPath}/RetrieveGameImageServlet?gameId=<%=currentGame.getId()%>&role=BANNER" alt="game logo"> </a>	
			 			</p>
			 			
			 			<p class=gamePrice>
			 					Purchased for: <span class = price><%=purchase.getPrice()%></span>
			 			</p>
			 					 			
			 			<p class=gameTitle>
			 				<a href=${pageContext.request.contextPath}/PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><%=currentGame.getName()%></a>
			 					 				
			 				<p class="gameDate">
			 					Purchased on: <%= purchase.getDatePurchased()%>		
			 				</p>
			 			</p>
			 			
			 		</div>
			 	<%} %>
			 	</section>
			 <%} %>
			</section>
				
			</main>
		</section>
				
		<jsp:include page="../BasePageFooter.jsp"></jsp:include>
		
	</body>
</html>