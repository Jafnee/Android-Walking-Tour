package cs221.group15.pathfinder;

import cs221.group15.pathfinder.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class ActivityNewWaypoint extends Activity implements View.OnClickListener{
    private Uri outputURI;
    private ArrayList<String> images = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_waypoint);
		// Show the Up button in the action bar.
		setupActionBar();

        ImageButton imgbutton = (ImageButton) findViewById(R.id.btnTakePhoto);
        imgbutton.setOnClickListener(this);
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
            startActivity(new Intent(this, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void btnCreateWaypoint(View view){
        String description;

        //http://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android
        EditText descText = (EditText) findViewById(R.id.editText1);
		Intent resultIntent = new Intent();
        String[] imgs = new String[images.size()];
        imgs = images.toArray(imgs);
        description = descText.getText().toString();

        resultIntent.putExtra("IMAGES_LIST",imgs);
        resultIntent.putExtra("DESC_TEXT",description);

        setResult(Activity.RESULT_OK,resultIntent);
        finish();
	}
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void cameraClick(View view) {

            dispatchTakePictureIntent();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           //Log.e("Debug",outputURI.toString());
//            Bundle extras = data.getExtras();
            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputURI);
            } catch (IOException e) {
                Log.e("Exception",e.getMessage());
            }

            if(imageBitmap != null) {
                ImageView imgview = (ImageView) findViewById(R.id.imgCameraImage);
                imgview.setImageBitmap(imageBitmap);
                images.add(outputURI.toString());

            } else {

                Toast.makeText(this.getApplicationContext(), "This hasn't worked", 50);
            }

            Toast.makeText(this.getApplicationContext(),"Image Successfull Captured",10);

            this.galleryAddPic();

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("Pathfinder", "IOException");
                Log.e("Pathfinder",ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {


                outputURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        outputURI);

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onClick(View view) {
        this.dispatchTakePictureIntent();
    }
}
