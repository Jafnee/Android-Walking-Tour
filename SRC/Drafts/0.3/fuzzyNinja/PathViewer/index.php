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
        print_r($route);
        break;
    case "list": 
        /*Lists all tours*/
        break;
    case "upld": 
        /*Sends a response of true if the POST is received, or false if nothing received*/
        header("Content-Type:plain/text");
        $data = $_POST;
        if($data) {
            echo "true";
        } else {
            echo "false";
        }
        break;
    case "404":
        $string = "<h1>OOPS</h1><h2>The Page Could Not Be Found</h2>";
        echo $string;
        break;
    default: break;
}
exit;
?>