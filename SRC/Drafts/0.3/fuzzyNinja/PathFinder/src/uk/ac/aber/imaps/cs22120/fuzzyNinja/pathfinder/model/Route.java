package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import java.util.LinkedList;

public class Route 
{

//	private Queue<RouteLocation> routeLocation;
	private LinkedList<RouteLocation> routeLocation;
	
	public Route()
	{
		
		routeLocation = new LinkedList<RouteLocation>();//Queue<RouteLocation>();
		
		
	}
	
	public void addLocation(Waypoint location){
		routeLocation.addLast(location);
	}
	
}
