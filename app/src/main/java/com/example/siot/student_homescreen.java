package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class student_homescreen extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ImageButton studInfoBtn,settingBtn;
    private ImageButton libraryBtn;
    private ImageButton attendenceBtn;
    private ImageButton notesBtn;
    private GestureDetector gestureDetector;
    private String name,branch,bloodgroup,dob;
    private Long enrollment,phone;

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
        settingBtn = findViewById(R.id.settingBtn);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String uid = currentUser.getUid();

        //Code to retrieve data from firebase for particular user
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("TYCO").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    name = documentSnapshot.getString("name");
                    enrollment = documentSnapshot.getLong("enrollment");
                    branch = documentSnapshot.getString("branch");
                    bloodgroup = documentSnapshot.getString("bloodgroup");
                    dob = documentSnapshot.getString("DOB");
                    phone = documentSnapshot.getLong("phone");
                }
            }
        });


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
                Toast.makeText(student_homescreen.this, "name="+name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), stuatt_sub_select.class);
                intent.putExtra("name",name);
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

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), settings.class);
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
            // Left swipe detected, open student_achievements.xml.class
            //Intent intent = new Intent(this, student_achievements.class);
            //startActivity(intent);
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
