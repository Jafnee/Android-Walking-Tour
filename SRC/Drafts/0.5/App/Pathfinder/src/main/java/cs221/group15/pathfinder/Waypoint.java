package cs221.group15.pathfinder;

/**
 * Created by gad16 on 29/01/2014.
 */

public class Waypoint {
    private float lat;
    private float lng;
    private long id;
    private long walkID;
    private float timestamp;

    private String[] photos;
    private String description;

    Waypoint(long id, long walkID) {
        this.id = id;
        this.walkID = walkID;
    }

    Waypoint(long walkID) {
        this.walkID = walkID;
    }
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public long getWalkID() {
        return walkID;
    }

    public void setWalkID(int walkID) {
        this.walkID = walkID;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhoto(String[] photo) {
        this.photos = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}