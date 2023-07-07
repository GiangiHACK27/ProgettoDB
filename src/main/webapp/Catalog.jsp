<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Category"
    import="java.util.List"
    import="model.Game"
    import="model.Game.Pegi"
    import="model.User"
    %>
<!DOCTYPE html>
<html lang = en>
	<head>		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src=./Scripts/CatalogScript.js></script>
		
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<link rel="stylesheet" href="./CSS/CatalogStyle.css">
		
		<title>Catalog</title>
	</head>
	<body>
	
	<jsp:include page="BasePageHeader.jsp"></jsp:include>
 
 	<jsp:include page="RetrieveAllCategories"></jsp:include>
 	
	<section class=main>
		<main>

 		<form>		
		<section id=searchSection>
			<div id=headingDiv>
				<h1>Our selection of games</h1>
			</div>
			
			<div id=searchDiv>
				<input type=text id=searchBar name=searchBar placeholder="Search for games by title" value="">
			</div>
		</section>
		<section id=filterSection>
			<%int maxPrice =  (int) application.getAttribute("maxPrice");
			
			User user = (User)session.getAttribute("user");
			if(user != null && user.getRole().equals(User.Role.ADMIN))
				maxPrice = (int) application.getAttribute("maxPriceUnlisted");
						
			Integer currentMaxPrice = maxPrice;
			
			String[] alreadyCheckedCategories = request.getParameterValues("categories");
			
			int startPegi = 18;
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
							if(p.getValue() == startPegi) {
								%>
								<option value="<%= p.getValue()%>" selected><%=p.toString().replace('_', ' ') %></option>
							<% } else {%>
								<option value="<%= p.getValue()%>"><%=p.toString().replace('_', ' ') %></option>		
					<% } }
					%>
				</select>	
				</div>
				<div id=sortDiv>
					<select name = sort>
						<option value = "name">Title(A to Z)</option>
						<option value = "name DESC">Title(Z to A)</option>
						<option value = "releaseDate">Release date(from newest)</option>
						<option value = "releaseDate DESC">Release date(from oldest)</option>
						<option value = "price">Price(from lowest)</option>
						<option value = "price DESC">Price(from highest)</option>
					</select>
				</div>
		</section>
		<section id=gameListSection>


		</section>
		</form>
		
		</main>
	</section>
	
	<jsp:include page="BasePageFooter.jsp"></jsp:include>
	</body>
</html>