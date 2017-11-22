package com.example.keng.traveler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by keng on 18/11/2017.
 */

public class ShareArrayAdapter extends ArrayAdapter<String>{


    public ShareArrayAdapter(@NonNull Context context, @LayoutRes int resource,ArrayList<String> arrayList) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =super.getView(position, convertView, parent);
        TextView txtRow = convertView.findViewById(R.id.listRow);
        txtRow.setText(getItem(position));
        return view;
    }

}

