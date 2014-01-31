/*
 *@(#)Route.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
package cs221.group15.pathfinder;

/**
 * The Route class will represent a Walking Tour.
 * The route will consists of multiple waypoints.
 * The waypoints will be stored in an array list of Waypoint objects {@link #waypoint}.
 * @see Waypoint
 * 
 * @author
 * @since
 * @version
 */
public class Route {
    private long id;
    private String shortDescription;
    private String longDescription;
    private String title;
    private float hours;
    private float distance;

    private Waypoint[] waypoint = null;
    
    
	/**
	 * This class constructor will create a walking tour and fill it in with the title,
	 * short description and long description the user has specified. 
	 * 
	 * @param title the name given to the walking tour
	 * @param shortDescription the short description of the tour, limited to 100 characters
	 * @param longDescription the longer description of the tour, limited to 1000 characters
	 */
    Route(String title, String shortDescription, String longDescription) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
    
    
	/**
	 * Sets the ID number of the walking tour.
	 * 
	 * @param id is a unique number for the walking tour, allowing it to be identified
	 */
    public void setId(long id) {
        this.id = id;
    }
    
    
	/**
	 * Returns the walking tour's ID as a long.
	 * This
	 * 
	 * @return ID is returned as a long
	 */
    public long getId() {
        return this.id;
    }
    
    
	/**
	 * Will assign the walking tour with a new short description.
	 * 
	 * @param shortDescription
	 */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Returns the short description of the walking tour.
     * 
     * @return shortDescription as a string
     */
    public String getShortDescription() {
        return this.shortDescription;
    }

    
    /**
     * Will assign the walking tour with a new long description.
     * 
     * @param longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    
    /**
     * Returns the long description of the walking tour.
     * 
     * @return longDescription as a string
     */
    public String getLongDescription() {
        return this.longDescription;
    }

    
    /**
     * 
     * @param hours
     */
    public void setHours(float hours) {
        this.hours = hours;
    }

    
    /**
     * 
     * @return
     */
    public float getHours() {
        return this.hours;
    }

    
    /**
     * 
     * @param distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    
    /**
     * 
     * @return
     */
    public float getDistance() {
        return this.distance;
    }
    
    
    /**
     * This will set waypoint {@link Route#waypoint} to the specified
     * array of Waypoint objects.
     * @see Waypoint
     * 
     * @param waypoints the array of Waypoint objects
     */
    public void setWaypoints(Waypoint[] waypoints) {
        this.waypoint = waypoints;
    }

    
    /**
     * Will set the walking tour's title.
     * 
     * @param title is the specified String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    
    /**
     * Returns the title of the walking tour.
     * 
     * @return the title as a String
     */
    public String getTitle() {
        return this.title;
    }

    
    /**
     * Will return the number of waypoints in the walking tour.
     * 
     * @return the number of waypoints as an int value
     */
    public int getTotalWaypoints() {
    	return this.waypoint.length; 
    }

    
    /**
     * Will return a specified waypoint from the array.
     * 
     * @param i the index of the waypoint in the array
     * @return an object of Waypoint
     */
    public Waypoint getWaypoint(int i) {
    	return this.waypoint[i]; 
    }

}