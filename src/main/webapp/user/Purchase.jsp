<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta name="viewport" content="initial-scale=1, width=device-width">

<link rel="stylesheet" href="./../CSS/BaseStyle.css">
<link rel="stylesheet" href="./../CSS/Purchase.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>

<body>
	<jsp:include page="../BasePageHeader.jsp"></jsp:include>

	<%
	String from = request.getParameter("from");
	String servletToCall = "PurchaseFromCartServlet?category=cart";
	if (from != null && from.equals("personalGamePage")) {
		String gameId = request.getParameter("gameId");
		servletToCall = "AddPurchaseGameServlet?gameId=" + gameId;
	}
	%>

	<section class=main>
		<main>
<%-- 			<form method="POST" action="<%=servletToCall%>">
 --%>				<!-- 					<label for = "card_number">Card number</label>
					<input type = "text" name = "card_number" required></input>
					<br>
				
					<input type = "submit" value = "buy"></input> -->
				<div class="row">
					<div class="col-75">
						<div class="container">
							<form method="POST" action="<%=servletToCall%>">

								<div class="row">
									<div class="col-50">
										<h3>Billing Address</h3>
										<label for="fname"><i class="fa fa-user"></i> Full
											Name</label> <input type="text" required id="fname" name="firstname"
											placeholder="John M. Doe"> <label for="email"><i
											class="fa fa-envelope"></i> Email</label> <input required type="email"
											id="email" name="email" placeholder="john@example.com">
										<label for="adr"><i class="fa fa-address-card-o"></i>
											Address</label> <input type="text" required id="adr" name="address"
											placeholder="542 W. 15th Street"> <label for="city"><i
											class="fa fa-institution"></i> City</label> <input type="text"
											id="city" name="city" placeholder="New York">

										<div class="row">
											<div class="col-50">
												<label for="state">State</label> <input type="text"
													id="state" name="state" placeholder="NY" required pattern="^\w{2,2}$">
											</div>
											<div class="col-50">
												<label for="zip">Zip</label> <input type="text" id="zip"
													name="zip" placeholder="10001" required pattern="^.{5,5}$">
											</div>
										</div>
									</div>

									<div class="col-50">
										<h3>Payment</h3>
										<label for="fname">Accepted Cards</label>
										<div class="icon-container">
											<i class="fa fa-cc-visa" style="color: red;"></i> <i
												class="fa fa-cc-amex" style="color: yellow;"></i> <i
												class="fa fa-cc-mastercard" style="color: red;"></i> <i
												class="fa fa-cc-discover" style="color: orange;"></i>
										</div>
										<label for="cname">Name on Card</label> <input type="text"
											id="cname" required name="cardname" placeholder="John More Doe">
										<label for="ccnum">Credit card number</label> <input
											type="text" required id="ccnum" name="card_number" required
											placeholder="1111-2222-3333-4444" pattern ="^\d{4}-\d{4}-\d{4}-\d{4}$">

										<div class="row">
											<div class="col-50">
												<label for="expyear">Exp Date</label> <input type="month"
													id="expyear" required name="expyear">
											</div>
											<div class="col-50">
												<label for="cvv">CVV</label> <input type="text" id="cvv"
													name="cvv" placeholder="352" required pattern="^.{3,4}$">
											</div>
										</div>
									</div>

								</div>
								</label> <input type="submit" value="Confirm" class="btn">
							</form>
						</div>
					</div>
				</div>
		</main>
	</section>

	<jsp:include page="../BasePageFooter.jsp"></jsp:include>

</body>
</html>