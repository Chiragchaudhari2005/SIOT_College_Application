package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    private Long enrollment,phone,parent,sem,avatarResourceIdLong;
    private int avatarResourceId;
    Boolean found = false;
    String collectionName;

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


        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String uid = currentUser.getUid();



        getData(uid);


    }


    public void getData(String uid){

        if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("TYCO").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName = "TYCO";
                        setupClickListeners();
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("SYCO").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="SYCO";
                        setupClickListeners();
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("FYCO").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="FYCO";
                        setupClickListeners();
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("TYIF").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="TYIF";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("SYIF").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="SYIF";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("FYIF").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="FYIF";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("TYME").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="TYME";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("SYME").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="SYME";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("FYME").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="FYME";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("TYCE").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="TYCE";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("SYCE").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="SYCE";
                    }
                }
            });
        }
        else if(found == false){
            //Code to retrieve data from firebase for particular user
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("FYCE").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        name = documentSnapshot.getString("name");
                        found = true;
                        collectionName="FYCE";
                    }
                }
            });
        }
    }

    private void setupClickListeners() {
        studInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), student_info.class);
                intent.putExtra("collectionName", collectionName);
                startActivity(intent);
            }
        });

        // Similarly, setup other click listeners here
        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), library_selectInfo.class);
                startActivity(intent);
            }
        });

        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), stuatt_sub_select.class);
                intent.putExtra("name", name);
                intent.putExtra("collectionName", collectionName);
                startActivity(intent);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FetchNotes.class);
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
