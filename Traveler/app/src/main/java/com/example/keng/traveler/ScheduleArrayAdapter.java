package com.example.keng.traveler;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
 */

public class ScheduleArrayAdapter extends ArrayAdapter<Schedule>{

    public ScheduleArrayAdapter(@NonNull Context context, ArrayList<Schedule> objects) {
        super(context,0,objects);
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
                AlertDialog.Builder builder =new AlertDialog.Builder(view.getContext());
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View dialogView =layoutInflater.inflate(R.layout.share_dialog,null);
                //builder.setView(dialogView);
                ListView listView= dialogView.findViewById(R.id.friendList);
                Button cancelBtn = dialogView.findViewById(R.id.cancelBtn);

                // Put Content Provider Data inside
                String[] values = new String[]{
                        "Kevin","Hermes","Logan","Horse"
                };
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.list_item,R.id.listRow,values);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(),arrayAdapter.getItem(i),Toast.LENGTH_LONG).show();
                    }
                });
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return convertView;
    }


}

