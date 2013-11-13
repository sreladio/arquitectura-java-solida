function validation() {
	var isbn = document.getElementById("isbn");
	if(isbn.value == "") {
		alert("datos no validos");
	}
	else {
		document.forms[0].submit();
	}
}