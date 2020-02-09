<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>Does Road Works?</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="/js/nav-bar.js"></script>
</head>
<body>
<img class="page">

    <div class="dropdown">
        <div id="myDropdown" class="dropdown-content">
            <label for="north">North</label> <input type="text" id="north" name="north"><br><br>
            <label for="south">South</label> <input type="text" id="south" name="south"><br><br>
            <label for="west">West</label> <input type="text" id="west" name="west"><br><br>
            <label for="east">East</label> <input type="text" id="east" name="east"><br><br>
        </div>
    </div>

    <button id="submit">Submit</button>

    <div id="map"></div>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=${API_KEY}&libraries=visualization">
    </script>

    <script>
        var map;
        var heatmap;
        var originalCoordinates = {
            north: 43.10,
            south: 43.05,
            east: -77.64,
            west: -77.70
        };

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

        var draggablePolygon = new google.maps.Rectangle({
            map: map,
            bounds: originalCoordinates,
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: "#000000",
            fillOpacity: 0.35,
            draggable: true,
            geodesic: true,
            editable: true
        });

        draggablePolygon.addListener('drag', function () {
            document.getElementById("north").value = draggablePolygon.bounds.Ya.i.toString();
            document.getElementById("south").value = draggablePolygon.bounds.Ya.g.toString();
            document.getElementById("west").value = draggablePolygon.bounds.Ta.g.toString();
            document.getElementById("east").value = draggablePolygon.bounds.Ta.i.toString();
        });

        draggablePolygon.addListener('bounds_changed', function () {
            document.getElementById("north").value = draggablePolygon.bounds.Ya.i.toString();
            document.getElementById("south").value = draggablePolygon.bounds.Ya.g.toString();
            document.getElementById("west").value = draggablePolygon.bounds.Ta.g.toString();
            document.getElementById("east").value = draggablePolygon.bounds.Ta.i.toString();
        });

    </script>
</body>


</div>

</body>
</html>