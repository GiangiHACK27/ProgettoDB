<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
<!DOCTYPE html>
<html lang = en>
	<head>
			<meta name="viewport" content="initial-scale=1, width=device-width">
			<link rel="stylesheet" href="./CSS/BaseStyle.css">
			<link rel="stylesheet" href="./CSS/Success.css">
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
	
	<section class=main>
	<main>
		<div id=successDiv>
			<h1>Success!</h1>
			Your purchase has been registered. <a href="${pageContext.request.contextPath}/user/GameLibrary.jsp">View your games in your library</a>
		</div>
		

	</main>

	</section>
	<jsp:include page="BasePageFooter.jsp"></jsp:include>
	
	</body>
</html>