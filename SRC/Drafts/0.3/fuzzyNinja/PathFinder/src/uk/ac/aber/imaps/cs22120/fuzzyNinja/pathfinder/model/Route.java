/* 
 * Author: Joshua C Moss
 * Date Created:
 * Date Last Modified:
 * Co Authors:
 * 
 * 
 * *

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import java.util.LinkedList;

public class Route 
{

//	private Queue<RouteLocation> routeLocation;
	private String title;
	private String shortDescription
	private String longDescription
	private LinkedList<RouteLocation> routeLocation;
	
	public Route()
	{
		
		routeLocation = new LinkedList<RouteLocation>();//Queue<RouteLocation>();
		
		
	}
	
	public void addLocation(Waypoint location){
		routeLocation.addLast(location);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public String getShort() {
		return shortDescription;
	}
	
	public void setShort(String s) {
		shortDescription = s;
	}
	
	public String getLong() {
		return longDescription;
	}
	
	public void setLong(String l) {
		longeDescription = l;
	}
	
	public WayPoint getLocation() {
		return routeLocation; //temp
	}
}
**/