/*
 * @Author: Joshua C H Moss
 * @Date Created: 25/11/2013
 * @Date Last Modified: 25/11/2013
 * 
 * 
 */

package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.view;


import uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;


public class ActivityRegister extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
	}
	
	public void btnCreateAccountClicked(View view){
		
		//Code to check input data is valid
		//Code to create an account in db
		//Code to move to ActivityHome
		//
		//Remark : Consider progressBar_Register.setVisibility(View.Visible); //set loading spinner to visible when loading.
		//Remark : Ensure both password fields hold same input, including case to verify correct password entry
		//
		//TaskId : SE_fuzzyNinja(Group15)_View_201
		
	}
	
	public void btnCancelCreateAccountClicked(View view)
	{
		
	}
	
}
