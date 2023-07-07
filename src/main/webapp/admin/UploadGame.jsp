<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.Category"
	import="java.util.List" import="model.Game" import="model.Game.Pegi"%>

<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		
		<title>Upload game</title>
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js" 
		integrity = "sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="></script>
		<script src="../Scripts/UploadGameScript.js"></script>
		
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/UploadGameStyle.css">		
	</head>
	
	<body>
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>

	<section class=main>
		<main>
		<div id=formDiv>
			<form action="/GamingWorldShop/admin/GameUploadServlet" method="post"
				enctype="multipart/form-data">
					<div id=bannerImageDiv>
						<label for=bannerImage>Upload banner image:</label> 
						<input type="file" id=bannerImage name=bannerImage accept="image/jpeg, image/png, image/jpg" required> 
					</div>
					<div id=showcaseImageDiv>
						<label for=showcaseImage>Upload showcase image:</label> 
						<input type="file" id=showcaseImages name=showcaseImage accept="image/jpeg, image/png, image/jpg" required> 
					</div> 
					<div id=nameDiv>
						Name: <input type="text" name="name" required maxlength="30"> 
					</div>
					<div id=publisherDiv>
						Publisher: <input type="text" name="publisher" required maxlength="30"> 
					</div>
					<div id=descriptionDiv>
						Description:<br> <textarea name="description" rows=30 cols=80 required> </textarea>
					</div> 
					<div id=shortDescriptionDiv>
						Short description:<br> <textarea name="shortDescription" rows=10 cols=80 required> </textarea> 
					</div>
					<div id=priceDiv>
						Price: &#36;
						<input type="number" min="0" max="100000000" step="0.01" id=price name="price" required onchange="convertToDecimal(this)" />
					</div>
					<div id=stateDiv>
						State: <select name="state">
							<option value="RELEASED">Released</option>
							<option value="BETA">Beta</option>
							<option value="ALPHA">Alpha</option>
							<option value="COMING_SOON">Coming soon</option>
							<option value="UNLISTED">Unlisted</option>
						</select> 
					</div> 	
					<div id=releaseDateDiv>		
						Release date: <input type="date" id=releaseDate name="releaseDate" min="1980-01-01" max="2027-01-01" required> 
					</div>
					<div id=pegiDiv>
						Pegi:
						<select name="pegi">
							<option value="PEGI_3">3</option>
							<option value="PEGI_7">7</option>
							<option value="PEGI_12">12</option>
							<option value="PEGI_16">16</option>
							<option value="PEGI_18">18</option>
						</select> 
					</div>				
					<div id=categoryDiv>
						<h3>Choose categories</h3>
						<%
						List<Category> categories = (List<Category>) request.getAttribute("categories");
						if(categories == null){
							categories = (List<Category>)application.getAttribute("categories");
						}
						for (Category c : categories) {
						%>
						<input id="<%=c.getName()%>" type="checkbox" name="categories" required value="<%=c.getName()%>">
						<label for="<%=c.getName()%>" > <%=c.getName()%> </label> <br>
						<%
						}
						%>
					</div>
					<div id="windows" class="section">
					    <h3>Windows requirements</h3>
					    <div class="nameValueFields">
					      <div class="nameValueRow">
					        <input type="text" required name="windows[name][]" placeholder="Hardware type" maxlength="30">
					        <input type="text" required name="windows[value][]" placeholder="Value" maxlength="30">
					        <button type="button" class="removeButton" onclick="removeNameValueRow(this)">Remove</button>
					      </div>
					    </div>
					    <button type="button" onclick="addNameValueRow('windows')">Add requirement</button>
					  </div>
					  
					  <div id="mac" class="section">
					    <h3>Mac requirements</h3>
					    <div class="nameValueFields">
					    </div>
					    <button type="button" onclick="addNameValueRow('mac')">Add requirement</button>
					  </div>
					  
					  <div id="linux" class="section">
					    <h3>Linux requirements</h3>
					    <div class="nameValueFields">
					    </div>
					    <button type="button" onclick="addNameValueRow('linux')">Add requirement</button>
					  </div>
					<div id=submitDiv>
						<input type="submit" name="summitta" value=Submit>
					</div>
			</form>
			<% String logError = (String) request.getAttribute("logError");
			if (logError != null) { %>
			<p> Errore: <%=logError%></p>
			<%}	%>
		</div>
		</main>
	</section>
	
	
	
	<jsp:include page="../BasePageFooter.jsp"></jsp:include>
</body>
</html>