package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class civil_engg_semesters extends AppCompatActivity {

    Button sem1,sem2,sem3,sem4,sem5,sem6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_engg_semesters);

        sem1=findViewById(R.id.civilsem1);
        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem1.class);
                startActivity(intent);
            }
        });

        sem2=findViewById(R.id.civilsem2);
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem2.class);
                startActivity(intent);
            }
        });

        sem3=findViewById(R.id.civilsem3);
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem3.class);
                startActivity(intent);
            }
        });

        sem4=findViewById(R.id.civilsem4);
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem4.class);
                startActivity(intent);
            }
        });

        sem5=findViewById(R.id.civilsem5);
        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem5.class);
                startActivity(intent);
            }
        });

        sem6=findViewById(R.id.civilsem6);
        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_sem6.class);
                startActivity(intent);
            }
        });
    }
}