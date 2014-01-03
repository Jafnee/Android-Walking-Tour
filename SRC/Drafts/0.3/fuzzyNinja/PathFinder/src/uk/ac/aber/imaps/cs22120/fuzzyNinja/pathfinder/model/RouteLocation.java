/* 
 * Author: Joshua C Moss
 * Date Created:
 * Date Last Modified:
 * Co Authors:
 * 
 * 
 * */

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import android.location.Location;

public class RouteLocation 
{

	/*
	 * Scope: This class is an object for holding the GPS coordinates of a section of the route.
	 * */
	
	private Location location;
	private Model model;
	
	
	public RouteLocation(){
		
	}
	
	public RouteLocation(Model model)
	{
		this.model = model;
		
		
		
	}
	
	public void setLocation()
	{

	}
	
	public double getLatitude()
	{
		return location.getLatitude();
	}
	public double getLongitude()
	{
		return location.getLongitude();
	}
	public double getAccuracy()
	{
		return location.getAccuracy();
	}
	
}
