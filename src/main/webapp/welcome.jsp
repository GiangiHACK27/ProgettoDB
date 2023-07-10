<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User"
    import="model.User.Role"
    %>

    
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<meta charset="ISO-8859-1">
		
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<link rel="stylesheet" href="./CSS/WelcomeStyle.css">
				
		<title>Welcome to Gaming World!</title>
	</head>
	<%User user = (User)session.getAttribute("user");%>
	
	<body>
	<jsp:include page="BasePageHeader.jsp"></jsp:include>
	
	<section class=main>
	<main>
		<div id = welcomeDiv>
		
			<h1>Discover a world of games</h1>
			Welcome to GamingWorld, your ultimate destination for all things gaming!
			Whether you're a casual gamer looking for an entertaining escape or a hardcore
			enthusiast seeking the latest and greatest titles, we've got you covered. 			
		</div>
		<div id=buttonDiv>
			<a href="Catalog.jsp"><button>Start shopping</button></a>
		</div>
		
	</main>
	</section>
		<jsp:include page="BasePageFooter.jsp"></jsp:include>
</html>