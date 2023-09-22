package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class screen_one extends AppCompatActivity {

    private Button staff_login_button;
    private Button student_login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_one);

        staff_login_button = findViewById(R.id.staff_login_button);
        student_login_button = findViewById(R.id.student_login_button);

        staff_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),stafflogin.class);
                startActivity(intent);
            }
        });

        student_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),studentlogin.class);
                startActivity(intent);
            }
        });
    }
}