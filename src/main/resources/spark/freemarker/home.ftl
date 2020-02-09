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

    <div>

    <form id="submitForm" action="/newBoundaries" method="post">
        <button id="submitBtn" type="submit">Submit</button>
        <div id="myDropdown" class="dropdown-content">
            <label for="north">North</label> <input type="text" id="north" name="north"><br><br>
            <label for="south">South</label> <input type="text" id="south" name="south"><br><br>
            <label for="west">West</label> <input type="text" id="west" name="west"><br><br>
            <label for="east">East</label> <input type="text" id="east" name="east"><br><br>
        </div>
    </form>
    </div>

    <div id="map" name="map"></div>

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

        function initMap(wayArr) {
            var north = ${north};
            if(north == 0){
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: {lat: 43.084430, lng: -77.676170},
                    mapTypeId: 'roadmap'
                });
            }
            else{
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: {lat: (${north} + ${south})/2, lng: (${west} + ${east})/2},
                    mapTypeId: 'roadmap'
                });

                originalCoordinates.north = ${north};
                originalCoordinates.south = ${south};
                originalCoordinates.west = ${west};
                originalCoordinates.east = ${east};

                document.getElementById("north").value = ${north};
                document.getElementById("south").value = ${south};
                document.getElementById("west").value = ${west};
                document.getElementById("east").value = ${east};
            }


            var flightPlanCoordinates = [];
            console.log("test test test");
            console.log("len: " + wayArr.length);
            for (i = 0; i < wayArr.length; i++) {
                flightPlanCoordinates = [];
                var way = wayArr[i].nodes;
                for (j = 0; j < way.length; j++) {
                    var node = way[j];
                    //console.log(node.lat + ", " + node.lon);
                    flightPlanCoordinates.push({lat:node.lat , lng:node.lon})
                }
                var curColor = '#'+(Math.random()*0xFFFFFF<<0).toString(16)
                var flightPath = new google.maps.Polyline({
                    path: flightPlanCoordinates,
                    geodesic: true,
                    strokeColor: curColor,
                    strokeOpacity: 1.0,
                    strokeWeight: 2,
                    map : map
                });
            }
        }

        initMap(${wayList})

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