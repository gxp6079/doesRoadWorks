<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>Does Road Works?</title>
    <link rel="icon" href="/src/main/resources/public/DoesRoadWorksIcon.ico" type="image/icon">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/materialize.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="/js/nav-bar.js"></script>
</head>
<body>
<img class="page">
    <div class="row">
        <div class="container col s3">

            <form id="submitForm" action="/newBoundaries" method="post">
                <label class="" for="north">North</label><input readonly type="text" id="north" name="north"><br><br>
                <label class="" for="south">South</label><input readonly type="text" id="south" name="south"><br><br>
                <label class="" for="west">West</label><input readonly type="text" id="west" name="west"><br><br>
                <label class="" for="east">East</label><input readonly type="text" id="east" name="east"><br><br>
                <input class="btn" type="submit">
            </form>


            <textarea readonly style="margin-top: 5vh" class="z-depth-1" id="dataField"></textarea>


            <input id="hideSquare" class="btn" onclick="showRemove()" type="button" style="margin-left: 1vh" value="Toggle Selection Area">
        </div>

        <div id="map" name="map" class="col s9 z-depth-1"></div>
    </div>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=${API_KEY}&libraries=visualization">
    </script>

    <script>
        var map;
        var heatmap;
        var originalCoordinates = {
            north: 40.748,
            south: 40.742,
            east: -73.985,
            west: -73.992
        };

        function initMap(wayArr) {
            var north = ${north};
            if(north == 0){
                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: {lat: 40.753032, lng: -73.991084},
                    mapTypeId: 'roadmap'
                });

                document.getElementById("north").value = originalCoordinates.north;
                document.getElementById("south").value = originalCoordinates.south;
                document.getElementById("west").value = originalCoordinates.west;
                document.getElementById("east").value = originalCoordinates.east;
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
                var way = wayArr[i];
                for (j = 0; j < way.nodes.length; j++) {
                    var node = way.nodes[j];
                    //console.log(node.lat + ", " + node.lon);
                    flightPlanCoordinates.push({lat:node.lat , lng:node.lon})
                }



                if (way.speed) {

                    var color = (way.isGood) ? '#00FF00' : '#FF0000';

                    var flightPath = new google.maps.Polyline({
                        path: flightPlanCoordinates,
                        geodesic: true,
                        strokeColor: color,
                        strokeOpacity: 1.0,
                        strokeWeight: 2,
                        map: map,
                        way: way
                    });

                    flightPath.addListener("click", function () {
                        document.getElementById("dataField").value = "Expected Time: " + this.way.expectedTime.toString() + "seconds\n Real Time: " + this.way.givenTime +
                        "seconds";
                    })
                }

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

        function showRemove() {
            if(draggablePolygon.map != null){
                draggablePolygon.setMap(null);
            }
            else{
                draggablePolygon = new google.maps.Rectangle({
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
                });;
            }
        }

    </script>
</body>


</div>

</body>
</html>