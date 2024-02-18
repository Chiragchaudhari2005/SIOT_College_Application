package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class staff_homescreen extends AppCompatActivity {

    private ImageButton notesBtn,infoBtn,attendenceBtn,libraryBtn,settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_homescreen);

        notesBtn = findViewById(R.id.notesBtn);
        infoBtn = findViewById(R.id.infoBtn);
        attendenceBtn = findViewById(R.id.attendenceBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        settingBtn = findViewById(R.id.settingBtn);

        Intent intent = getIntent();

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), studentInfo_Staff.class);
                startActivity(intent);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NotesUpload.class);
                startActivity(intent);
            }
        });

        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), attendence_selectinfo.class);
                startActivity(intent);
            }
        });

        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), library_record_branches.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), settings.class);
                startActivity(intent);
            }
        });

    }
}
