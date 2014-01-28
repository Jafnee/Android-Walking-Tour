package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        this.startActivity(intent);

    }

    private void showAddWayPointActivity() {
        Intent intent = new Intent(this, ActivityNewWaypoint.class);
        this.startActivity(intent);
    }

}