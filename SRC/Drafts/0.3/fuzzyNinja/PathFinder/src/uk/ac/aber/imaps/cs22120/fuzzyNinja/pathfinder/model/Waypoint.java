/*
 * Scope: This class is a extension of a location on the route, holding information about the current point.
 * */

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import android.graphics.Bitmap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Waypoint
{
    private double latitude,longitude;  //GPS coordinates for the waypoint
	private String name;                //Name given to the waypoint
	private String description;         //Description given to the waypoint
    private Bitmap bitmap;              //Photo associated with the waypoint
	private Date timestamp;             //The date and time the waypoint was recorded
    private DateFormat dateFormat;      // "yyyy/MM/dd HH:mm:ss"


    /**
     * Default constructor
     */
    public Waypoint()
    {

    }


    /**
     * Constructor assigns the GPS coordinates and the timestamp
     *
     * @param latitude latitude coordinate
     * @param longitude longitude coordinate
     * @param timestamp date and time
     */
	public Waypoint(double latitude, double longitude,Date timestamp)
	{
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.setCoordinates(latitude, longitude);
        this.setTimestamp(timestamp);
	}


	public double getLatitude()
    {
        return latitude;
	}


    public double getLongitude()
    {
        return longitude;
    }


	public void setCoordinates(double lat, double lon)
    {
		latitude = lat;
        longitude = lon;
	}


	public String getName()
    {
		return name;
	}


	public void setName(String n)
    {
		name = n;
	}


	public String getDescription()
    {
		return description;
	}


	public void setDescription(String d)
    {
		description = d;
	}


	public Bitmap getPhoto()
    {
        return bitmap;
	}

	
	public void setPhoto(Bitmap b)
    {
		bitmap = b;
	}


    /**
     * Returns the timestamp object.
     *
     * @return timestamp as a Date object.
     */
	public Date getTimestamp()
    {
		return timestamp;
	}


    /**
     * Returns the timestamp as a string.
     *
     * @return String returned is in "yyyy/MM/dd HH:mm:ss" format.
     */
    public String getTimeStampString()
    {
        return dateFormat.format(timestamp);
    }


	public void setTimestamp(Date t)
    {
	timestamp = t;
	}
}
