package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class student_info extends AppCompatActivity {
    private TextView nametv,enrollmenttv,branchtv,bloodgrouptv,dobtv,phonetv,semtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);

        nametv = findViewById(R.id.textView1);
        enrollmenttv = findViewById(R.id.textView2);
        branchtv = findViewById(R.id.textView3);
        bloodgrouptv = findViewById(R.id.textView4);
        dobtv = findViewById(R.id.textView5);
        phonetv = findViewById(R.id.textView6);
        semtv = findViewById(R.id.textView7);

        Intent intent = getIntent();

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
                    String name = documentSnapshot.getString("name");
                    nametv.setText(name);
                    Long enrollment = documentSnapshot.getLong("enrollment");
                    enrollmenttv.setText(String.valueOf(enrollment));
                    String branch = documentSnapshot.getString("branch");
                    branchtv.setText(branch);
                    String bloodgroup = documentSnapshot.getString("bloodgroup");
                    bloodgrouptv.setText(bloodgroup);
                    String dob = documentSnapshot.getString("dob");
                    dobtv.setText(dob);
                    Long phone = documentSnapshot.getLong("phone");
                    phonetv.setText(String.valueOf(phone));
                    Long sem = documentSnapshot.getLong("sem");
                    semtv.setText(String.valueOf(sem));
                }
            }
        });
    }
}
