package com.example.keng.traveler;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by keng on 18/11/2017.
 * reference : http://dharmikpatel.online/android-tutorials/alert-dialog/
 * reference (return result from other Activity): https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
 * reference (prevent double click action starting two times activity): https://stackoverflow.com/questions/8906456/prevent-opening-activity-for-multiple-times
 */

public class ScheduleArrayAdapter extends ArrayAdapter<Schedule>{

    Context activityContext;
    final public int GET_EMAIL= 1;
    public ScheduleArrayAdapter(@NonNull Context context, ArrayList<Schedule> objects) {
        super(context,0,objects);
        activityContext = context;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule_layout,parent,false);
            //inflate the layout
        }
        ImageView imageView =convertView.findViewById(R.id.iconImg);
        TextView nameTxt = convertView.findViewById(R.id.nameTxt);
        TextView districtTxt = convertView.findViewById(R.id.districtTxt);
        TextView descriptionTxt = convertView.findViewById(R.id.descriptionTxt);
        ImageButton shareBtn = convertView.findViewById(R.id.shareBtn);
        final Schedule schedule = getItem(position);

        nameTxt.setText(schedule.getName());
        districtTxt.setText(schedule.getDistrict());
        descriptionTxt.setText(schedule.getDescription());
        imageView.setImageResource(schedule.getImageID());
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactIntent = new Intent(getContext(),ContactInfo.class);
                contactIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Activity detailActivity = (Activity)activityContext;
                detailActivity.startActivityForResult(contactIntent,GET_EMAIL);

            }
        });

        return convertView;
    }


}

