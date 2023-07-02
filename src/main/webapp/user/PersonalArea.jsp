<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User" %>
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/PersonalAreaStyle.css">
		<title>Personal Area</title>
	</head>
	<%
		User user = (User)session.getAttribute("user");
		String username = user.getUsername();
	%>
	<body>
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>
		<section class=main>
			<main>	
				<div id=centerDiv>
					<h1><%=username.substring(0, 1).toUpperCase() + username.substring(1) %>'s personal area </h1>	
					<%if(user.getRole().toString().equals("ADMIN")){ %>
					<div class=optionDiv id=uploadGameDiv>
						<a href=/GamingWorldShop/admin/GameUploadServlet>
							<span class=emptySpan></span>Upload a new game
						</a>		
					</div>
					<%} %>
					<div class=optionDiv id=viewLibraryDiv>
						<a href=/GamingWorldShop/user/GameLibrary.jsp>
							<span class=emptySpan></span>View your game library
						</a>	
					</div>
					<div class=optionDiv id=changeCredentialsDiv>
						<a href=/GamingWorldShop/user/UpdateUser.jsp>
							<span class=emptySpan></span>Change credentials(to do)
						</a>
					</div>
				</div>
			</main>
		</section>
		<jsp:include page="../BasePageFooter.jsp"></jsp:include>
	</body>
</html>