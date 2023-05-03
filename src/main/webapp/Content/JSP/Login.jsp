<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
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
	
	<form method="post" action="/GamingWorldShop/LoginServlet">
		user: <input type="text" name="username" value="<%= oldUser%>">
		<br>
		password: <input type="password" name="password" value="<%= oldPassword%>">
		<br>
		<input type="submit" name="submit" value="SUMMITTA">
		<br>
	</form>
	
	<% String logError = (String)request.getAttribute("logError");
	  if(logError != null) { %>
		<p>Errore: <%=  logError%></p>
	<%} %>
	</body>
</html>