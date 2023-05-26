
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<!DOCTYPE html>
<html lang = en>
	<head>
			<meta name="viewport" content="initial-scale=1, width=device-width">
			<link rel="stylesheet" href="./CSS/BaseStyle.css">
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
	
	<jsp:include page="BasePageHeader.jsp"></jsp:include>
	
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