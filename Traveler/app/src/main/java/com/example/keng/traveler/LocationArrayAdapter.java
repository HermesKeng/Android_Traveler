package com.example.keng.traveler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keng on 18/11/2017.
 */

public class LocationArrayAdapter extends ArrayAdapter<Location>{

    Integer[] imageId = {R.drawable.bangkok,R.drawable.tokyo,R.drawable.newyork,R.drawable.londan,R.drawable.singapore};
    public LocationArrayAdapter(@NonNull Context context, ArrayList<Location> objects) {
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_layout,parent,false);
            //inflate the layout
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.locationImage);
        TextView textView = (TextView)convertView.findViewById(R.id.location);
        Location location = getItem(position);
        textView.setText(location.getLocation());
        imageView.setImageDrawable(getContext().getResources().getDrawable(imageId[position]));
        return convertView;
    }
}
