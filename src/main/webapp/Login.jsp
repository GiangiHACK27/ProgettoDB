<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="org.owasp.encoder.Encode"
    %>
<!DOCTYPE html>
<html lang = en>
	<head>
			<meta name="viewport" content="initial-scale=1, width=device-width">
			<link rel="stylesheet" href="./CSS/BaseStyle.css">
			<link rel="stylesheet" href="./CSS/Login.css">
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
		<h1>
			SIGN IN
		</h1>
		<div id=loginDiv>
			<form method="post" action="/GamingWorldShop/LoginServlet">
				<div id=usernameDiv class=inputDiv>
					<label for=username> Username</label>
					<input type="text" name="username" id=username required value="<%= Encode.forHtmlAttribute(oldUser)%>">
				</div>
				<div id=passwordDiv class=inputDiv>
					<label for=password>Password</label>
					<input type="password" name="password" id=password required value="<%= Encode.forHtmlAttribute(oldPassword)%>">
				</div>
				<div id=submitDiv class=submitDiv>
					<input type="submit" name="submit" value="Login" id=submit>
				</div>
				<% String logError = (String)request.getAttribute("logError");
					if(logError != null) { %>
				<div id=errorDiv class=errorDiv>
							<p>Error: <%=  Encode.forHtmlContent(logError)%></p>

				</div>
						<%} %>
			</form>
		</div>
		
		<div id=infoDiv>
			GamingWorld is a relatively HUGE community of GAMERS and gaming related activities. Find MILLIONS of games and gamer friends on Gaming World
			and discover an ENTIRELY NEW community of GAMING.<br>
			Not a part of the community yet? What are you waiting for? <a href=Register.jsp>Join us!</a>
		</div>
	</main>

	</section>
	<jsp:include page="BasePageFooter.jsp"></jsp:include>
	
	</body>
</html>