package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.view;

import uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.R.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu actionBar) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(menu.activity_main, actionBar);
		return true;
	}

}