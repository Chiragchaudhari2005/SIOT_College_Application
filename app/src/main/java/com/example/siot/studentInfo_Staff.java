package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class studentInfo_Staff extends AppCompatActivity {

    //String arr[] = {};
    private ListView listView;
    private EditText enrollment_edtxt;
    private Button searchBtn;
    String[] name = new String[1];
    String documentId;
    String collectionName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentinfostaff);

        listView = findViewById(R.id.listView);
        enrollment_edtxt = findViewById(R.id.enrollment_edtxt);
        searchBtn = findViewById(R.id.searchBtn);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String uid = currentUser.getUid();
        Intent intent = getIntent();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });
    }

    void searchData(){
        // Code to retrieve data from Firebase for a particular user based on enrollment number
        CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("TYCO");

        // Get enrollment number from the intent
        String enrollString = enrollment_edtxt.getText().toString();
        long enrollmentNumber = 0;

        try {
             enrollmentNumber = Long.parseLong(enrollString);
            // Rest of the code
        } catch (NumberFormatException e) {
            // Handle the case where input is not a valid long
            Toast.makeText(studentInfo_Staff.this, "Invalid enrollment number", Toast.LENGTH_SHORT).show();
        }




        collectionRef.whereEqualTo("enrollment", enrollmentNumber)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nameStr = document.getString("name");
                                name[0] = nameStr;
                                long enrollment = document.getLong("enrollment");
                                String branch = document.getString("branch");
                                String bloodgroup = document.getString("bloodgroup");
                                String DOB = document.getString("DOB");
                                String phone = document.getString("Phone");
                                documentId = document.getId();

                                collectionName = document.getReference().getParent().getId();

                                CustomAdapterInfo ca = new CustomAdapterInfo(getApplicationContext(), R.layout.customlayoutinfo, name,documentId);
                                listView.setAdapter(ca);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(studentInfo_Staff.this, studentDetailsStaff.class);
                                        intent.putExtra("documentId", documentId);
                                        intent.putExtra("collectionName", collectionName);
                                        // Start the activity
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }
                });
    }
}
