package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class library_Semselection extends AppCompatActivity {

    Button sem1,sem2,sem3,sem4,sem5,sem6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_semselection);

        Intent intent = getIntent();
        String branch = intent.getStringExtra("branch");

        sem1=findViewById(R.id.sem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem1");
                startActivity(intent);
            }
        });

        sem2=findViewById(R.id.sem2);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem2");
                startActivity(intent);
            }
        });

        sem3=findViewById(R.id.sem3);
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem3");
                startActivity(intent);
            }
        });

        sem4=findViewById(R.id.sem4);
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem4");
                startActivity(intent);
            }
        });

        sem5=findViewById(R.id.sem5);
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem5");
                startActivity(intent);
            }
        });

        sem6=findViewById(R.id.sem6);
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), semesterList.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sem","sem6");
                startActivity(intent);
            }
        });
    }
}