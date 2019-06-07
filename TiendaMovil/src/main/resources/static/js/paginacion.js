//Recarga de página
$(document).ready(function() {
	changePageAndSize();
});

// Permite recargar la página cada vez que cambia el tamaño de página
function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {

		// Url base
		var urlBase = path;
		// Establece el tamaño de página recién seleccionado
		var pageSize = "?pageSize=" + this.value;
		// Siempre que se cambia el tamaño de página, nos vamos a la página 1
		var page = "&page=1";
		// Comprobamos si se ha realizado una búsqueda, verificando
		// si en la url hay un parámetro llamado nombre. Si lo hay
		// lo volvemos a incluir en la url como parámetro, y si no
		// no hacemos nada
		var modelo = $.urlParam('modelo');
		var strModelo = "";
		if (modelo != null)
			strModelo = "&modelo=" + modelo;

		window.location.replace(urlBase + pageSize + page + strModelo);

	});
}