package cs221.group15.pathfinder;

/*
 *@(#)Route.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
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
	 * This class constructor will create a walking tour and fill it in with the title, short description
	 * and long description the user has specified.
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
	 * Sets the ID number of the walking tour
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
	 * Will assign the walking tour with a new short description
	 * 
	 * @param shortDescription
	 */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Returns the short description of the walking tour
     * 
     * @return shortDescription as a string
     */
    public String getShortDescription() {
        return this.shortDescription;
    }

    
    /**
     * Will assign the walking tour with a new long description
     * 
     * @param longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    
    /**
     * Returns the long description of the walking tour
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

    public float getHours() {
        return this.hours;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return this.distance;
    }
    public void setWaypoints(Waypoint[] waypoints) {
        this.waypoint = waypoints;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTotalWaypoints() { return this.waypoint.length; }

    public Waypoint getWaypoint(int i) { return this.waypoint[i]; }

}