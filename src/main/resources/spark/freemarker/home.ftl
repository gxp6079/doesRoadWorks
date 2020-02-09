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
        function initMap(wayArr) {
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 15,
                center: {lat: 43.084430, lng: -77.676170},
                mapTypeId: 'roadmap'
            });


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

                var flightPath = new google.maps.Polyline({
                    path: flightPlanCoordinates,
                    geodesic: true,
                    strokeColor: '#FF0000',
                    strokeOpacity: 1.0,
                    strokeWeight: 2,
                    map : map
                });
            }





        }
        function getPoints() {

            return [new google.maps.LatLng(43.084430, -77.676170),
                new google.maps.LatLng(43.084430, -77.676)];
        }

        initMap(${wayList})
    </script>
</body>


</div>

</body>
</html>