<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		
		<jsp:include page="BasePageHeader.jsp"></jsp:include>
		
		<main>
			<div id=row-1>
				<div id=sliderImages>
					<img id=image src="./images/mariaMaddalaenaPuttana.jpg">
				</div>
		
				<div id=shortDescription>
					<table>
						<tbody>
							<tr>
								<td>
									<img id=image2 src="./images/mariaPuttana.jpg">
								</td>
							</tr>
						
							<tr>
								<td>
									<p>La cataclismica conclusione della trilogia di Total War: WARHAMMER è qui. Riunisci i tuoi eserciti e addentrati nel Regno del Caos, una dimensione di orrori indicibili dove il destino del mondo sarà deciso una volta per tutte. Sconfiggerai i tuoi demoni?… oppure li comanderai?
									</p>
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
					<h1>GIOCO INCREDIBILE</h1>
					
					<p>L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscurità e disperazione.

						Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerà tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai?
						L'ultimo ruggito di un dio morente rompe il confine tra i mondi, aprendo un portale verso il Regno del Caos. Da questo Maelstrom, emergono Khorne, Nurgle, Tzeentch e Slaanesh, i quattro Poteri Perniciosi che seminano oscurità e disperazione.

						Gli austeri guerrieri di Kislev e il vasto impero del Grande Catai attendono sulla soglia, mentre il vendicativo Principe Demone giura di distruggere coloro che lo hanno corrotto. L'imminente conflitto divorerà tutto. Sconfiggerai i tuoi demoni? Oppure li comanderai?
					</p>
				</div>
			</div>
		</main>
	
		<jsp:include page="BasePageFooter.jsp"></jsp:include>
	</body>
</html>