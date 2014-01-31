/*
 *@(#)Waypoint.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
package cs221.group15.pathfinder;


/**
 * This class is for the individual waypoints that will get created
 * along the walking tour. Each waypoint object will have GPS coordinates
 * and a timestamp.The object will also have a description and a picture 
 * provided by the user.
 * 
 * The latitude and longitude coordinates will be used on the website
 * to place a marker of the waypoint's location on the map.
 * @see LocationListen#onLocationChanged(android.location.Location)
 * 
 * The waypoints will be added to a Route's array, therefore becoming part
 * of that walking tour.
 * @see Route
 * 
 * 
 * @author 	gad16
 * @since	0.1
 * @version 1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 */
public class Waypoint {
    private float lat;
    private float lng;
    private long id;
    private long walkID;
    private float timestamp;

    private String[] photos;
    private String description;

    
    /**
     * The constructor will create the Waypoint object and will pass in
     * the walkID which is the ID of the Route(walking tour) it belongs to.
     * 
     * @param id 		the unique number to identify this waypoint
     * @param walkID 	the ID number of the Route it belongs to
     */
    Waypoint(long id, long walkID) {
        this.id = id;
        this.walkID = walkID;
    }

    
    /**
     * This class constructor will only create the waypoint object,
     * it will not assign it to a Route.
     * 
     * @param walkID the unique number to identify this waypoint
     */
    Waypoint(long walkID) {
        this.walkID = walkID;
    }
    
    
    /**
     * Returns the latitude coordinate of the waypoint.
     * 
     * @return the float value of latitude
     */
    public float getLat() {
        return lat;
    }

    
    /**
     * Sets latitude value of the waypoint.
     * 
     * @param lat a float value of latitude
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    
    /**
     * Returns the longitude coordinate of the waypoint.
     * 
     * @return the float value of longitude
     */
    public float getLng() {
        return lng;
    }

    
    /**
     * Sets the longitude value of the waypoint.
     * 
     * @param lng a float value of longitude
     */
    public void setLng(float lng) {
        this.lng = lng;
    }
    

    /**
     * Returns the ID of the walking tour it belongs to.
     * 
     * @return the ID of the route it belongs to as a long
     */
    public long getWalkID() {
        return walkID;
    }

    
    /**
     * Sets the ID of the walking tour the waypoint belongs to.
     * 
     * @param walkID an integer value of the Route ID
     */
    public void setWalkID(int walkID) {
        this.walkID = walkID;
    }

    
    /**
     * Returns the ID of the waypoint.
     * 
     * @return the ID of the waypoint as a long
     */
    public long getId() {
        return id;
    }

    
    /**
     * Sets the ID of the waypoint.
     * 
     * @param id an integer value
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     * Returns the timestamp of the location, will include the date and time.
     * 
     * @return the timestamp as a float
     */
    public float getTimestamp() {
        return timestamp;
    }

    
    /**
     * Sets the timestamp of the waypoint, will set the date and time
     * the waypoint was created.
     * 
     * @param timestamp a float value representing date and time
     */
    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    
    /**
     * Returns the photo as a string, this allows the picture to be
     * uploaded to the webserver via a HTTP Post.
     * @see HTTPPostSender
     * 
     * @return a String representation of the photo
     */
    public String[] getPhotos() {
        return photos;
    }

    
    /**
     * Will assign a photo to the waypoint.
     * 
     * @param photo as a string representation
     */
    public void setPhoto(String[] photo) {
        this.photos = photo;
    }

    
    /**
     * Returns the description of the waypoint.
     * 
     * @return as a String
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Sets the waypoint with a specified description.
     * 
     * @param description is a String the user specifies.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}