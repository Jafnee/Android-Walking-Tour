/*
 * Scope: This class is a extension of a location on the route, holding information about the current point.
 * */

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

public class Waypoint extends RouteLocation 
{
	private Location coordinates;
	private String name;
	private String description;
	//photos
	private Date timestamp;
	
	public Waypoint()
	{		
		
	}
	
	public Location getCoordinates() {
		//get coords
	}
	
	public void setCoordinates(double lat, double lng) {
		//sets coords
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
//	public PICTUREDATATYPE getPhoto() {
//		//return photo
//	}
	
//	public void setPhoto(PICTUREDATATYPE p) {
//		photos = p;
//	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date t) {
	timestamp = t;
	}
}
