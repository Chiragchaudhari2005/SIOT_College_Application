package com.example.siot;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;  // Import the TextView class

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class studentDetailsStaff extends AppCompatActivity {

    private TextView nametv, enrollmenttv, branchtv, bloodgrouptv, dobtv, phonetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentdetailsstaff);

        // Initialize TextViews
        nametv = findViewById(R.id.textView1);
        enrollmenttv = findViewById(R.id.textView2);
        branchtv = findViewById(R.id.textView3);
        bloodgrouptv = findViewById(R.id.textView4);
        dobtv = findViewById(R.id.textView5);
        phonetv = findViewById(R.id.textView6);

        Intent intent = getIntent();
        String documentId = intent.getStringExtra("documentId");
        String collectionName = intent.getStringExtra("collectionName");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        // Code to retrieve data from Firebase for a particular documentId
        DocumentReference docRef = FirebaseFirestore.getInstance().collection(collectionName).document(documentId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {  // Check if the document exists
                        String name = documentSnapshot.getString("name");
                        nametv.setText(name);
                        Long enrollment = documentSnapshot.getLong("enrollment");
                        enrollmenttv.setText(String.valueOf(enrollment));
                        String branch = documentSnapshot.getString("branch");
                        branchtv.setText(branch);
                        String bloodgroup = documentSnapshot.getString("bloodgroup");
                        bloodgrouptv.setText(bloodgroup);
                        String dob = documentSnapshot.getString("DOB");
                        dobtv.setText(dob);
                        Long phone = documentSnapshot.getLong("phone");
                        phonetv.setText(String.valueOf(phone));
                    } else {
                        // Handle the case where the document does not exist
                    }
                } else {
                    // Handle errors in retrieving the document
                }
            }
        });
    }
}
