package com.example.keng.traveler;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
// reference set custom dialog button : https://stackoverflow.com/questions/18352324/how-can-can-i-add-custom-buttons-into-an-alertdialogs-layout
public class DetailActivity extends AppCompatActivity {

    TextView locationTxt,periodTxt;
    ImageView detailLocationImage;
    ListView itineraryListView;
    ArrayList<Schedule> schedules;
    ArrayAdapter<Schedule> scheduleArrayAdapter;
    TextView toolbarTitle;
    ImageButton addBtn;
    final public int GET_EMAIL= 1;
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
        toolbarTitle=(TextView)findViewById(R.id.toolBarTitle);
        detailLocationImage.setImageResource(setUpImage(locationIndex));
        periodTxt = (TextView)findViewById(R.id.periodText);
        addBtn = (ImageButton)findViewById(R.id.addBtn);
        // get data from resource
        String[] locationStr=getResources().getStringArray(R.array.travelLocation);
        String[] travelStartStr = getResources().getStringArray(R.array.travelArriveDate);
        String[] travelLeaveStr = getResources().getStringArray(R.array.travelLeaveDate);
        String period = travelStartStr[locationIndex] +" - "+travelLeaveStr[locationIndex];
        locationTxt.setText(locationStr[locationIndex].toUpperCase());
        periodTxt.setText(period);
        toolbarTitle.setText("Your Journey");
        //set up schedule list

        schedules = new ArrayList<Schedule>();
        schedules.add(new Schedule("Levain Bakery","Manhattan","American",0));
        schedules.add(new Schedule("Two Little Red Hens","Manhattan","American, Cafe",0));
        schedules.add(new Schedule("Brooklyn Botanic Garden","Brooklyn","Garden, Leisure",1));
        //setup arrayadapter for listView
        scheduleArrayAdapter=new ScheduleArrayAdapter(this,schedules);

        itineraryListView.setAdapter(scheduleArrayAdapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.ShareDiologStyle);
                LayoutInflater layoutInflater = LayoutInflater.from(DetailActivity.this);
                final View dialogView = layoutInflater.inflate(R.layout.add_new_dialog,null);
                builder.setView(dialogView);
                final AlertDialog dialog =builder.create();
                Button addDialogBtn =dialogView.findViewById(R.id.addDialogBtn);
                Button cancelDialogBtn = dialogView.findViewById(R.id.cancelDialogBtn);
                final EditText location = (EditText)dialogView.findViewById(R.id.locationInput);
                final EditText district = (EditText)dialogView.findViewById(R.id.districtInput);
                final EditText description = (EditText)dialogView.findViewById(R.id.descriptionInput);
                final Spinner spinner =(Spinner)dialogView.findViewById(R.id.spinner);
                addDialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String locationStr = location.getText().toString();
                        String districtStr = district.getText().toString();
                        String descriptionStr = description.getText().toString();
                        int category = spinner.getSelectedItemPosition();
                        schedules.add(new Schedule(locationStr,districtStr,descriptionStr,category));
                        scheduleArrayAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                cancelDialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });


                dialog.show();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case GET_EMAIL:
                    String emailStr = data.getStringExtra("emailAddress");
                    Toast.makeText(this, emailStr, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        // add here
    }
}
