<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.User"%>
<!DOCTYPE html>
<html lang= "en">
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/UpdateUserStyle.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js" 
		integrity = "sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="></script>
		<script src="../Scripts/UpdateUserScript.js"defer></script>
		
		<title>Update credentials</title>
	</head>
	<% User user = (User) session.getAttribute("user"); %>
	
	<body>
	
	<jsp:include page="../BasePageHeader.jsp"></jsp:include>
	
	<section class=main>

		<main>
			<div id=centerDiv>
			
				<h1>
				Update your credentials
				</h1>
				
				<div id=formDiv>
					<form method="post" action="/GamingWorldShop/UpdateUserServlet" id=inputForm>					
						<div id=emailDiv class=inputDiv>	
							<label for=email>Email:</label>		
							
							<input type=email id=email name="email" required pattern="[a-z0-9]+@[a-z]+\.[a-z]{2,3}"
							onchange="validateFormElem(this, document.getElementById('errorDiv'), emailErrorMessage)" value = "<%=user.getEmail()%>" >
						</div>
						
						<div id=passwordDiv class=inputDiv>
							<label for=password>Password:</label>
							
							<input type="password" name="password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,30}$"
							onchange="validateFormElem(this, document.getElementById('errorDiv'), passwordErrorMessage)"
						 	value="">
						</div>
						
						<div id=errorDiv></div>
						<div id=submitDiv class=inputDiv>
						<input type="submit" name="submit" id=submitButton value="Confirm">
						</div>
					</form>
				</div>
				
			</div>
		</main>
		<% String logError = (String)request.getAttribute("logError");
		  if(logError != null) { %>
			<p>Errore: <%=  logError%></p>
		<%} %>
	</section>
	
	<jsp:include page="../BasePageFooter.jsp"></jsp:include>
	</body>
</html>