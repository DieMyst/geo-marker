var pointsLink = "/api/points";
var locationLink = "/api/location";
var singlePointLink = "/point.html";

var points = [];
var markers = [];

var map;

function updateMap() {
    clearMarkers();
    var latlngbounds = new google.maps.LatLngBounds();
    $.each(points, function(k, point) {
        var latLng = new google.maps.LatLng (point.latitude, point.longitude);
        latlngbounds.extend(latLng);
        addMarker(latLng, point.name);
    });

    map.setCenter(latlngbounds.getCenter());
    map.fitBounds(latlngbounds);
}

function addMarker(location, name) {
    var marker = new google.maps.Marker({
        position: location,
        map: map,
        title: name
    });
    markers.push(marker);
}

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function clearMarkers() {
    setMapOnAll(null);
    markers = [];
}

function updateList() {
    $.get(pointsLink).done(function(pointList) {
        var linkList = $("#points-list");
        linkList.empty();
        points = [];
        $.each(pointList, function(k, point) {
            points.push(point);
            var li = $('<li></li>');
            li.append($('<a>', {
                href: singlePointLink + "?name=" + point.name + "&lon=" + point.longitude + "&lat=" + point.latitude + "&address=" + point.address,
                text: point.name
            }));
            linkList.append(li)

        });
        updateMap();
    });

}

function addErrorMsg(msg) {
    var error = $("#error");
    error.text(msg);
    error.show();
}

function addPoint(name, address) {
    $.get(locationLink + "?name=" + name + "&address=" + address).done(function(status){
        updateList()
    }).fail(function() {
        addErrorMsg("Неверный адрес")
    });
}
$(function() {
    $("#btn").click(function() {
        var name = $("#name");
        var address = $("#address");
        var error = $("#error");


        name.parent().removeClass("has-error");
        address.parent().removeClass("has-error");
        error.hide();

        if (name.val() === "") {
            name.parent().addClass("has-error");
            addErrorMsg("Необходимо ввести название")
        } else {
            addPoint(name.val(), address.val());
            name.val("");
            address.val("");
        }

        return false;
    });
});

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 1
    });
    updateList();
}
