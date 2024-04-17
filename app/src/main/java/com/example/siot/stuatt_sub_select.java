package com.example.siot;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class stuatt_sub_select extends AppCompatActivity {
    private Button subjectBtn,dateBtn,nextBtn;
    private String branch,selectedSubject,collectionName;
    private String[] subjectList;
    private long sem=0;
    private TextView semTv;
    final int[] checkedItem = {-1};
    public String selectedDate,name;
    private EditText dateEdtTxt,subEdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stuatt_subselect);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        collectionName = intent.getStringExtra("collectionName");

        subEdtTxt = findViewById(R.id.subEdtTxt);
        nextBtn = findViewById(R.id.nextBtn);
        semTv = findViewById(R.id.semTv);
        dateEdtTxt = findViewById(R.id.dateEdtTxt);

        getBranch();

        subjectList = setSubjectList();

        dateEdtTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Call the method to show the DatePickerDialog
                    showDatePickerDialog();
                }
            }
        });

        dateEdtTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to show the DatePickerDialog
                showDatePickerDialog();
            }
        });
        subEdtTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Call the method to show the DatePickerDialog
                    selectSubject();
                }
            }
        });

        subEdtTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to show the DatePickerDialog
                selectSubject();
            }
        });
/*        subjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSubject();
            }
        });*/

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((selectedDate == null || selectedDate.isEmpty()&&(selectedSubject == null || selectedSubject.isEmpty()))) {
                    Toast.makeText(stuatt_sub_select.this, "Select Date & Subject", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedDate == null || selectedDate.isEmpty()) {
                    // Show toast message for not selecting a date
                    Toast.makeText(stuatt_sub_select.this, "Select Date", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                if (selectedSubject == null || selectedSubject.isEmpty()) {
                    // Show toast message for not selecting a subject
                    Toast.makeText(stuatt_sub_select.this, "Select Subject", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                // Proceed to the next activity
                Intent intent = new Intent(stuatt_sub_select.this, stuattmain.class);
                intent.putExtra("name", name);
                intent.putExtra("branch", branch);
                intent.putExtra("sem", sem);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedSubject", selectedSubject);
                startActivity(intent);
            }
        });

    }


    void setDate(int year, int month, int dayOfMonth) {
        // Format the selected date as dd-mm-yyyy
        selectedDate = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year);

        // Set the formatted date to the EditText
        dateEdtTxt.setText(selectedDate);
    }


    void showDatePickerDialog() {
        // Get the current year, month, and day
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Update the selectedDate and set it to the EditText
                setDate(year, monthOfYear, dayOfMonth);
            }
        }, currentYear, currentMonth, currentDay);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }


    String[] setSubjectList(){
        String[] listItems = new String[]{"Default Subject 1", "Default Subject 2"};
        if ("CO".equals(branch) && 6 == sem){
            listItems = new String[]{"PWP","MAD","NIS","ETI","EDP","MGT","All Subjects"};
        }
        if ("CO".equals(branch) && 5 == sem){
            listItems = new String[]{"AJP","CSS","EST","STE","OSY"};
        }
        return listItems;
    }
    void getBranch(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String uid = currentUser.getUid();

        // Code to retrieve data from the specified collection using the document ID (uid)
        DocumentReference docRef = FirebaseFirestore.getInstance().collection(collectionName).document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null) {
                        branch = documentSnapshot.getString("branch");
                        // Check if "sem" field is present and not null
                        if (documentSnapshot.contains("sem") && documentSnapshot.get("sem") != null) {
                            sem = documentSnapshot.getLong("sem");
                            semTv.setText(String.valueOf(sem));
                        } else {
                            // Handle the case where "sem" is not present or null
                            // You might set a default value or show an error message
                        }
                    }
                }
            }
        });
    }

    void selectSubject(){

        subjectList = setSubjectList();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(stuatt_sub_select.this);

        alertDialog.setTitle("Select subject");

        Log.e(TAG, "selectSubject: "+subjectList);
        alertDialog.setSingleChoiceItems(subjectList, checkedItem[0], (dialog, which) -> {
            checkedItem[0] = which;

            subEdtTxt.setText(subjectList[which]);
            selectedSubject = subjectList[which];

            dialog.dismiss();
        });

        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

        });

        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
}
