var map;

function initMap() {

    var queries = {};
    $.each(document.location.search.substr(1).split('&'), function(c,q){
        var i = q.split('=');
        queries[i[0].toString()] = i[1].toString();
    });
    console.debug(queries);
    var name = decodeURIComponent(queries.name);
    var lat = Number(queries.lat);
    var lng = Number(queries.lon);
    var address = decodeURIComponent(queries.address);

    $("#name").text(name);
    $("#address").text(address);

    var latLng = new google.maps.LatLng (lat, lng);
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 16
    });

    var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        title: name
    });

    map.panTo(latLng);

}
