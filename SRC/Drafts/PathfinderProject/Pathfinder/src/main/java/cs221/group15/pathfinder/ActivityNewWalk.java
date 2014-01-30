package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
	
	public void btnCreateWalkClicked(View view){


        EditText title = (EditText) findViewById(R.id.txtbxWalkName);
        EditText shortDes = (EditText) findViewById(R.id.txtbxShortDes);
        EditText lngDes = (EditText) findViewById(R.id.txtbxLongDescription);

        String titleString = title.getText().toString();
        String shortString = shortDes.getText().toString();
        String longString = lngDes.getText().toString();

        Intent result = new Intent();
        result.putExtra("TITLE_STRING",titleString);
        result.putExtra("SHORT_STRING",shortString);
        result.putExtra("LONG_STRING",longString);

        setResult(Activity.RESULT_OK,result);
        finish();
	}

}