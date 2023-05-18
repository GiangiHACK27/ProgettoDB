<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
			<meta name="viewport" content="initial-scale=1, width=device-width">
			<link rel="stylesheet" href="./CSS/Login.css">
		<title>Login Page</title>
		</head>
	<body>
		
	<% String oldUser = request.getParameter("username");
	   if(oldUser == null)
		   oldUser = "";
	   String oldPassword = request.getParameter("password");
	   if(oldPassword == null)
		   oldPassword = "";
	%>
	
	<header class=pageHeader>
		<nav>
			<ul>
				<li>Home</li>
				<li>Personal Area</li>
			</ul>
		</nav>
	</header>
	
	<div id=parent>
	<div id=loginForm>
		<form method="post" action="/GamingWorldShop/LoginServlet">
			user: <input type="text" name="username" value="<%= oldUser%>">
			<br>
			password: <input type="password" name="password" value="<%= oldPassword%>">
			<br>
			<input type="submit" name="submit" value="SUMMITTA">
			<br>
		</form>
	</div>
	</div>
	<% String logError = (String)request.getAttribute("logError");
	  if(logError != null) { %>
		<p>Errore: <%=  logError%></p>
	<%} %>
	</body>
</html>