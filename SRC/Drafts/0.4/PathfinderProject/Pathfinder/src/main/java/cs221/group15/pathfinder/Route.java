package cs221.group15.pathfinder;

/**
 * Created by gad16 on 29/01/2014.
 * Modified by lpd1 on 30/01/2014
 */

public class Route {
    private long id;
    private String shortDescription;
    private String longDescription;
    private String title;
    private float hours;
    private float distance;

    private Waypoint[] waypoint = null;

    Route(String title, String shortDescription, String longDescription) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public float getHours() {
        return this.hours;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return this.distance;
    }
    public void setWaypoints(Waypoint[] waypoints) {
        this.waypoint = waypoints;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTotalWaypoints() { return this.waypoint.length; }

    public Waypoint getWaypoint(int i) { return this.waypoint[i]; }

}