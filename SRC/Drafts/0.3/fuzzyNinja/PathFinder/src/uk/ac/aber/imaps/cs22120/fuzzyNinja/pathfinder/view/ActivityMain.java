package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.view;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityMain extends FragmentActivity {

	private GoogleMap routeMap;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
	   routeMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	   routeMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(52.41370, -4.08655)));
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAddWaypoint:
			showAddWayPointActivity();
			break;
		case R.id.menuCreateNewWalk:
			showCreateWalkActivity();
			break;
		case R.id.menuViewTours:
			showViewToursActivity();
			break;
		case R.id.menuSettings:
			showSettingsActivity();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void showSettingsActivity() {
		Intent intent = new Intent(this, ActivitySettings.class);
		startActivity(intent);
	}

	private void showViewToursActivity() {
//		Intent intent = new intent(this, ActivityViewTours.class);
		System.out.println("Not Yet Implemented");

		
	}

	private void showCreateWalkActivity() {
		Intent intent = new Intent(this, ActivityNewWalk.class);
		startActivity(intent);

	}

	private void showAddWayPointActivity() {
		Intent intent = new Intent(this, ActivityNewWaypoint.class);
		startActivity(intent);
	}

}