<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User" %>
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<title>Personal Area</title>
	</head>
	<body>
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>
	<section class=main>
		<%User user = (User)session.getAttribute("user");
		String username = user.getUsername();
		%>
		
		<h1>Welcome to your personal area: <%=username %> </h1>
		
		
		<div>
		<%if(user.getRole().toString().equals("ADMIN")){ %>
			<a href=/GamingWorldShop/admin/GameUploadServlet>Upload a new game</a>
		<%} %>
		</div>
			</section>
	<jsp:include page="../BasePageFooter.jsp"></jsp:include>
	</body>
</html>