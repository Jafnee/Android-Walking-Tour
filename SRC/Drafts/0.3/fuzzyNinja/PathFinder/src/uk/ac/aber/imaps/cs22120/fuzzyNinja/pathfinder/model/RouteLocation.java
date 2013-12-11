package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import android.location.Location;

public class RouteLocation 
{

	/*
	 * Scope: This class is an object for holding the GPS coordinates of a section of the route.
	 * */
	
	private Location location;
	
	public RouteLocation()
	{
		
		
		
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
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