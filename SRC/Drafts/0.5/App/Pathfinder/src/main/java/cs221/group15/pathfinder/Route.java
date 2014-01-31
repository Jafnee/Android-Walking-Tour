/*
 *@(#)Route.java 1.0 2014-01-31
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
 * The Route will also be sent over the Internet to a web server where it will be stored
 * and the tour will be viewable on a map; once the user is done recording the walk.
 * @see HTTPPostSender
 * 
 * @author 	lpd1
 * @author 	gad16
 * @since	0.1
 * @version 1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
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
     * Sets the amount of time the user takes to record the walking tour,
     * by totaling up the time it takes to reach one way point to another.
     * The time is measured in hours.
     * 
     * @param hours is the total amount of time to complete a walking tour as a float
     */
    public void setHours(float hours) {
        this.hours = hours;
    }

    
    /**
     * Returns the amount of time required to complete a walking tour
     * in hours.
     * 
     * @return the number of hours as a float
     */
    public float getHours() {
        return this.hours;
    }

    
    /**
     * Sets the total distance of the walking tour in meters.
     * This is done by getting the distances of one waypoint to
     * another and getting the sum of them.
     * 
     * @param distance the distance to be set for the Route
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    
    /**
     * Returns the distance of a walking tour from start to finish and
     * while going through all of the waypoints.
     * 
     * @return the distance as a float
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
     * @return the number of waypoints as an integer value
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