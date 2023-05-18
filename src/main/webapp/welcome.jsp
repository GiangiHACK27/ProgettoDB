
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User"
    import="model.User.Role"
    %>

    
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta name="viewport" content="initial-scale=1, width=device-width">
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<meta charset="ISO-8859-1">
		<title>Welcome to Gaming World!</title>
	</head>
	
	<body>
	<jsp:include page="BasePageHeader.jsp"></jsp:include>
	<%User user = (User)session.getAttribute("user");%>

	<%if(user != null){ %>
		Logged in as <%=user.getUsername()%>
	<%} %>

		<form action="/GamingWorldShop/user/LogoutServlet" method="GET">
			<button type="submit">Logout</button>
		</form>
		<a href="/GamingWorldShop/Register.jsp">Register</a><br>
		
		<form action="/GamingWorldShop/SearchGames" method ="GET" >
			<input type="submit" name="summitta" value="Check out our games!"> <br>
		</form>	</body>
</html>