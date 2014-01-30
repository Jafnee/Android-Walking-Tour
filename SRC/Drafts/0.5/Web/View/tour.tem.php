<!doctype html>
<html>
<head>
	<title>Walking Tour Displayer</title>
	<link rel = "stylesheet" type = "text/css" href = "/View/styles.css" />
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBXciRE4QaBPIsr0gj3jjpO_eUaW4huja4&sensor=false"></script>
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>

	<!--http://lokeshdhakar.com/projects/lightbox2/-->
	<script src = "/View/lightbox/js/lightbox-2.6.min.js"></script>
	<link rel = "stylesheet" type = "text/css" href = "/View/lightbox/css/lightbox.css" />	
        <script type ="text/javascript">
            
            //Initialize the variables
            var length = <?php echo count($route->waypoints); ?>;
            var map;
            var directionsDisplay;
            var directionsService = new google.maps.DirectionsService();
            var waypointarray = new Array();
            <?php foreach($route->waypoints as $wp) { ?>
            waypointarray.push({location: new google.maps.LatLng(<?php echo $wp->get_latitude(); ?>,<?php echo $wp->get_longitude(); ?>),stopover: true});
            <?php } ?>
            
            var start = waypointarray[0].location;
            var end = waypointarray[length - 1].location;
            waypointarray.splice(0,1);
            waypointarray.splice(length-1,1);
            
            //Initialize the map
            function initialize() {
                
                directionsDisplay = new google.maps.DirectionsRenderer();
                var mapOptions = {
                    zoom: 15,
                    center: start
                }
                
                map = new google.maps.Map(document.getElementById('map'), mapOptions);	
                directionsDisplay.setMap(map);
                
                var request = {
			     origin:start,
			     waypoints: waypointarray,
			      destination:end,
 			     travelMode: google.maps.TravelMode.WALKING
                };
                
                directionsService.route(request, function(response, status) {
   				if (status == google.maps.DirectionsStatus.OK) {
      					directionsDisplay.setDirections(response);
   				 }
                });
                
                $('div.info').slideUp();
            }
            
            //Function to show the waypoint information on click
            function showInfo(i) {
                    $('div.info').slideUp();
                    $('div.info.'+i).slideDown();
                    if(i == 0) {
			map.setCenter(start);
                    } else if(i == (length-1)) {
                    	map.setCenter(end);
                    } else {
			var latLng = waypointarray[i-1].location;
			map.setCenter(latLng);
                    }	
			
                    map.setZoom(17);	
            }
            
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
</head>
<body>
    <div id = "wrapper">
        <div id = "main">
            <div id = "heading">
                <h1>Walking Tour Displayer</h1>
            </div>
            <div id = "map"><!--DON'T PUT ANYTHING HERE--></div>
            <div id = "info">
                <div id = "tour-desc">
                    <h2><?php echo $route->title; ?></h2>
                    <h3>Tour Description:</h3>
                    <p><?php echo $route->get_long_desc(); ?></p>
                    <!--WAYPOINTS-->
                    <?php
                    $len=(count($route->waypoints)-1);
                    $i=0;
                    
                    foreach($route->waypoints as $wp) {
                    ?>
                    <h2 class = "clickable" onclick = "showInfo(<?php echo $i; ?>)">
                         <?php echo ($i == 0) ? "Start:" : (($i == $len) ? "Finish:" : "Waypoint ".($i + 1).":"); echo "  v"; ?>   
                    </h2>
                    <div class = "info <?php echo $i; ?>">
                        <?php foreach($wp->get_images() as $img) { ?>
                        <a href ="<?php echo $img; ?>" data-lightbox="info-<?php echo $i; ?>">
                            <img src = "<?php echo $img; ?>" class  = "gallimg" />
                        </a>
                        <?php } ?>
                        <p><?php echo $wp->get_description(); ?></p>
                    </div>
                    <?php 
                    $i++;
                    } ?>
                    
                    
                </div>
            </div>
            <div class = "clear"></div>
        </div>
    </div>
</body>
</html>