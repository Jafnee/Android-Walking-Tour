<?php
define("DOCROOT","/home/lewisdal/public_html/groupproject/");
define("LIB",DOCROOT."Lib/");
define("File",DOCROOT."Files/");
define("View",DOCROOT."View/");

session_start();

$host="localhost";
$user="lewisdal_groproj";
$pass="gr0uppr0j3ct";
$dbname="lewisdal_groupproject";
$db=new mysqli( $host , $user , $pass , $dbname );

include(LIB."route.lib.php");
include(LIB."waypoint.lib.php");
include(LIB."image.lib.php");
$route=new route();
$waypoint=new waypoint();
?>