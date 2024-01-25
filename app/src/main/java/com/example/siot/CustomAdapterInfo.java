package com.example.siot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.siot.R;

public class CustomAdapterInfo extends ArrayAdapter<String> {
    private final Context context;
    private String[] name;
    String documentId;
    LayoutInflater inflater;

    public CustomAdapterInfo(@NonNull Context context, int resource, String[] name,  String documentId) {
        super(context, resource,name);
        this.name = name;
        this.context = context;
        this.documentId = documentId;
        inflater = LayoutInflater.from(context);
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view

            //LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.customlayoutinfo, null);

        // Set the text of the TextView
        TextView nametv = convertView.findViewById(R.id.nametextView);
        String nameOG = name[0];
        nametv.setText(nameOG);
        // Return the completed view to render on screen
        return convertView;
    }
}
