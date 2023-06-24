<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	
		<meta name="viewport" content="initial-scale=1, width=device-width">
	
		<link rel="stylesheet" href="./../CSS/BaseStyle.css">
	
		<title>Insert title here</title>
	</head>
	
	<body>
		<jsp:include page = "../BasePageHeader.jsp"></jsp:include>
	
		<% String from = request.getParameter("from"); 
		String servletToCall = "PurchaseFromCartServlet";
		if(from != null && from.equals("personalGamePage")) {
			String gameId = request.getParameter("gameId");
			servletToCall = "AddPurchaseGameServlet?gameId=" + gameId;
		}
		%>

		<section class = main>
			<main>
				<form method = "POST" action = "<%= servletToCall%>">
					<label for = "card_number">Card number</label>
					<input type = "text" name = "card_number" required></input>
					<br>
				
					<input type = "submit" value = "buy"></input>
				</form>
			</main>
		</section>
	
		<jsp:include page = "../BasePageFooter.jsp"></jsp:include>

	</body>
</html>