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
					<img id=imageSlider src="./images/mariaMaddalaenaPuttana.jpg">
				</div>
		
				<div id=shortDescription>
					<table>
						<tbody>
							<tr>
								<td>
									<img id=imageBanner src="./images/mariaPuttana.jpg">
								</td>
							</tr>
						
							<tr>
								<td>
									<!--
									<p>La cataclismica conclusione della trilogia di Total War: WARHAMMER è qui. Riunisci i tuoi eserciti e addentrati nel Regno del Caos, una dimensione di orrori indicibili dove il destino del mondo sarà deciso una volta per tutte. Sconfiggerai i tuoi demoni?… oppure li comanderai?
									</p>
									-->
									
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
					<h1><%=game.getName() %></h1>
					<p> <%= game.getDescription() %> </p>
				</div>
				
				<div id="requirements">
					<div id="reqBar">
						<button class="reqButton"> WINDOWS </button>
						<button class="reqButton"> LINUX </button>
						<button class="reqButton"> MAC </button>	
					</div>
					
					<% Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = (Map)request.getAttribute("requirements");
						for(Map.Entry<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirements : requirementsMap.entrySet()) {
							String style = "";
							if(requirements.getKey().toString().toLowerCase().equals("windows"))
								style = "display: block";
					%>
						<div id="<%=requirements.getKey().toString().toLowerCase()%>" class="reqSchede" style="<%=style%>">					
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