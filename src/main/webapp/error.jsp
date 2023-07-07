<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/BaseStyle.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ErrorStyle.css">
		
		<title>Error</title>
	</head>
	<body>
		<jsp:include page="BasePageHeader.jsp"></jsp:include>

		<section class=main>
			<main>
				<%if(response.getStatus() == 405){ %>
					You do not have the necessary permits do view this page. <a href="${pageContext.request.contextPath}">Return to home page</a>
				<%}else{ %>
					A fatal error has occurred while processing your request. <a href="${pageContext.request.contextPath}">Return to home page</a>
				<%} %>
				
				<%=exception.getMessage() %>
			</main>
		</section>
	
	<jsp:include page="BasePageFooter.jsp"></jsp:include>

	</body>
</html>