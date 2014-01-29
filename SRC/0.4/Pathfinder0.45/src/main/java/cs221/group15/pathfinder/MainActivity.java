/*
Java class relating to the Main Activity - the first screen seen when starting the app
@author Group15
 */

package cs221.group15.pathfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private Button btnWaypoint, btnWalk, btnTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWaypoint = (Button) findViewById(R.id.btnNewWalk);
        btnWalk = (Button) findViewById(R.id.btnNewWaypoint);
        btnTour = (Button) findViewById(R.id.btnViewTours);
        btnWaypoint.setOnClickListener(this);
        btnTour.setOnClickListener(this);
        btnWalk.setOnClickListener(this);
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
		Intent intent = new Intent(this, ActivityViewTours.class);
        this.startActivity(intent);
    }

    private void showCreateWalkActivity() {
        Intent intent = new Intent(this, ActivityNewWalk.class);
        this.startActivity(intent);
    }

    private void showAddWayPointActivity() {
        Intent intent = new Intent(this, ActivityNewWaypoint.class);
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
            case R.id.btnViewTours:
                showViewToursActivity();
                break;
        }
    }

}