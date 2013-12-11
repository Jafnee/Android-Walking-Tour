package uk.ac.aber.cs22120.fuzzyNinja.pathFinder;

import uk.ac.aber.cs22120.fuzzyNinja.pathFinder.WalkLocation;
import java.util.ArrayList;

/**
* This class contains information associated with a walking tour.
* 
*/
public class Walk {
	private String title; // The title of the walking tour
	private String shortDescription; // A short description of the walking tour
	private String longDescription; // A long description of the walking tour
	private ArrayList<WalkLocation> waypoints; // A list of the waypoints along the walking tour
	
	/** Sets the title of a given walking tour
	* @param title the title of the walking tour
	*/
	public void setTitle(String title) {}
	
	/** Gets the title of the walking tour
	* @return Returns the title of the walking tour
	*/
	public String getTitle() {}
	
	/** Sets the short description of the walking tour
	* @param description the short description for the walking tour
	*/
	public void setShort(String description) {}
	
	/** Gets the short description of the walking tour
	* @return Returns the short description for the walking tour
	*/
	public String getShort() {}
	
	/** Sets the long description of the walking tour
	* @param description the long description for this walking tour
	*/
	public void setLong(String description) {}
	
	/** Gets the long description of the walking tour
	* @return Returns the long description for the walking tour
	*/
	public String getLong() {}
	
	/** Adds a location to the end of the list
	* @param location the location added to the list
	*/
	public void addLocation(WalkLocation location) {}
	
	/** Gets a location from the queue at the specified index
	* @param index the index from which to get the location
	*/
	public WalkLocation getLocation(int index) {}
	
	/** Deletes a the location at the specified index
	* @param index at which to delete location
	*/
	public void deleteLocation(int index) {}
	
	/**Default constructor*/
	public Walk() {}
}