package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class StudentHomeFragment extends Fragment {

    public StudentHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.student_homescreen, container, false);

        // Retrieve buttons from the layout
        ImageButton infoBtn = view.findViewById(R.id.infoBtn);
        ImageButton button2 = view.findViewById(R.id.imageButton2);

        // Set click listeners for the buttons
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button1 click
                if (isAdded()) {
                    // Handle button1 click
                    Intent intent = new Intent(getActivity(), student_info.class);
                    startActivity(intent);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button2 click
                if (isAdded()) {
                    // Handle button1 click
                    Intent intent = new Intent(getActivity(), student_info.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
