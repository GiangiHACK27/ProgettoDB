<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign up to Gaming World</title>
		</head>
	<body>
		
	<% String usernameOld = request.getParameter("username");
	   if(usernameOld == null)
		   usernameOld = "";
	   String emailOld = request.getParameter("email");
	   if(emailOld == null)
		   emailOld = "";
	%>
	
	<form method="post" action="/GamingWorldShop/common/RegisterServlet">
		username: <input type="text" name="username" value="<%= usernameOld%>">
		<br>
		email: <input type=text name="email" value = "<%=emailOld %>" >
		<br>
		password: <input type="password" name="password" value="">
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