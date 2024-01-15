package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class student_homescreen extends AppCompatActivity {

    ImageButton library_record;
    ImageButton student_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        Intent intent = getIntent();
        library_record=findViewById(R.id.imageButton3);

        library_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),library_record_branches.class);
                startActivity(intent);
            }
        });

        student_info=findViewById(R.id.imageButton1);

        student_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), com.example.siot.student_info.class);
                startActivity(intent);
            }
        });
    }
}