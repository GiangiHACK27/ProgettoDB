<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Content/CSS/style.css">
		<title>Welcome to Gaming World!</title>
	</head>
	
	<body>

<%if(request.getSession().getAttribute("user") != null){ %>
You are logged in
<%} %>
		
		<form action="/GamingWorldShop/common/ImageUploadServlet" method="post" enctype="multipart/form-data">
			<fieldset> <legend>Test image upload</legend> 
				ID: <input type="text" name="id"> <br>
				Select file: <input type="file" name="raw"> <br>
				Alt text: <input type="text" name="altText"> <br>
				<input type="submit" name="summitta"> <br>
			</fieldset>
			
		</form>
		
		<form action="/GamingWorldShop/common/ImageGetterServlet" method ="post" >
			<fieldset> <legend>Test image visualization</legend> 
				ID: <input type="text" name="id"> <br>
				<input type="submit" name="summitta" value="Test image view"> <br>
			</fieldset>
		</form>
		
		<a href="/GamingWorldShop/Content/common/Login.jsp">Login</a>
		<form action="/GamingWorldShop//user/LogoutServlet" method="GET">
			<button type="submit">Logout</button>
		</form>
		<a href="/GamingWorldShop/Content/common/Register.jsp">Register</a><br>
	</body>
</html>