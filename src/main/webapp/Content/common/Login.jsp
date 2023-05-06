<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../CSS/style.css">
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
	
	<div class="form">
		<form method="post" action="/GamingWorldShop/common/LoginServlet">
			
			<div class="formContent">
				user: <input type="text" name="username" value="<%= oldUser%>">
				<br>
				password: <input type="password" name="password" value="<%= oldPassword%>">
				<br>
				<input type="submit" name="submit" value="SUMMITTA">
				<br>
			</div>
		</form>
	</div>
	
	<% String logError = (String)request.getAttribute("logError");
	  if(logError != null) { %>
		<p>Errore: <%=  logError%></p>
	<%} %>
	</body>
</html>