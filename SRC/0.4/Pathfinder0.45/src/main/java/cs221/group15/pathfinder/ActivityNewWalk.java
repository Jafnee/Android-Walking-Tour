package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

public class ActivityNewWalk extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_walk);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		    getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(this,MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void btnCreateWalkClicked(View view){
        /*
        This method needs:
            - Code to store the new walk in the database
            - Code to tell the app that there's a walk running
                (without using a global variable. We're not cavemen)
         */
        finish();
	}

}