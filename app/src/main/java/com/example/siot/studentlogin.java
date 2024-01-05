package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class studentlogin extends AppCompatActivity {

    private Button studentloginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        Intent intent = getIntent();
        studentloginButton = findViewById(R.id.studentloginButton);

        studentloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), student_homescreen.class);
                intent.putExtra("Student login",1);
                startActivity(intent);
            }
        });
    }
}