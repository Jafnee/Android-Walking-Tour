package uk.ac.aber.cs22120.fuzzyNinja.pathFinder;

import uk.ac.aber.cs22120.fuzzyNinja.pathFinder.GPSLocation;
import java.util.ArrayList;
import java.util.Date;

/** Specifies a waypoint in the walking tour
*
*/
public class WalkLocation {
	private GPSLocation coordinates; // GPS coordinates for the waypoint
	private String name; // Name given to the waypoint
	private String description; // Description given to the waypoint
	private ArrayList<Bitmap> photos; // List of photos associated with the waypoint
	private Date timestamp; // The date and time the waypoint was recorded
	
	/** Default constructor for this class
	* GPS coordinates and timestamp should be assigned in this method
	*/
	public WalkLocation() {}
	
	/** Adds a Bitmap object to the list of photos
	* @param photo photo to be added
	*/
	public void addToPhotos(Bitmap photo) {}
	
	/** Gets a Bitmap object from list of photos
	* @param index index of the required Bitmap object
	* @return Returns the Bitmap object at the specified index
	*/
	public Bitmap getFromPhotos(int index) {}
	
	/** Deletes an element at the specified index
	* @param index index of the Bitmap to be deleted
	*/
	public void removeFromPhotos(int index) {}
}