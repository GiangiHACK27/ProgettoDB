<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang = en>
	<head>
		<meta charset="ISO-8859-1">
		
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="stylesheet" href="../CSS/BaseStyle.css">
		<link rel="stylesheet" href="../CSS/PurchasesStyle.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<script src=../Scripts/PurchaseListScript.js></script>
		
		<title>Purchase</title>
	</head>

	<body>
		
		<jsp:include page="../BasePageHeader.jsp"></jsp:include>
		
		<section class=main>
			<main>
		
				<form>
					<h1> Purchases </h1>
					
					<section id="filterSection">
						<input id=username type=text name=username placeholder="Filter by username" value="">
						
						<label for=minDate>From</label>
						<input id=minDate type=date name=minDate value="1999-09-09">
						
						<label for=maxDate>To</label>
						<input id=maxDate type=date name=maxDate value="2023-01-01">
					</section>
					
					<section id="tableSection">
						<table id=purchaseListTable>
							<thead>
								<tr>
									<th>User Id</th>
									<th>Username</th>
									<th>Game Id</th>
									<th>Price</th>
									<th>Date Purchased</th>
								</tr>		
							</thead>
							<tbody id=tableBody>
							
							</tbody>				
						</table>
					</section>
				</form>
			</main>
		</section>
		
		<jsp:include page="../BasePageFooter.jsp"></jsp:include>
		
	</body>
</html>