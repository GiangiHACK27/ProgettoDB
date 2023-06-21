let USDollar = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
});

window.addEventListener('load', function () {
  var classes = document.getElementsByClassName("price")
  for(var i = 0; i<classes.length; i++){
	  classes[i].innerHTML = USDollar.format(classes[i].innerHTML/100);
  }
})

