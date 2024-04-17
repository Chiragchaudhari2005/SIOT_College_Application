package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class staff_homescreen extends AppCompatActivity {

    private ImageButton notesBtn;
    private ImageButton infoBtn;
    private ImageButton attendenceBtn;
    private ImageButton libraryBtn,settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_homescreen);

        notesBtn = findViewById(R.id.notesBtn);
        infoBtn = findViewById(R.id.infoBtn);
        attendenceBtn = findViewById(R.id.attendenceBtn);
        libraryBtn = findViewById(R.id.libraryBtn);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

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
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), library_selectInfo.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.settings){
            Intent intent = new Intent(this,settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
