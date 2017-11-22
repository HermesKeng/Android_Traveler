package com.example.keng.traveler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView locationTxt,periodTxt;
    ImageView detailLocationImage;
    ListView itineraryListView;
    ArrayList<Schedule> schedules;
    ArrayAdapter<Schedule> scheduleArrayAdapter;
    ImageButton shareBtn;
    public int setUpImage(int locationIndex){
        switch (locationIndex){
            case 0:
                return R.drawable.bangkok_blur;
            case 1:
                return R.drawable.tokyo_blur;
            case 2:
                return R.drawable.newyork_blur;
            case 3:
                return R.drawable.londan_blur;
            case 4:
                return R.drawable.singapore_blur;
            default:
                return 0;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //get Extra Data
        Bundle extras = getIntent().getExtras();
        int locationIndex = extras.getInt("Location",0);
        // set up each component's content in the view
        detailLocationImage=(ImageView)findViewById(R.id.detailLocationImage);
        locationTxt =(TextView)findViewById(R.id.locationTxt);
        itineraryListView = (ListView)findViewById(R.id.itinearyListView);

        detailLocationImage.setImageResource(setUpImage(locationIndex));
        periodTxt = (TextView)findViewById(R.id.periodText);
        // get data from resource
        String[] locationStr=getResources().getStringArray(R.array.travelLocation);
        String[] travelStartStr = getResources().getStringArray(R.array.travelArriveDate);
        String[] travelLeaveStr = getResources().getStringArray(R.array.travelLeaveDate);
        String period = travelStartStr[locationIndex] +" - "+travelLeaveStr[locationIndex];
        locationTxt.setText(locationStr[locationIndex].toUpperCase());
        periodTxt.setText(period);

        //set up schedule list

        schedules = new ArrayList<Schedule>();
        schedules.add(new Schedule("Levain Bakery","Manhattan","American",0));
        schedules.add(new Schedule("Two Little Red Hens","Manhattan","American, Cafe",0));
        schedules.add(new Schedule("Brooklyn Botanic Garden","Brooklyn","Garden, Leisure",1));
        //setup arrayadapter for listView
        scheduleArrayAdapter=new ScheduleArrayAdapter(this,schedules);

        itineraryListView.setAdapter(scheduleArrayAdapter);


    }
}
