<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.User"
    import="model.User.Role"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/BaseStyle.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderStyle.css">

<header class=pageHeader>
		<nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}"> STORE </a></li>
				
					<%User user = (User)session.getAttribute("user");%>
					<%if(user == null ){ %>
					<li>
						<a href="${pageContext.request.contextPath}/Login.jsp"> LOGIN </a>
					</li>
					<%}else{ %>
					<li class = dropdown>
						<a><%=user.getUsername().toUpperCase() %> </a>
						<div class =dropdown-content>
							<ul>
								<li><a href="${pageContext.request.contextPath}/user/PersonalArea.jsp">PERSONAL AREA </a> </li>
								<%-- <li><a href="${pageContext.request.contextPath}/lOGOUT.JSP"> </li> doesn't work without button... --%>
							</ul>
						</div>
						
					</li>
					<%} %>
				
			</ul>
		</nav>
	</header>