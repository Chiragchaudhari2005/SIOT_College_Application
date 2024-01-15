package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class library_record_branches extends AppCompatActivity {

    Button info_tech,comp_engg,mech_engg,civil_engg,auto_engg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_record_branches);
        /* creating multiple intent to load same class of library record containing semesters */
        info_tech=findViewById(R.id.info);
        info_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), library_record.class);
                startActivity(intent);
            }
        });

        comp_engg=findViewById(R.id.comp);
        comp_engg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), comp_engg_semesters.class);
                startActivity(intent);
            }
        });

        mech_engg=findViewById(R.id.mech);
        mech_engg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), mech_engg_semesters.class);
                startActivity(intent);
            }
        });

        civil_engg=findViewById(R.id.civil);
        civil_engg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), civil_engg_semesters.class);
                startActivity(intent);
            }
        });

        auto_engg=findViewById(R.id.auto);
        auto_engg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), auto_engg_semesters.class);
                startActivity(intent);
            }
        });
    }
}