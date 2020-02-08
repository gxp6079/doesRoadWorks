<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Does Road Works?</title>
<#--    <link rel="stylesheet" href="public/css/style.css">-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <style>
        #map {
            height: 80vh;
            width: 80vw;
        }
    </style>
</head>
<body>
<div class="page">
    <h1>${title} </h1>

    <div id="map"></div>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=${API_KEY}&libraries=visualization">
    </script>

    <script>
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
            return [new google.maps.LatLng(43.084430, -77.676170),
                new google.maps.LatLng(43.084430, -77.676)];
        }

        initMap()
    </script>
</body>


</div>

</body>
</html>