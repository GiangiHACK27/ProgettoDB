<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload game</title>
</head>
<body>
<form action="/GamingWorldShop/admin/GameUploadServlet" method="post" enctype ="multipart/form-data">
			<fieldset> <legend>Upload your game</legend> 
			
				<label for=bannerImage>Upload banner image:</label>
				<input type="file" id =bannerImage name=bannerImage accept="image/jpeg, image/png, image/jpg"> <br>
				
				<label for=showcaseImages>Upload showcase images:</label>
				<input type="file" id =showcaseImages name=showcaseImages multiple = multiple accept="image/jpeg, image/png, image/jpg"> <br>
				
				Name: <input type="text" name="name"> <br>
				Description: <input type="text" name="description"> <br>
				Short description: <input type="text" name="shortDescription"> <br>
				Price: <input type="number" min="0" max="10000" step="1" name="price"/><br>
				State: <select name="state">
					  	<option value="RELEASED">Released</option>
					 	<option value="BETA">Beta</option>
						<option value="ALPHA">Alpha</option>
						<option value="COMING_SOON">Coming soon</option>
						<option value="UNLISTED">Unlisted</option>
					   </select> <br>
				Release date: <input type="date" name="releaseDate"
					       value="2023-05-12"
					       min="2023-05-12" max="2025-01-01">
					       


				
				
				Pegi: <select name="pegi">
					  	<option value="PEGI_3">3</option>
					 	<option value="PEGI_7">7</option>
						<option value="PEGI_12">12</option>
						<option value="PEGI_16">16</option>
						<option value="PEGI_18">18</option>
					   </select> <br>


				<input type="submit" name="summitta"> <br>
			</fieldset>
</form>
	<% String logError = (String)request.getAttribute("logError");
	  if(logError != null) { %>
		<p>Errore: <%=  logError%></p>
	<%} %>
</body>
</html>