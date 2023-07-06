<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    
    import = "java.util.List, model.Game"
    %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/GameListStyle.css">
		<link rel="stylesheet" href="../CSS/CartStyle.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src="../Scripts/WishlistScript.js" defer></script>
		
		<title>Insert title here</title>
	</head>
	
	<body>

		<jsp:include page="../BasePageHeader.jsp"></jsp:include>
		
		<jsp:include page="../ViewCartServlet?category=wishlist"></jsp:include>
		
			<section class=main>
	<main>
		
		<section id=gameListContents>
			<h1 id=gameListTitle>Your wishlist</h1>
			
			 <% List<Game> games = (List<Game>)request.getAttribute("wishlistItems");
			  
				 if(games.isEmpty()){ %>
			 	<p id=emptyGameListTitle>Your wishlist is currently empty. <a href=${pageContext.request.contextPath}/Catalog.jsp>Let's fix that!</a></p>
			 <%}else{ %>
			 
			 	<section id=gameSection>
			 	<%for (Game currentGame:games){ %>
			 		<div class=gameDiv>
			 			<p class=gameImage>
			 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><img src="RetrieveGameImageServlet?gameId=<%=currentGame.getId()%>&role=BANNER" alt="game logo"> </a>	
			 			</p>
			 			<p class=gamePrice>
			 				<span class= price><%=currentGame.getPrice()%></span><br>
			 				<span class= removeButton><a id=<%=currentGame.getId()%>>Remove</a></span>
			 			</p>
			 			<p class=gameTitle>
			 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><%=currentGame.getName()%></a>
			 			</p>
	
			 		</div>
			 		
			 	<%} %>
			 	</section>
			 <%} %>
		</section>
		<%if(!games.isEmpty()) { %>

		<section id=options>			
			<div id=emptyCartOption>
				<span id=emptyCartButton> <a>Empty wishlist</a></span>
			</div>
		</section>
		<%} %>
		
	</main>
	
	</section>
		
		
		
		<jsp:include page="../BasePageFooter.jsp"></jsp:include>

	</body>
</html>