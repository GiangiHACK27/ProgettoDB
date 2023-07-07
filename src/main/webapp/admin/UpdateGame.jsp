<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Category"
	import="java.util.List" import="model.Game" import="model.Game.Pegi, model.Game.State,  model.SystemRequirement, java.util.*"%>

<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js" 
		integrity = "sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="></script>
		<script src="../Scripts/UpdateGameScript.js"></script>
			
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/UploadGameStyle.css">
				
		<title>Upload game</title>
	</head>
	
	<jsp:include page="/RetrieveGameCategories"></jsp:include>
	<jsp:include page="/RetrieveGameServlet"></jsp:include>
	<jsp:include page="/RetrieveGameRequirementsServlet"></jsp:include>
	
	<% Game game = (Game)request.getAttribute("game"); %>
	<body>
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>

	<section class=main>
		<main>
		<div id=formDiv>
			<form action="/GamingWorldShop/admin/UpdateGameServlet?gameId=<%=game.getId() %>" method="post"
				enctype="multipart/form-data">
					<div id=bannerImageDiv>
						<label for=bannerImage>Change banner image (stays unchanged otherwise):</label> 
						<input type="file" id=bannerImage name=bannerImage accept="image/jpeg, image/png, image/jpg"> 
					</div>
					<div id=showcaseImageDiv>
						<label for=showcaseImage>Change showcase image (stays unchanged otherwise):</label> 
						<input type="file" id=showcaseImages name=showcaseImage accept="image/jpeg, image/png, image/jpg"> 
					</div> 
					<div id=nameDiv>
						Name: <input type="text" name="name" required maxlength="30" value= <%=game.getName() %>> 
					</div>
					<div id=publisherDiv>
						Publisher: <input type="text" name="publisher" required maxlength="30" value= <%=game.getPublisher() %>> 
					</div>
					<div id=descriptionDiv>
						Description:<br> <textarea name="description" rows=30 cols=80 required><%=game.getDescription() %> </textarea>
					</div> 
					<div id=shortDescriptionDiv>
						Short description:<br> <textarea name="shortDescription" rows=10 cols=80 required> <%=game.getShortDescription() %> </textarea> 
					</div>
					<div id=priceDiv>
						Price: &#36;
						<input type="number" value=<%=(float)game.getPrice()/100 %> min="0" max="100000000" step="0.01" id=price name="price" required onchange="convertToDecimal(this)" />
					</div>
					<div id=stateDiv>
						State: <select name="state">
						<%for(State state : State.values()){ %>
							<%if(game.getState().equals(state)){ %>
								<option value="<%=state.getValue().toUpperCase() %>" selected><%=state.getValue().replace('_', ' ')%></option>
							<%}else{ %>
								<option value="<%=state.getValue().toUpperCase() %>"><%=state.getValue().replace('_', ' ')%></option>
							<%} %>
						<%} %>
						</select>
					</div> 	
					<div id=releaseDateDiv>		
						Release date: <input type="date" id=releaseDate name="releaseDate" min="1980-01-01" max="2027-01-01" required value=<%=game.getReleaseDate()%>> 	
					</div>
					<div id=pegiDiv>
						Pegi:
						<select name="pegi">
							<%for (Pegi pegi: Pegi.values()){ %>
								<%if(game.getPegi().equals(pegi)){ %>
									<option value="<%=pegi.toString()%>"  selected ><%=pegi.getValue()%></option>
								<%}else{ %>
									<option value="<%=pegi.toString()%>"><%=pegi.getValue()%></option>
								<%} %>
							<%} %>
						</select> 
					</div>				
					<div id=categoryDiv>
						<h3>Choose categories</h3>
						<%
						List<Category> gameCategories = (List<Category>) request.getAttribute("gameCategories");
						List<Category> categories = (List<Category>) request.getAttribute("categories");
						if(categories == null){
							categories = (List<Category>)application.getAttribute("categories");
						}
						for (Category c : categories) {
						%>
							<%if(gameCategories.stream().map(category->category.getName()).anyMatch(name->name.equals(c.getName())) ){ %>
							<input id="<%=c.getName()%>" type="checkbox" name="categories" checked value="<%=c.getName()%>">
							<label for="<%=c.getName()%>" > <%=c.getName()%> </label> <br>
							<%}else{%>
							<input id="<%=c.getName()%>" type="checkbox" name="categories" value="<%=c.getName()%>">
							<label for="<%=c.getName()%>" > <%=c.getName()%> </label> <br>
							<%}%>
						<%
						}
						%>
					</div>
					<% Map<SystemRequirement.OperatingSystem, List<SystemRequirement>> requirementsMap = (Map)request.getAttribute("requirements"); %>

					 <div id="windows" class="section">
					    <h3>Windows requirements</h3>
					    <div class="nameValueFields">
						<%List<SystemRequirement> defaultValue = null;
						List<SystemRequirement> list = requirementsMap.getOrDefault(SystemRequirement.OperatingSystem.WINDOWS, defaultValue);
						if(list != null)
							for(SystemRequirement req:list){
						  %>
					      <div class="nameValueRow">
					        <input value=<%=req.getName() %> type="text" required name="windows[name][]" placeholder="Hardware type" maxlength="30">
					        <input value=<%=req.getValue() %> type="text" required name="windows[value][]" placeholder="Value" maxlength="30">
					        <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
					      </div>
					    <%} %>
					    </div>
					    <button type="button" onclick="addNameValueRow('windows')">Add requirement</button>
					  </div>
					  
					  <div id="mac" class="section">
					    <h3>Mac requirements</h3>
					    <div class="nameValueFields">
						<%
						list = requirementsMap.getOrDefault(SystemRequirement.OperatingSystem.MAC, defaultValue);
						if(list != null)
							for(SystemRequirement req:list){
						  %>
					      <div class="nameValueRow">
					        <input value=<%=req.getName()%> type="text" required name="mac[name][]" placeholder="Hardware type" maxlength="30">
					        <input value=<%=req.getValue()%> type="text" required name="mac[value][]" placeholder="Value" maxlength="30">
					        <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
					      </div>
					    <%} %>
					    </div>
					    <button type="button" onclick="addNameValueRow('mac')">Add requirement</button>
					  </div>
					  
					  <div id="linux" class="section">
					    <h3>Linux requirements</h3>
					    <div class="nameValueFields">
						<%defaultValue = null;
						list = requirementsMap.getOrDefault(SystemRequirement.OperatingSystem.LINUX, defaultValue);
						if(list != null)
							for(SystemRequirement req:list){
						  %>
					      <div class="nameValueRow">
					        <input value=<%=req.getName()%> type="text" required name="linux[name][]" placeholder="Hardware type" maxlength="30">
					        <input value=<%=req.getValue()%> type="text" required name="linux[value][]" placeholder="Value" maxlength="30">
					        <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
					      </div>
					    <%} %>
					    </div>
					    <button type="button" onclick="addNameValueRow('linux')">Add requirement</button>
					  </div>
					<div id=submitDiv>
						<input type="submit" name="summitta" value=Submit>
					</div>
			</form>
			<% String logError = (String) request.getAttribute("logError");
			if (logError != null) { %>
			<p> Error: <%=logError%></p>
			<%}	%>
		</div>
		</main>
	</section>
	
	
	
	<jsp:include page="../BasePageFooter.jsp"></jsp:include>
</body>
</html>