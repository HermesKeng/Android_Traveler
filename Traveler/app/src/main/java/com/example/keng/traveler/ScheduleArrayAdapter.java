package com.example.keng.traveler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by keng on 18/11/2017.
 */

public class ScheduleArrayAdapter extends ArrayAdapter<Schedule>{

    public ScheduleArrayAdapter(@NonNull Context context, ArrayList<Schedule> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule_layout,parent,false);
            //inflate the layout
        }
        ImageView imageView =convertView.findViewById(R.id.iconImg);
        TextView nameTxt = convertView.findViewById(R.id.nameTxt);
        TextView districtTxt = convertView.findViewById(R.id.districtTxt);
        TextView descriptionTxt = convertView.findViewById(R.id.descriptionTxt);

        Schedule schedule = getItem(position);

        nameTxt.setText(schedule.getName());
        districtTxt.setText(schedule.getDistrict());
        descriptionTxt.setText(schedule.getDescription());
        imageView.setImageResource(schedule.getImageID());

        return convertView;
    }

}
