package cs221.group15.pathfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jap38 on 29/01/2014.
 */
public class ActivityViewTours extends Activity {

    //Change to Tour array from database
    String[] WalkArray;
    private ListView tourList;
    private ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tours);
        tourList = (ListView) findViewById(R.id.tourList);
        //Make Sure to change WalkArray to the proper name of the Array
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, WalkArray);
        tourList.setAdapter(arrayAdapter);

        tourList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //After walks completed this will grab the position (-1) of the array based upon the number of saved tours
                //Toast.makeText(getApplicationContext(), "Int i is " + i, Toast.LENGTH_LONG).show();
                showEditToursActivity(i);
            }
        });
    }

    private void showEditToursActivity(int i) {
        Intent intent = new Intent(this, ActivityEditTours.class);
        intent.putExtra("TOUR_CLICK_ID",i);
        this.startActivity(intent);
    }
}
