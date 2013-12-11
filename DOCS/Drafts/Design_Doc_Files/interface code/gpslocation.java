package uk.ac.aber.cs22120.fuzzyNinja.pathFinder;

public class GPSLocation {
	private double latitude; // Represents a latitude, given in degrees
	private double longitude; // Represents a longitude, given in degrees
	
	/** Sets the latitude
	* @param lat the latitude in degrees
	*/
	public void setLatitude(double lat);
	
	/** Gets the latitude
	* @return Returns the latitude in degrees
	*/
	public double getLatitude();
	
	/** Sets the longitude
	* @param lng the longitude in degrees
	*/
	public void setLongitude(double lng);
	
	/** Gets the longitude
	* @return Returns the longitude in degrees
	*/
	public double getLongitude();
}
