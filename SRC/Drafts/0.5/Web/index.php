<?php
include($_SERVER['DOCUMENT_ROOT']."/Lib/config.lib.php");

$cmd = $_GET['m'];

switch($cmd)
{
    case "view":
        /*View a tour*/
        $id=$_GET['id'];
        if(!$id) { header("Location: /index.php?m=404"); }
        
        $route->load($id);
        $route->waypoints = $waypoint->load_walk($id);
        
        include(View."tour.tem.php");
        break;
    case "list": 
        /*Lists all tours*/
        $routes=$route->list_all();
        include(View."listtours.tem.php");
        break;
    case "upld": 
        /*Sends a response of true if the POST is received, or false if nothing received*/
        header("Content-Type:plain/text");
        $data = $_POST;
        if($data) {
            echo "true";
            mail("lewis@lewisdale.co.uk","POSTDATA","received the post!");
        } else {
            echo "false";
            mail("lewis@lewisdale.co.uk","POSTDATA","Did not receive data");
        }
        
        print_r($_REQUEST);
        break;
    case "404":
        $string = "<h1>OOPS</h1><h2>The Page Could Not Be Found</h2>";
        echo $string;
        break;
    default: header("Location: /index.php?m=list"); break; /*Send them to the tour list if they don't know where they're going*/
}
exit;
?>