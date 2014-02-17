<?php /*I'm so, so sorry if you want to try to read this. It's a horrific mess.*/ ?>
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
		
	<script type = "text/javascript">
                
		var map;
		var directionsDisplay;
		var directionsService = new google.maps.DirectionsService();
		var waypointarray = new Array();
		var start = new google.maps.LatLng(<?php echo $route->waypoints[0]->get_latitude(); ?>,<?php echo $route->waypoints[0]->get_longitude(); ?>);
		var end =  new google.maps.LatLng(<?php $i=count($route->waypoints); echo $route->waypoints[$i-1]->get_latitude(); ?>,<?php echo $route->waypoints[$i-1]->get_longitude(); ?>);
		function initialize() {
			directionsDisplay = new google.maps.DirectionsRenderer();
			var Start = new google.maps.LatLng(<?php echo $route->waypoints[0]->get_latitude(); ?>,<?php echo $route->waypoints[0]->get_longitude(); ?>);
			var mapOptions = {
    			 	 zoom: 15,
   				 center: Start
  				}
  			map = new google.maps.Map(document.getElementById('map'), mapOptions);

				
			directionsDisplay.setMap(map);
			

                        
                        <?php $i=0; $length=count($route->waypoints); foreach($route->waypoints as $wp)
                        { 
                            if($i != 0 && $i != ($length-1)) {?>
                                waypointarray.push({location: new google.maps.LatLng(<?php echo $wp->get_latitude(); ?>,<?php echo $wp->get_longitude(); ?>),stopover: true});
                            <?php }
                            $i++;
                            } ?>
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
		google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {  
			    alert("Pressed some shit");		
		});
	</script>
</head>
<body>
	<div id = "wrapper">
		<div id = "main">	
                    <a href ="/" class ="home">Home</a>
		<div id = "heading">
			<h1>Walking Tour Displayer</h1>
		</div>
		
			
			<div id = "map">
			
			</div>		
			<div id = "info">
			<div id = "tour-desc">
                            <h2><?php echo $route->title; ?></h2>    
				<h3>Tour Description:</h3>
				<p><?php echo $route->longDesc; ?></p>
                                <?php $i=0; foreach($route->waypoints as $wp)
                                { ?>
                                
                                <div id ="divider"></div>
                                
                                
                                <h2 class="clickable" onclick="showInfo(<?php echo $i; ?>)">
                                    <?php if($i==0) { 
                                            echo "Start:"; 
                                    } 
                                    else if ($i == count($route->waypoints) -1) 
                                            { echo "Finish:"; } 
                                            
                                            else { echo "Waypoint ".$i.":"; }?>
                                    
                                </h2>
                                <div class ="info <?php echo $i; ?>">
                                    <p><?php echo $wp->get_description(); ?></p>
                                </div>
                                    <?php $i++;
                                
                                } ?>
                        </div>
                        </div>
		<div class = "clear"></div>
		</div>
	</div>
</body>
</html>
