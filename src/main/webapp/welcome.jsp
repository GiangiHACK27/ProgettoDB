
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


		<div>		
			<%if(user != null){ %>
				<h1>Logged in as <%=user.getUsername()%></h1>
			<%} %>		
			<a href="/GamingWorldShop/SearchGames">Check out our games!</a>
		</div>
</html>