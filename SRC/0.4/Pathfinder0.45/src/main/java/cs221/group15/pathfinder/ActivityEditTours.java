package cs221.group15.pathfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jap38 on 29/01/2014.
 */
public class ActivityEditTours extends Activity {

    private TextView Title, SrtDesc, LngDesc;
    //Array Position Needed
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tours);
        Bundle extras = getIntent().getExtras();
        this.i = (Integer) extras.get("TOUR_CLICK_ID");
        Title = (TextView) findViewById(R.id.textTitle);
        SrtDesc = (TextView) findViewById(R.id.textShtDesc);
        LngDesc = (TextView) findViewById(R.id.textLngDesc);

        //Will set the TextViews to the contents of the selected object.
        //e.g. Title.setText(WalkArray[i].Title);
        Title.setText("");
        SrtDesc.setText("");
        LngDesc.setText("");
    }
}
