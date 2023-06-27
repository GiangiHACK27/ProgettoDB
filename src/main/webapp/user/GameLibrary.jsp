<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import ="java.util.List, model.Game"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">

		<link rel="stylesheet" href="../CSS/BaseStyle.css"> 
		<link rel="stylesheet" href="../CSS/GameListStyle.css">		
		
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
			 	List<Game> gamePurchased = (List<Game>)request.getAttribute("gamePurchased");
			 	
					 if(gamePurchased.isEmpty()) { %>
			 		<p id=emptyGameListTitle>Your Library is empty. <a href=${pageContext.request.contextPath}/Catalog.jsp>Let's fix that!</a></p>
			 	<%} else { %>
			 
			 	<section id=gameSection>
			 	<%for (Game currentGame : gamePurchased){ %>
			 		<div class=gameDiv>
			 			<p class=gameImage>
			 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><img src="RetrieveGameImageServlet?gameId=<%=currentGame.getId()%>&role=BANNER" alt="game logo"> </a>	
			 			</p>
			 			<p class=gameTitle>
			 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><%=currentGame.getName()%></a>
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