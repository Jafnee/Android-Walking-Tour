<?php
include($_SERVER['DOCUMENT_ROOT']."/Lib/config.lib.php");
/* This code contains all the functions required to send the walking tour data 
 * from the android phone to the database for the tours to be displayed on the 
 * webpage.
 */

       /* Here we check whether data has been sent and then sends a 
        * response dependubg on whether data has been sent or not. If data has 
        * been sent a resonse of 'true' is received; if no data is sent a 
        * response of false is sent.
        */
        header("Content-Type:plain/text");
        $json = json_decode($HTTP_RAW_POST_DATA);
        if($json->{'Title'}) {
            echo "True";
        } else {
            echo "False";
        }
        
        
        /* 
         * Here we process the POST message received from the app, populating a 
         * route object with information and waypoint objects
         */
        $route = new route();
        $route->long_desc = ucfirst($json->Long_Description);
        $route->short_desc = ucfirst($json->Short_Description);
        $route->Title = ucfirst($json->Title);
        
        
        /*
         * Here we process the POST message received fromt he app, populating a 
         * hold object with information for individual waypoints which then get
         * added to an array of waypoints.
         */
        $arr = $json->Route;
        $waypoints = array();
        foreach($arr as $wp) {
            $hold = new waypoint();
            $hold->latitude = $wp->lat;
            $hold->longitude = $wp->long;
            $hold->desc = $wp->description;
            $hold->timestamp = $wp->timestamp;            
    
            //We'll save the code for processing the images for later versions
            $waypoints[] = $hold;
        }       
        
        $route->waypoints = $waypoints;        
        $route->save();
        ?>