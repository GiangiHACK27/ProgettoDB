function changeSchedeReq(id) {
	schedes = document.getElementsByClassName("reqSchede");
	for(schede in schedes) {
		schedes[schede].style = "display: none";
	}
	
	document.getElementById(id+"Schede").style = "display: block";
}