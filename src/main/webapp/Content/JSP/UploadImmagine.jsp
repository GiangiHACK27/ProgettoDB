<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Upload an immagine</title>
	</head>
	
	<body>
		
		<form method="post" action="ImmagineUploadServlet" enctype="multipart/form-data">
			<fieldset>
				ID: <input type="text" name="id"> <br>
				Select the file: <input type="file" name="raw"> <br>
				Testo alternativo: <input type="text" name="testoAlternativo"> <br>
				<input type="submit" name="summitta"> <br>
				<input type="reset" name="reset"> <br>
			</fieldset>
		</form>
		
	</body>
</html>