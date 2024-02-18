package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class screen_one extends AppCompatActivity {

    private Button staff_login_button;
    private Button student_login_button;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_one);

        staff_login_button = findViewById(R.id.staff_login_button);
        student_login_button = findViewById(R.id.student_login_button);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(screen_one.this, staff_homescreen.class));
        }
        else {
          student_login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),studentlogin.class);
                    startActivity(intent);
                }
          });
            staff_login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),stafflogin.class);
                    startActivity(intent);
                }
            });
        }
    }
}