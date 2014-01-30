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

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //CREATE VARIABLES
    private Button btnWaypoint, btnWalk, btnDB,btnSaveWalk;
    private Route currentRoute;
    private static final int CREATE_WALK_REQUEST = 1;
    private static final int CREATE_WAYPOINT_REQUEST = 2;

    private float lat;
    private float lng;


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

        }

        return super.onOptionsItemSelected(item);
    }

    private void showViewToursActivity() {
//		Intent intent = new intent(this, ActivityViewTours.class);
        System.out.println("Not Yet Implemented");


    }

    private void showCreateWalkActivity() {
        Intent intent = new Intent(this, ActivityNewWalk.class);
        this.startActivityForResult(intent, CREATE_WALK_REQUEST);

    }

    private int REQUEST_CODE = 1;

    private void showAddWayPointActivity() {
        Intent intent = new Intent(this, ActivityNewWaypoint.class);
        this.startActivityForResult(intent,CREATE_WAYPOINT_REQUEST);



    }

    public void updateLocation(Location location) {
        this.lat = (float) location.getLatitude();
        this.lng = (float) location.getLongitude();
    }


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
                Toast.makeText(this.getApplicationContext(),"YOU FUCKED UP BOII",Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
                Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

    }

    /*http://stackoverflow.com/questions/5459290/how-to-check-my-internet-access-on-android*/
    public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(this.getApplicationContext().CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();


    }

    public void show(double i, double j) {
        Toast.makeText(this.getApplicationContext(),String.valueOf(this.lat) + " " + String.valueOf(this.lng),Toast.LENGTH_LONG).show();
    }

}