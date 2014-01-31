/*
 *@(#)MainActivity.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
/*
Java class relating to the Main Activity - the first screen seen when starting the app
@author Group15
 */

package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Is the starting screen that the user navigates from by using the main buttons.
 * @see ActivityNewWalk
 * @see ActivityNewWaypoint
 * 
 * @author	jap38
 * @author 	lpd1
 * @author	gad16
 * @author	owd2
 * @author 	jam67
 * @author 	jaj42
 * @since	0.3
 * @version	1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //CREATE VARIABLES
    private Button btnWaypoint, btnWalk, btnDB,btnSaveWalk;
    private Route currentRoute;
    private static final int CREATE_WALK_REQUEST = 1;
    private static final int CREATE_WAYPOINT_REQUEST = 2;

    private float lat;
    private float lng;


    /**
     * This method is called when the activity is first created.
     * The buttons are created and have onClickListeners assigned to them which
     * should detect when the user taps on the button.
     * A reference to the location manager is acquired and a listener that responds
     * to location updates is defined. The listener is registered to the location manager to
     * receive location updates.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWaypoint = (Button) findViewById(R.id.btnNewWalk);
        btnWalk = (Button) findViewById(R.id.btnNewWaypoint);
        btnSaveWalk = (Button) findViewById(R.id.btnSaveWalk);

        btnWaypoint.setOnClickListener(this);
        btnWalk.setOnClickListener(this);
        btnSaveWalk.setOnClickListener(this);


        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListen locationListener = new LocationListen(this);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(loc != null) {
            this.show(loc.getLatitude(),loc.getLongitude());
        }
    }

    
    /**
     * This method inflates the menu and adds items to the action bar and if the action bar
     * is present; items are added to it.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    /**
     * This hook is called whenever an item in the menu has been selected by the user.
     * It will open the respective activity the user has selected.
     */
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
        }
        return super.onOptionsItemSelected(item);
    }

    
    /**
     * Will show all of the walking tours that have been saved onto the phone's database.
     */
    private void showViewToursActivity() {
//		Intent intent = new intent(this, ActivityViewTours.class);
        System.out.println("Not Yet Implemented");
    }

    
    /**
     * Opens the the walk creation activity.
     * @see ActivityNewWalk
     */
    private void showCreateWalkActivity() {
        Intent intent = new Intent(this, ActivityNewWalk.class);
        this.startActivityForResult(intent, CREATE_WALK_REQUEST);
    }

    private int REQUEST_CODE = 1;

    
    /**
     * Opens the activity that allows the user to add new waypoints.
     * @see ActivityNewWaypoint
     */
    private void showAddWayPointActivity() {
        Intent intent = new Intent(this, ActivityNewWaypoint.class);
        this.startActivityForResult(intent,CREATE_WAYPOINT_REQUEST);
    }

    
    /**
     * Will acquire the latitude and longitude coordinates from the current
     * location object and assign them to lat and long variables
     * 
     * @param location
     */
    public void updateLocation(Location location) {
        this.lat = (float) location.getLatitude();
        this.lng = (float) location.getLongitude();
    }


    /**
     * Overrides the onClick event listener
     * 
     */
    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View view) {
        System.out.println(view.getId());
        switch(view.getId()){
            case R.id.btnNewWalk:
                showCreateWalkActivity();
                break;
            case R.id.btnNewWaypoint:
                showAddWayPointActivity();
                break;
            case R.id.btnSaveWalk:
                saveWalk();
                break;
        }
    }

    
    /**
     * Overrides the onActivityResult method, to create a new Waypoint and save into the database
     */
    /*
     * (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            try {
                if(requestCode == CREATE_WAYPOINT_REQUEST) {
                    Bundle extras = data.getExtras();
                    String descText = data.getStringExtra("DESC_TEXT");
                    String[] images = data.getStringArrayExtra("IMAGES_LIST");

                    Waypoint waypoint = new Waypoint(currentRoute.getId());
                    waypoint.setDescription(descText);
                    waypoint.setPhoto(images);
                    waypoint.setTimestamp(System.currentTimeMillis());
                    waypoint.setLat(this.lat);
                    waypoint.setLng(this.lng);
                    /*
                        GPS CODE - OR getLastKnownPosition()
                     */


                    DatabaseHandler bhhandle = new DatabaseHandler(this.getApplicationContext());
                    bhhandle.addWaypoint(waypoint);


                    //Toast.makeText(this.getApplicationContext(),descText,1000);
                    //STORE IN DATABASE
                    //SEND TO SERVER
                    //SUMMON CTHULHU
                } else if (requestCode == CREATE_WALK_REQUEST) {
	                    /*Handle the new route*/
	                    Bundle extras = data.getExtras();
	
	                    String shortDes = data.getStringExtra("SHORT_STRING");
	                    String longDes = data.getStringExtra("LONG_STRING");
	                    String title = data.getStringExtra("TITLE_STRING");
	
	                    this.currentRoute = new Route(title,shortDes,longDes);
	
	                    DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
	                    currentRoute.setId(db.addRoute(currentRoute));
	                    TextView label = (TextView) findViewById(R.id.txtTourRunning);
	                    label.setText("Current Tour: " + currentRoute.getTitle());
	
	                    if(currentRoute.getId() <= 0) {
	                        Toast.makeText(MainActivity.this,"Database Write Failure", Toast.LENGTH_LONG).show();
                    } else {
                    	
                    	}
                	}
            	} catch (NullPointerException e) {
                Toast.makeText(MainActivity.this, "Something bad happened", Toast.LENGTH_LONG).show();
            }
        }
    }

    
    /**
     * Retrieves the walk from the local database and sends it webserver using HTTP POST
     */
    public void saveWalk() {
        try {
            long id = this.currentRoute.getId();
            DatabaseHandler db = new DatabaseHandler(this.getApplicationContext());
            currentRoute.setWaypoints(db.getAllWaypoints(id));

            HTTPPostSender postSender = new HTTPPostSender();
            postSender.buildString(currentRoute, this.getContentResolver());
            postSender.cm = (ConnectivityManager) getSystemService(this.getApplicationContext().CONNECTIVITY_SERVICE);




            if(isOnline()) {
                postSender.execute(this.currentRoute);
                Toast.makeText(this.getApplicationContext(),"Walk successfully sent to server",Toast.LENGTH_LONG).show();

            } else {
                //oh god why..
                Toast.makeText(this.getApplicationContext(),"Something bad happened",Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
                Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
    }

    
    /**
     * 
     * @return True if phone is online
     */
    public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(this.getApplicationContext().CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}