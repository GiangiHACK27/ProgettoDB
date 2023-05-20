<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.User" import="model.User.Role"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/BaseStyle.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/HeaderStyle.css">

<header class=pageHeader>

	<div id="logo">
		<a href="${pageContext.request.contextPath}"><img
			src="${pageContext.request.contextPath}/logo.png" /> </a>
	</div>
	<nav>
		<ul>
			<li class=dropdown><a href="${pageContext.request.contextPath}"> STORE </a>
				<div class=dropdown-content id=store-div>
					<ul>
						<li><a href= "${pageContext.request.contextPath}/"> HOME PAGE </a></li>
						<li><a href= "${pageContext.request.contextPath}/SearchGames"> CATALOG </a></li>
					</ul>
				</div></li>
			<% User user = (User) session.getAttribute("user"); %>
			<% if (user == null) { %>
			<li class=dropdown><a href="${pageContext.request.contextPath}/Login.jsp"> SIGN IN </a>
				<div class=dropdown-content id=sign-in-div>
					<ul>
						<li id=sign-in><a href="${pageContext.request.contextPath}/Login.jsp"> LOG IN</a></li>
						<li><a href="${pageContext.request.contextPath}/Register.jsp"> SIGN UP</a></li>
					</ul>
				</div></li>
			<% } else { %>
			<li class=dropdown><a
				href="${pageContext.request.contextPath}/user/PersonalArea.jsp"><%=user.getUsername().toUpperCase()%>
			</a>
				<div class=dropdown-content>
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/user/PersonalArea.jsp">PERSONAL &nbsp;AREA</a></li>
						<li><a
							href="${pageContext.request.contextPath}/user/LogoutServlet">LOGOUT</a></li>
					</ul>
				</div></li>
			<% } %>

		</ul>
	</nav>
</header>