var map;
function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : 37.380300,
			lng : -6.007697
		},
		zoom : 8
	});
}