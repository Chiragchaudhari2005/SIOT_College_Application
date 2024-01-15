package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class library_record extends AppCompatActivity {

    Button sem1,sem2,sem3,sem4,sem5,sem6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_record);

        sem1=findViewById(R.id.sem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semester1.class);
                startActivity(intent);
            }
        });

        sem2=findViewById(R.id.sem2);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), info_tech_sem2.class);
                startActivity(intent);
            }
        });

        sem3=findViewById(R.id.sem3);
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), info_tech_sem3.class);
                startActivity(intent);
            }
        });

        sem4=findViewById(R.id.sem4);
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), info_tech_sem4.class);
                startActivity(intent);
            }
        });

        sem5=findViewById(R.id.sem5);
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), info_tech_sem5.class);
                startActivity(intent);
            }
        });

        sem6=findViewById(R.id.sem6);
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), info_tech_sem6.class);
                startActivity(intent);
            }
        });
    }
}