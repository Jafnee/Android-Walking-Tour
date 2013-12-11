package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class RouteLocationManager implements Runnable, LocationListener{
	
	private Location location;
	
	private Route route;
	
	public RouteLocationManager()
	{
		
	}
	
	public RouteLocationManager(Route route)
	{
		this.route = route;

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
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error - GPS unavailable");
		}
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		this.notify();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
