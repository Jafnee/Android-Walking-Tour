<?php
include($_SERVER['DOCUMENT_ROOT']."/Lib/config.lib.php");
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        /*Sends a response of true if the POST is received, or false if nothing received*/
        header("Content-Type:plain/text");
        $json = json_decode($HTTP_RAW_POST_DATA);
        if($json->{'Title'}) {
            echo "True";
        } else {
            echo "False";
        }
        
        

        $route = new route();
        $route->long_desc = $json->Long_Description;
        $route->short_desc = $json->Short_Description;
        $route->Title = $json->Title;
        
       //mail("11ready22@gmail.com","App Debugging",$route->long_desc.$route->short_desc.$route->Title);
        
        print_r($json);
        
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
        
        mail("11ready22@gmail.com","Waypoint Count",count($route->waypoints[0]));
        
        $route->save();
        ?>