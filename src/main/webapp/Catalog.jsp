

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Category"
    import="java.util.List"
    import="model.Game"
    import="model.Game.Pegi"
    %>
<!DOCTYPE html>
<html lang = en>
	<head>
		<link rel="stylesheet" href="./CSS/CatalogStyle.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src=./Scripts/CatalogScript.js></script>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<title>Insert title here</title>
	</head>
	<body>
	<jsp:include page="BasePageHeader.jsp"></jsp:include>
	<section class=main>
		<main>
		<form action="/GamingWorldShop/SearchGames" method="get">
		
		<section id=searchSection>
			<div id=headingDiv>
				<h1>Our selection of games</h1>
			</div>
			
			<div id=searchDiv>
				<input type=text id=searchBar name=searchBar placeholder="Search for games by title">
			</div>
		</section>
		<section id=filterSection>
			<%int maxPrice =  (int) application.getAttribute("maxPrice");
			Integer currentMaxPrice = maxPrice;
			String t = request.getParameter("currentMaxPrice");
			if (t != null)
				currentMaxPrice = Integer.parseInt(t);
			
			String[] alreadyCheckedCategories = request.getParameterValues("categories");
			
			int oldPegi = 18;
			t = request.getParameter("pegi");
			if(t != null)
				oldPegi = Integer.parseInt(t);
			%>
			
				<div id=categoryDiv>
					<% List<Category> categories = (List<Category>)request.getAttribute("categories");
						for(Category c : categories) { 
						
						boolean find = false;
						if(alreadyCheckedCategories != null)
							for(String checkedCategory : alreadyCheckedCategories) {
								if(checkedCategory.equals(c.getName()))
									find = true;
							}
						
						if(find) { %>
							<div class=singleCategory>
							<input id="<%= c.getName()%>" type="checkbox" name="categories" value="<%= c.getName()%>" checked>
						
						<% } else { %> 
							<div class=singleCategory>
							<input id="<%= c.getName()%>" type="checkbox" name="categories" value="<%= c.getName()%>">
						<% } %>
	
							<label for="<%= c.getName()%>"> <%= c.getName()%> </label>
							</div>
					<% 
						}
					%>
				</div>
				<div id = priceRangeDiv>
					Price range:<br> 0
					<input type="range" id=range name="currentMaxPrice" min="0" max="<%= maxPrice%>" value="<%= currentMaxPrice%>"
					 oninput= "rangeOutput.value=formatPrice(range.value)"><output id = rangeOutput><%= maxPrice%></output>
					 
				</div>
				
				<div id = pegiDiv>
				Age limit:
				<select name="pegi">
					<% Pegi[] pegis = Pegi.values();  
						for(Pegi p : pegis) { 
							if(p.getValue() == oldPegi) {
								%>
								<option value="<%= p.getValue()%>" selected><%=p.toString().replace('_', ' ') %></option>
							<% } else {%>
								<option value="<%= p.getValue()%>"><%=p.toString().replace('_', ' ') %></option>		
					<% } }
					%>
				</select>	
				</div>
				<div id = submitDiv>
				<input type="submit" value="Submit" name=button id=submitButton> 
				</div>
		</section>
		<section id=gameListSection>
 		<%-- <% List<Game> games = (List<Game>)request.getAttribute("games");
			for(Game g : games) {
				out.println(g.getName());
			}
		%> --%> 
			
		</section>
					</form>
		
		</main>
	</section>
	
	<jsp:include page="BasePageFooter.jsp"></jsp:include>
	</body>
</html>