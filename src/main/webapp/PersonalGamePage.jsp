<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Game, model.SystemRequirement, java.util.*, model.User, model.Game.State, model.User.Role" %>
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<link rel="stylesheet" href="./CSS/PersonalGamePage.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src="./Scripts/PersonalGamePageScript.js" defer></script>
	
		<title>Personal Game Page</title>
	</head>

	<body>
		
		<!-- Retrieve info about the game -->
		<jsp:include page="/RetrieveGameServlet"></jsp:include>
		<jsp:include page="/RetrieveGameCategories"></jsp:include>
		<jsp:include page="/RetrieveGameRequirementsServlet"></jsp:include>
		
		<% Game game = (Game)request.getAttribute("game"); %>
		<% User user = (User) session.getAttribute("user"); %>
		<% List<Category> categories = (List<Category>) request.getAttribute("gameCategories"); %>
		<jsp:include page="/RetrieveGameStatusServlet?gameId=<%=game.getId()%>"></jsp:include>
		
		<%		boolean isInCart = (boolean)request.getAttribute("isInCart");
				boolean isInWishlist = (boolean)request.getAttribute("isInWishlist");
				boolean isBuyed = (boolean)request.getAttribute("isBuyed"); %>
		<!-- Retrieve info about the game -->
		
		<jsp:include page="BasePageHeader.jsp"></jsp:include>
		
		<section class=main>
		
		<main>
			<%if(user != null && user.getRole().equals(User.Role.ADMIN)){%>
				<button onclick="location.href='admin/UpdateGame.jsp?gameId=<%=game.getId()%>'" id=updateGameButton>Edit game</button>
			<%} %>
			<div id=row-1>
				<div id=sliderImages>
					<img id=imageSlider alt="showcase image" src="RetrieveGameImageServlet?gameId=<%=game.getId()%>&role=SHOWCASE">
				</div>
		
				<div id=shortDescription>
					<table>
						<tbody>
							<tr>
								<td>
									<img id=imageBanner alt="banner image" src="RetrieveGameImageServlet?gameId=<%=game.getId()%>&role=BANNER">
								</td>
							</tr>
						
							<tr>
								<td>									
									<p> <%=game.getShortDescription() %> </p>
								</td>
							</tr>
							
							<tr>
								<td>
									<span class="infoShortDescription">Publisher:</span> <%= game.getPublisher()%>
								</td>
							</tr>
							
							<tr>
								<td>
									<span class="infoShortDescription">State:</span> <%= game.getState().getValue().replace('_', ' ') %>
								</td>
							</tr>
							<tr>
								<td>
									<span class="infoShortDescription">Categories:</span> 
									<%for(Category c:categories){ %>
										<%=c.getName() %>
									 <%} %>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			
			<div id="row-2">
				<div id="description">
					<h1> <%= game.getName()%> </h1>
						<%if(!game.getState().equals(State.UNLISTED)){ %>
							<% if(! isBuyed) { 
								 if(! isInWishlist && user != null) { %>
								<button id="addToWishlistButton" onclick="addToWishlist(<%= game.getId() %>)">Add to wishlist</button>
							<% } %>
								
							  <% if(! isInCart) { %>
			 						<button id="addToCartButton" onclick="addToCart(<%= game.getId() %>)" >Add to cart</button>								
								<% } %>
							
								<button onclick="location.href='user/PurchaseRedirectServlet?from=personalGamePage&gameId=<%= game.getId()%>'">Buy: <span id="buyButton"><%= game.getPrice() %></span></button>
							<% }else{ %>
								<button onclick="location.href='user/GameLibrary.jsp'">View in library </button>
							<%} %>
						<%} %>
					<p> <%= game.getDescription()%> </p>
				</div>
				
				<% Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = (Map)request.getAttribute("requirements"); %>
				
				<div id="requirements">
					<div id="reqBar">
						<% 
						String firstNotEmptyOs = "";
						for(Map.Entry<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirements : requirementsMap.entrySet()) {
							String os = requirements.getKey().toString().toLowerCase();
							if(requirements.getValue().size() > 0) {
								if(firstNotEmptyOs.equals(""))
									firstNotEmptyOs = os;
						%>
								<button id="<%=os%>Button" class="reqButton" onclick="changeSchedeReq('<%=os%>')"><%= os.toUpperCase()%></button>
						<% 
							}
						}
					
						%>
					</div>
					
					<% 
						for(Map.Entry<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirements : requirementsMap.entrySet()) {
							String style = "";
							if(requirements.getKey().toString().toLowerCase().equals(firstNotEmptyOs))
								style = "display: block";
					%>
						<div id="<%=requirements.getKey().toString().toLowerCase()%>Schede" class="reqSchede" style="<%=style%>">					
					<% 
							for(SystemRequirement requirement : requirements.getValue()) {	
					%> 
								<p id="<%= requirements.getKey().toString().toLowerCase()%>"><%= requirement.getName()%>: <span id="rightText"><%=requirement.getValue() %></span></p> 
					<% 	} %>
						</div>
					<% 	
					}
					%>
				</div>
				<div id=pegi>
						<img src="./images/pegi<%=game.getPegi().getValue()%>.jpg" alt = pegi>
				</div>
			</div>
		</main>
	</section>
		<jsp:include page="BasePageFooter.jsp"></jsp:include>
	</body>
</html>