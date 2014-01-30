package cs221.group15.pathfinder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class ActivitySaveWalk extends Activity {
    private Route currentRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_walk);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_save_walk, menu);
        return true;
    }

    public void saveToLocal() {
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.addRoute(currentRoute); /* Assuming there are methods inside dbHandler to interrogate each Waypoint's methods. */
    }

    public void loadFromLocal() {
        DatabaseHandler dbHandler = new DatabaseHandler(this);

        //currentRoute = dbHandler.getRoute(); /* dbHandler should have a method to examine the database, define a Route object and return it. */
    }

    public void sendToServer() {
        HTTPPostSender sender = new HTTPPostSender();
        sender.buildString(currentRoute);
        if(sender.trySend()) {
            Toast.makeText(ActivitySaveWalk.this, "Walk successfully sent to server", Toast.LENGTH_LONG);
            finish();
        } else {
            Toast.makeText(ActivitySaveWalk.this, "Error sending walk to server", Toast.LENGTH_LONG);
        }
    }
    
}
