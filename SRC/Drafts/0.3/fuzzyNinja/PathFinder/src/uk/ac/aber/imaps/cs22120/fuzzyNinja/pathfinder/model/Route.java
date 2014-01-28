/* 
 * Author: Joshua C Moss
 * Date Created:
 * Date Last Modified:
 * Co Authors:
 * 
 * 
 * */

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import java.util.Date;
import java.util.LinkedList;

public class Route 
{


	private String title;                       //Title of the walking tour
	private String shortDescription;            //Short description of the walking tour
	private String longDescription;             //Long description of the walking tour
	private LinkedList<Waypoint> waypoints;     //List of the waypoints along the walking tour
    private Waypoint waypoint;


    /**
     * Default constructor
     */
    public Route()
    {

    }

    /**
     * This constructor will create an object of Route with the
     * minimum information required.
     *
     * @param title The title for the walking tour object
     */
	public Route(String title)
	{
		this.setTitle(title);
		waypoints = new LinkedList<Waypoint>();
	}

    /**
     * Sets the title of the walking tour
     *
     * @param title The title of the walking tour
     */
    public void setTitle(String title)
    {
        title = title;
    }

    /**
     * Gets the title of the walking tour
     *
     * @return title of the walking tour
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * Sets the short description of the walking tour
     *
     * @param s The short description for the walking tour
     */
    public void setShort(String s)
    {
        shortDescription = s;
    }


    /**
     * Gets the short description of the walking tour
     *
     * @return Returns the short description of the walking tour
     */
    public String getShort()
    {
        return shortDescription;
    }


    /**
     * Sets the long description of the walking tour
     *
     * @param l The long description of the walking tour
     */
    public void setLong(String l)
    {
        longDescription = l;
    }


    /**
     * Gets the long description of the walking tour
     *
     * @return Returns the long description of the walking tour
     */
    public String getLong()
    {
        return longDescription;
    }


    /**
     * Creates and adds the waypoint to the end of the list
     *
     * @param wLatitude The latitude of the waypoint
     * @param wLongitude the longitude of the waypoint
     * @param wTimestamp The timestamp of the waypoint
     */
	public void addLocation(double wLatitude, double wLongitude,Date wTimestamp)
    {
        waypoint = new Waypoint(wLatitude, wLongitude, wTimestamp);
		waypoints.addLast(waypoint);
	}

    /**
     * Gets a waypoint from the list at the specified index
     *
     * @param index The index of the location
     * @return Returns an an object of Waypoint
     */
	public Waypoint getLocation(int index)
    {
		return waypoints.get(index);
	}


    /**
     * Deletes the waypoint at the specified index
     *
     * @param index The index of the waypoint to be deleted
     */
    public void deleteLocation(int index)
    {
        waypoints.remove(index);
    }


}
