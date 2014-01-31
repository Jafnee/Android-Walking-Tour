/*
 *@(#)LocationListen.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
package cs221.group15.pathfinder;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * This class handles the GPS coordinates that will be assigned to
 * the waypoints. The class will have an object of type Location
 * that will update itself when the user moves to a new position,
 * the GPS coordinates will then be acquired from these positions.
 * @see Waypoint
 * 
 * 
 * 
 * @author 	lpd1
 * @since 	0.5
 * @version 1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 */
public class LocationListen implements LocationListener {
    MainActivity parent;

    LocationListen(MainActivity parent) {
        this.parent=parent;
    }

    
    /**
     * This method will be called if the current location update does not
     * match the last known location.
     * The new Location object will be sent to the MainActivity.
     * @see MainActivity
     */
    @Override
    public void onLocationChanged(Location location) {
        parent.updateLocation(location);
        parent.show(location.getLatitude(),location.getLongitude());
    }

    
    /**
     * Called when the provider status changes.
     */
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    
    /**
     * Called when the provider is enabled by the user.
     */
    @Override
    public void onProviderEnabled(String s) {

    }

    
    /**
     * Called when the provider is disabled by the user.
     */
    @Override
    public void onProviderDisabled(String s) {

    }
}
