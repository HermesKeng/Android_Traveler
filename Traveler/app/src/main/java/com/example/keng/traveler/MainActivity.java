package com.example.keng.traveler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.Preference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView locationListView;
    LocationArrayAdapter arrayAdapter;
    String[] locationStrList,arriveTimeStrList,leaveTimeStrList;
    ArrayList<Location> locationList = new ArrayList<Location>();
    public static final String PREFERENCE_PACKAGE = "com.example.keng.androidpolyuhk";
    public static final String Preference_NAME = "MyProfile";
    public static int MODE = Context.MODE_PRIVATE;

    final String LOC_TAG = "Location";
    final String LOC_LIST_TAG = "LocationList";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context c =null;

        try {
            c = this.createPackageContext(PREFERENCE_PACKAGE,CONTEXT_IGNORE_SECURITY);
            SharedPreferences sharedPreferences = c.getSharedPreferences(Preference_NAME,MODE);
            String name =sharedPreferences.getString("Name","Default Name");
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

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
            detailIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(detailIntent);
        }
    };
}
