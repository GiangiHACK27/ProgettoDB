<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../CSS/style.css">
		<title>Login Page</title>
	</head>
	
	<% String oldUser = request.getParameter("username");
	   if(oldUser == null)
		   oldUser = "";
	   String oldPassword = request.getParameter("password");
	   if(oldPassword == null)
		   oldPassword = "";
	%>

<!-- 	
	<body id="loginPage">
        <header>
            <div id="headerContent">
                <a href=""><img id="textLogoLogin" src="GamingWorld/Content/images/Steam_icon_logo.svg.png"></a>

                <nav>
                    <ul>
                        <li><a href=""> HOME </a></li>
                        <li><a href=""> LOGIN </a></li>
                        <li><a href=""> REGISTER </a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <div class="form">
            <img id="logoLogin" src="GamingWorld/Content/images/Steam_icon_logo.svg.png" alt="logo" >
    
            <form method="post" action="/GamingWorldShop/common/LoginServlet">
                
                <div class="formContent">
                    <label for="username">INSERT USERNAME</label>
                    <br>
                    <input id="username" class="fieldtext" type="text" name="username" value="<%= oldUser%>" required>
                    <br>
                    <label for="password">INSERT PASSWORD</label>
                    <br>
                    <input id="password" class="fieldtext" type="password" name="password" value="<%= oldPassword%>" required>
                    <br>
                    <input type="submit" class="button" id="login" name="submit" value="Login">
                    <br>
                    <a id="alternativeLogin" href="">Can't login?click here</a>
                    <br>
                    
                    <% String logError = (String)request.getAttribute("logError");
	  					if(logError != null) { %>
						<p>Errore: <%=  logError%></p>
					<%} %>	
                </div>
            </form>
        </div>
	
        <footer></footer>

	</body>
	 -->
	 
	 <body id="loginPage">
	
        <header>
            <div id="headerContent">
                <a href=""><img id="textLogoLogin" src="../images/Steam_icon_logo.svg.png"></a>

                <nav>
                    <ul>
                        <li><a href=""> HOME </a></li>
                        <li><a href=""> LOGIN </a></li>
                        <li><a href=""> REGISTER </a></li>
                    </ul>
                </nav>
            </div>
        </header>

        <div class="form">
            <img id="logoLogin" src="../images/Steam_icon_logo.svg.png" alt="logo" >
    
            <form method="post" action="/GamingWorldShop/common/LoginServlet">
                
                <div class="formContent">
                    <label for="username">INSERT USERNAME</label>
                    <br>
                    <input id="username" class="fieldtext" type="text" name="username" value="" required>
                    <br>
                    <label for="password">INSERT PASSWORD</label>
                    <br>
                    <input id="password" class="fieldtext" type="password" name="password" value="" required>
                    <br>
                    <input type="submit" class="button" id="login" name="submit" value="Login">
                    <br>
                    <a id="alternativeLogin" href="">Can't login?click here</a>
                </div>
            </form>
        </div>
	
        <footer></footer>

	</body>
	
</html>