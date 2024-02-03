package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class student_homescreen extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ImageButton studInfoBtn;
    private ImageButton libraryBtn;
    private ImageButton attendenceBtn;
    private ImageButton notesBtn;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homescreen);

        gestureDetector = new GestureDetector(this, this);

        Intent intent = getIntent();
        studInfoBtn = findViewById(R.id.infoBtn);
        libraryBtn = findViewById(R.id.libraryBtn);
        attendenceBtn = findViewById(R.id.attendenceBtn);
        notesBtn = findViewById(R.id.notesBtn);


        studInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), student_info.class);
                startActivity(intent);
            }
        });

        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), library_record_branches.class);
                startActivity(intent);
            }
        });

        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), stuattmain.class);
                startActivity(intent);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), stunotesmain.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaX = e2.getX() - e1.getX();

        if (Math.abs(deltaX) > 200 && Math.abs(velocityX) > 200) {
            // Left swipe detected, open achievements.class
            Intent intent = new Intent(this, achievements.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    // Implement other methods of OnGestureListener interface if needed
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }
}
