package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class staff_homescreen extends AppCompatActivity {

    private ImageButton notes_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_homescreen);

        notes_btn = findViewById(R.id.notes_btn);
        Intent intent = getIntent();



        notes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), notes_staff1.class);
                startActivity(intent);
            }
        });
    }
}
