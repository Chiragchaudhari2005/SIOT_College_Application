package com.example.siot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.siot.R;

public class CustomAdapterInfo extends ArrayAdapter<String> {
    private final Context context;
    private String[] name;
    String documentId;
    LayoutInflater inflater;
    int avatarResourceId;

    public CustomAdapterInfo(@NonNull Context context, int resource, String[] name,  String documentId,int avatarResourceId) {
        super(context, resource,name);
        this.name = name;
        this.context = context;
        this.documentId = documentId;
        this.avatarResourceId = avatarResourceId;
        inflater = LayoutInflater.from(context);
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view

            //LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.customlayoutinfo, null);

        // Set the text of the TextView
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView nametv = convertView.findViewById(R.id.nametextView);
        String nameOG = name[0];
        nametv.setText(nameOG);
        if (avatarResourceId != 0) {
            // Check if the resource ID is valid
            // Set the profileImg ImageView with the specified drawable
           imageView.setImageResource(avatarResourceId);
        } else {
            // Handle the case where the resource ID is not found
            // You may set a default image or take other appropriate action
            imageView.setImageResource(R.drawable.default_avatar);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
