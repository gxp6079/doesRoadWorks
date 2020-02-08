/* Data points defined as an array of LatLng objects */
var map;
var heatmap;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: {lat: 43.084430, lng: -77.676170},
        mapTypeId: 'satellite'
    });

    heatmap = new google.maps.visualization.HeatmapLayer({
        data: getPoints(),
        map: map
    });
}
function getPoints() {
    return [new google.maps.LatLng(43.084430, -77.676170)];
}

initMap()

// <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2913.9522415168635!2d-77.67711978460042!3d43.084499397035714!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89d14c603a147e55%3A0xbe7eb31ed5e22c03!2sRochester%20Institute%20of%20Technology!5e0!3m2!1sen!2sus!4v1581190992721!5m2!1sen!2sus" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen=""></iframe>



/**
var heatmapData = [
    {location: new google.maps.LatLng(43.084430, -77.676170), weight: 0.5},
    new google.maps.LatLng(43.084430, -77.676170)
];

var rit = new google.maps.LatLng(43.084430, -77.676170);

map = new google.maps.Map(document.getElementById('map'), {
    center: rit,
    zoom: 13,
    mapTypeId: 'satellite'
});

var heatmap = new google.maps.visualization.HeatmapLayer({
    data: heatmapData
});
heatmap.setMap(map);
 */
