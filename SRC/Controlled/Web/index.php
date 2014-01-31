<?php

/*This code contains all the processes for the homepage to opeate correctly.
 * 
 * If case is 'view' it allows the user to view a tour. It gets the id of the 
 * the relevant tour from the database and then loads the walk. If the ID does
 * not exist then a error 404 page appears.
 * 
 * If case is 'list' it lists all the routes on the database. It calls a method
 * which lists all the tours stored on the database
 * 
 * If case is 'upld' it checks whether data has been sent and then sends a 
 * response dependubg on whether data has been sent or not. If data has been 
 * sent a resonse of 'true' is received; If no data is sent a response of false
 * is sent.
 * 
 * If case is '404' a string is printed stating that the page could not be 
 * found. 
 */
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