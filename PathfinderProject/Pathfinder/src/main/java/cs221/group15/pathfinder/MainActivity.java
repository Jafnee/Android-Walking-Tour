/*
Java class relating to the Main Activity - the first screen seen when starting the app
@author Group15
 */

package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //CREATE VARIABLES
    private Button btnWaypoint, btnWalk, btnDB,btnSaveWalk;
    private Route currentRoute;
    private static final int CREATE_WALK_REQUEST = 1;
    private static final int CREATE_WAYPOINT_REQUEST = 2;


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

    private void showSaveWalkActivity() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

      //  bundle.putParcelable("route.constant",currentRoute); // Replace with key defined in constants file
        intent.putExtras(bundle);
        intent.setClass(this, ActivitySaveWalk.class);
        this.startActivity(intent);
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
                showSaveWalkActivity();
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

                    //Waypoint waypoint = new Waypoint(null,"Test",descText,null);

                    DatabaseHandler bhhandle = new DatabaseHandler(this.getApplicationContext());
                    //bhhandle.addWaypoint(waypoint);
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

                    if(currentRoute.getId() == null )
                }
            } catch (NullPointerException e) {
                Toast.makeText(MainActivity.this, "Something bad happened", Toast.LENGTH_LONG).show();
            }
        }

    }

}