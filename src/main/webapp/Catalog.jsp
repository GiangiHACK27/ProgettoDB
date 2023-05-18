

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Category"
    import="java.util.List"
    import="model.Game"
    import="model.Game.Pegi"
    %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<title>Insert title here</title>
	</head>
	<body>
	
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
		
		<form action="/GamingWorldShop/SearchGames" method="get">
				<% List<Category> categories = (List<Category>)request.getAttribute("categories");
					for(Category c : categories) { 
					
					boolean find = false;
					if(alreadyCheckedCategories != null)
						for(String checkedCategory : alreadyCheckedCategories) {
							if(checkedCategory.equals(c.getName()))
								find = true;
						}
					
					if(find) { %>
						<input id="<%= c.getName()%>" type="checkbox" name="categories" value="<%= c.getName()%>" checked>
					
					<% } else { %> 
						<input id="<%= c.getName()%>" type="checkbox" name="categories" value="<%= c.getName()%>">
					<% } %>

					<label for="<%= c.getName()%>"> <%= c.getName()%> </label>
					<br>
				<% 
					}
				%>
			<input type="range" name="currentMaxPrice" min="0" max="<%= maxPrice%>" value="<%= currentMaxPrice%>">
			<br>
			<select name="pegi">
				<% Pegi[] pegis = Pegi.values();  
					for(Pegi p : pegis) { 
						if(p.getValue() == oldPegi) {
							%>
							<option value="<%= p.getValue()%>" selected><%=p %></option>
						<% } else {%>
							<option value="<%= p.getValue()%>"><%=p %></option>		
				<% } }
				%>
			</select>
				
			<input type="submit" value="summitta"> 
		</form>
		
 		<% List<Game> games = (List<Game>)request.getAttribute("games");
			for(Game g : games) {
				out.println(g.getName());
			}
		%> 

	</body>
</html>