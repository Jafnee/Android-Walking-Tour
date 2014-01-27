/* 
 * Author: Joshua C Moss
 * Date Created:
 * Date Last Modified:
 * Co Authors: None
 * 
 * 
 * */

/**package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class RouteLocationManager implements Runnable, LocationListener{
	
//	private Location location;
//	
//	private Route route;
		
	private LocationManager locMan;

	
	public RouteLocationManager()
	{
		setup();
	}
	
	public void setup()
	{
		locMan = (LocationManager) Context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public RouteLocationManager(Route route)
	{
		//this.route = route;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		
	}
	
	
	
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		try {
			wait();
		} catch (InterruptedException tErr) {
			tErr.printStackTrace();
			System.out.println("Error - Location Service unavailable");
		}
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		try {
		notify();
		} catch (InterruptedException tErr) {
			tErr.printStackTrace();
			System.out.println("Error - Cannot gain Locaton Service Handle");
		}
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}**/
