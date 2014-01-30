package cs221.group15.pathfinder;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by lews on 30/01/14.
 */
public class LocationListen implements LocationListener {
    MainActivity parent;

    LocationListen(MainActivity parent) {
        this.parent=parent;

    }

    @Override
    public void onLocationChanged(Location location) {
        parent.updateLocation(location);
        parent.show(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
