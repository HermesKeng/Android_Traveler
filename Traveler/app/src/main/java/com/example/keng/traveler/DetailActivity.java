package com.example.keng.traveler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView locationTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        String Location = extras.getString("Location","Error : No Content Pass");
        locationTxt = (TextView)findViewById(R.id.locationTxt);
        locationTxt.setText(Location);
    }
}
