/*
 * @Author: Joshua C H Moss
 * @Date Created: 25/11/2013
 * @Date Last Modified: 25/11/2013
 * 
 * 
 */

package uk.ac.aber.cs22120.fuzzyNinja.pathFinder.View;

import uk.ac.aber.cs22120.fuzzyNinja.pathFinder.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class ActivityLogin extends Activity {

	private Button btnLogin; 
	private ProgressBar progressBar_Login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		btnLogin = (Button) findViewById(R.id.btnLogin);
		progressBar_Login = (ProgressBar) findViewById(R.id.progressBar_Login);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	public void btnLoginClicked(View view){
		
		//Code to connect to user accounts table, and confirm password.
		//Code to show main screen Activity upon successful login.
		//Code to cover if invalid login
		//
		//Remark : Consider progressBar_Login.setVisibility(View.Visible); //set loading spinner to visible when loading.
		//
		//TaskId : SE_fuzzyNinja(Group15)_View_101
		
	}
	
	public void btnRegisterClicked(View view){
		
		setContentView(R.layout.activity_register);
		//
		//TaskId : SE_fuzzyNinja(Group15)_View_102
		
	}
	
}