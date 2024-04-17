package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    private EditText branchEdt,semEdt;
    private Button searchBtn,branchBtn,semBtn;
    String[] name = new String[1];
    String documentId;
    String collectionName,selectedSem,selectedBranch;
    final int[] checkedItem = {-1};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentinfostaff);

        listView = findViewById(R.id.listView);
        enrollment_edtxt = findViewById(R.id.enrollment_edtxt);
       // branchBtn = findViewById(R.id.branchBtn);
       // semBtn = findViewById(R.id.semBtn);
        searchBtn = findViewById(R.id.searchBtn);
        branchEdt = findViewById(R.id.branchEdtTxt);
        semEdt = findViewById(R.id.semEdtTxt);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String uid = currentUser.getUid();
        Intent intent = getIntent();


        branchEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Call the method to show the DatePickerDialog
                    showBranchPicker();
                }
            }
        });

        branchEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to show the DatePickerDialog
                showBranchPicker();
            }
        });

        semEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to show the DatePickerDialog
                showSemPicker();
            }
        });

        semEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Call the method to show the DatePickerDialog
                    showSemPicker();
                }
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedBranch!=null && selectedSem!=null){
                    searchData();
                }
                else {
                    Toast.makeText(studentInfo_Staff.this, "branch or sem not selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void searchData(){


            collectionName = getCollection();
            // Code to retrieve data from Firebase for a particular user based on enrollment number
            CollectionReference collectionRef = FirebaseFirestore.getInstance().collection(collectionName);

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
                                    Long avatarResourceIdLong = document.getLong("avatar");
                                    int avatarResourceId = avatarResourceIdLong != null ? avatarResourceIdLong.intValue() : 0;
                                    documentId = document.getId();

                                    collectionName = document.getReference().getParent().getId();

                                    CustomAdapterInfo ca = new CustomAdapterInfo(getApplicationContext(), R.layout.customlayoutinfo, name, documentId, avatarResourceId);
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

    private String getCollection(){
        String collection = new String();
        if (selectedBranch == "CO" && selectedSem == "SEM 6") {
            collection = "TYCO";
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 5") {
            collection = "TYCO";
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 4") {
            collection = "SYCO";
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 3") {
            collection = "SYCO";
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 2") {
            collection = "FYCO";
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 1") {
            collection = "FYCO";
        }
        return collection;
    }

    void showBranchPicker(){
        branchEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(studentInfo_Staff.this);

        alertDialog.setTitle("Choose branch");  // title of the alert dialog

        final String[] listItems = new String[]{"CO", "IF", "ME","AT"};

        alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
            checkedItem[0] = which;

            branchEdt.setText(listItems[which]);
            selectedBranch = listItems[which];

            dialog.dismiss();
        });

        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

        });

        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }

    void showSemPicker() {
        semEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(studentInfo_Staff.this);


        alertDialog.setTitle("Choose Semester");

        final String[] listItems = new String[]{"SEM 1", "SEM 2", "SEM 3", "SEM 4", "SEM 5", "SEM 6"};

        alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {

            checkedItem[0] = which;

            semEdt.setText(listItems[which]);
            selectedSem = listItems[which];

            dialog.dismiss();
        });

        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

        });

        AlertDialog customAlertDialog = alertDialog.create();

        customAlertDialog.show();
    }
}
