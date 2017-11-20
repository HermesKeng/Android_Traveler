package com.example.keng.traveler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView locationListView;
    LocationArrayAdapter arrayAdapter;
    String[] locationStrList,arriveTimeStrList,leaveTimeStrList;
    ArrayList<Location> locationList = new ArrayList<Location>();
    final String LOC_TAG = "Location";
    final String LOC_LIST_TAG = "LocationList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationStrList = getResources().getStringArray(R.array.travelLocation);
        arriveTimeStrList = getResources().getStringArray(R.array.travelArriveDate);
        leaveTimeStrList = getResources().getStringArray(R.array.travelLeaveDate);
        locationListView = (ListView)findViewById(R.id.locationListView);

        Location tempLocation;
        for(int i=0;i<locationStrList.length;i++){
            int resID =getResources().getIdentifier(locationStrList[i],"drawable",this.getPackageName());
            tempLocation = new Location(locationStrList[i],resID,arriveTimeStrList[i],leaveTimeStrList[i]);
            locationList.add(tempLocation);
        }
        arrayAdapter =new LocationArrayAdapter(this,locationList);


        locationListView.setAdapter(arrayAdapter);
        locationListView.setOnItemClickListener(itemClickListener);

    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent detailIntent = new Intent(MainActivity.this,DetailActivity.class);
            detailIntent.putExtra(LOC_TAG,i);
            startActivity(detailIntent);
        }
    };

}
