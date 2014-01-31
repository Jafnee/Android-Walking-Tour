/*
 *@(#)ActivityNewWalk.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Is the activity to create a new walking tour.
 * 
 * @author	lpd1
 * @author 	gad16
 * @author	jap38
 * @since	0.3
 * @version	1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 *
 */
public class ActivityNewWalk extends Activity {

	
	/**
     * When the activity is created the method is executed.
     * The method sets up the button for the user to go back to the main activity
     * and has a listener, to detect when the user selects it.
     */
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

	
	/**
	 * When an item on the menu is selected the method is called.
	 * When the user taps on the home button they are brought back to
	 * the main activity.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			startActivity(new Intent(this,MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * When the user selects the create walk button, a new tour walk will be created with
	 * the title and descriptions the user has specified.
	 * 
	 * @param view
	 */
	public void btnCreateWalkClicked(View view){



        EditText title = (EditText) findViewById(R.id.txtbxWalkName);
        EditText shortDes = (EditText) findViewById(R.id.txtbxShortDes);
        EditText lngDes = (EditText) findViewById(R.id.txtbxLongDescription);

        String titleString = title.getText().toString();
        String shortString = shortDes.getText().toString();
        String longString = lngDes.getText().toString();

        if(titleString.equals("")) {
            title.setError("Please Enter A Title");
        } else if(shortString.equals("")) {
            shortDes.setError("Please Enter A Short Description");
        } else if(longString.equals("")) {
            lngDes.setError("Please Enter A Long Description");
        } else {
            Intent result = new Intent();
            result.putExtra("TITLE_STRING",titleString);
            result.putExtra("SHORT_STRING",shortString);
            result.putExtra("LONG_STRING",longString);

            setResult(Activity.RESULT_OK,result);
            finish();
        }
	}
}