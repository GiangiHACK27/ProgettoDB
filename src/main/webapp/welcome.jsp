<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		<title>Welcome to Gaming World!</title>
	</head>
	
	<body>

<%if(request.getSession().getAttribute("user") != null){ %>
You are logged in
<%} %>
		
		<form action="/GamingWorldShop/ImageUploadServlet" method="post" enctype="multipart/form-data">
			<fieldset> <legend>Test image upload</legend> 
				ID: <input type="text" name="id"> <br>
				Select file: <input type="file" name="raw"> <br>
				Alt text: <input type="text" name="altText"> <br>
				<input type="submit" name="summitta"> <br>
			</fieldset>
			
		</form>
		
		<form action="/GamingWorldShop/ImageGetterServlet" method ="post" >
			<fieldset> <legend>Test image visualization</legend> 
				ID: <input type="text" name="id"> <br>
				<input type="submit" name="summitta" value="Test image view"> <br>
			</fieldset>
		</form>
		
		<a href="/GamingWorldShop/Login.jsp">Login</a>
		<form action="/GamingWorldShop/user/LogoutServlet" method="GET">
			<button type="submit">Logout</button>
		</form>
		<a href="/GamingWorldShop/Register.jsp">Register</a><br>
		
		<form action="/GamingWorldShop/SearchGames" method ="GET" >
			<input type="submit" name="summitta" value="Check out our games!"> <br>
		</form>	</body>
</html>