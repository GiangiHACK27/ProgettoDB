<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.Cart, java.util.*, model.Game"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1, width=device-width">
<link rel="stylesheet" href="./CSS/BaseStyle.css">
<link rel="stylesheet" href="./CSS/CartStyle.css">
<script src="./Scripts/CartScript.js" defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<title>Cart</title>
</head>
<body>
<jsp:include page="BasePageHeader.jsp"></jsp:include>
<jsp:include page="/ViewCartServlet"></jsp:include>
	<section class=main>
	<main>
		<h1 id=cartTitle>Your shopping cart</h1>
		
		 <% List<Game> games = (List<Game>)request.getAttribute("cartItems");
			games.forEach(e->System.out.println(e.getName()));

		 %> 
		 <%if(games.isEmpty()){ %>
		 	Your cart is currently empty. <a href=${pageContext.request.contextPath}/SearchGames>Let's fix that!</a>
		 <%}else{ %>
		 
		 	<section id=gameSection>
		 	<%for (Game currentGame:games){ %>
		 		<div class=gameDiv>
		 			<p class=gameImage>
		 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><img src=${pageContext.request.contextPath}/images/logo.png alt=a> </a>	
		 			</p>
		 			<p class=gamePrice>
		 				<span class= price><%=currentGame.getPrice()%></span><br>
		 				<span class=removeButton><a id=<%=currentGame.getId()%>>Remove</a></span>
		 			</p>
		 			<p class=gameTitle>
		 				<a href=PersonalGamePage.jsp?gameId=<%=currentGame.getId()%>><%=currentGame.getName()%></a>
		 			</p>

		 		</div>
		 	</section>
		 		
		 	<%} %>
		 <%} %>
	</main>
	</section>
	
<jsp:include page="BasePageFooter.jsp"></jsp:include>

</body>
</html>