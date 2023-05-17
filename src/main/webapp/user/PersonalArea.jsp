<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Personal Area</title>
	</head>
	<body>
		<%User user = (User)session.getAttribute("user");
		String username = user.getUsername();
		%>
		
		<h1>Welcome in you personal area: <%=username %> </h1>
		
				<%if(user.getRole().toString().equals("ADMIN")){ %>
		<form action="/GamingWorldShop/admin/GameUploadServlet" method ="GET" >
			<input type="submit" name="summitta" value="Upload game"> <br>
		</form>
		<%} %>
	</body>
</html>