<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="model.Game, model.SystemRequirement, java.util.*" %>
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="./CSS/BaseStyle.css">
		<link rel="stylesheet" href="./CSS/PersonalGamePage.css">
		
		<script src="./Scripts/PersonalGamePageScript.js" defer></script>
	
		<title>Personal Game Page</title>
	</head>

	<body>
		
		<!-- Retrieve info about the game -->
		<jsp:include page="/RetrieveGameServlet"></jsp:include>
		<!-- Retrieve info about the game -->
		
		<% Game game = (Game)request.getAttribute("game"); %>
		
		<jsp:include page="BasePageHeader.jsp"></jsp:include>
		
		<section class=main>
		
		<main>
			<div id=row-1>
				<div id=sliderImages>
					<img id=imageSlider src="RetrieveGameImageServlet?gameId=<%=game.getId()%>&role=SHOWCASE">
				</div>
		
				<div id=shortDescription>
					<table>
						<tbody>
							<tr>
								<td>
									<img id=imageBanner src="RetrieveGameImageServlet?gameId=<%=game.getId()%>&role=BANNER">
								</td>
							</tr>
						
							<tr>
								<td>									
									<p> <%=game.getShortDescription() %> </p>
								</td>
							</tr>
							
							<tr> 
								<td>
									<p>Valutazioni recenti: </p>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			
			<div id="row-2">
				<div id="description">
					<h1> <%=game.getName() %> </h1>
					
					<button onclick="location.href='user/Purchase.jsp?from=personalGamePage&gameId=<%= game.getId()%>'">SUMMITTA!!!</button>
					<p> <%= game.getDescription() %> </p>
				</div>
				
				<div id="requirements">
					<div id="reqBar">
						<button id="windowsButton" class="reqButton" onclick="changeSchedeReq('windows')"> WINDOWS </button>
						<button id="linuxButton" class="reqButton" onclick="changeSchedeReq('linux')"> LINUX </button>
						<button id="macButton" class="reqButton" onclick="changeSchedeReq('mac')"> MAC </button>	
					</div>
					
					<% Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = (Map)request.getAttribute("requirements");
						for(Map.Entry<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirements : requirementsMap.entrySet()) {
							String style = "";
							if(requirements.getKey().toString().toLowerCase().equals("windows"))
								style = "display: block";
					%>
						<div id="<%=requirements.getKey().toString().toLowerCase()%>Schede" class="reqSchede" style="<%=style%>">					
					<% 
							for(SystemRequirement requirement : requirements.getValue()) {	
					%> 
								<p id="<%= requirements.getKey().toString().toLowerCase()%>"><%= requirements.getKey().toString().toLowerCase()%>: <%=requirement.getName() %></p>
					<% 	} %>
						</div>
					<% 	
					}
					%>
					
				</div>
			</div>
		</main>
	</section>
		<jsp:include page="BasePageFooter.jsp"></jsp:include>
	</body>
</html>