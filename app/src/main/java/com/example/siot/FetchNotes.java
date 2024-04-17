package com.example.siot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FetchNotes extends AppCompatActivity {

    Button getbranch, getsem, fetchbtn;
    String selectedBranch, selectedSem;
    private EditText branchEdt,semEdt;
    final int[] checkedItem = {-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_notes);
        fetchbtn=findViewById(R.id.fetchfilebtn);
        branchEdt = findViewById(R.id.branchEdtTxt);
        semEdt = findViewById(R.id.semEdtTxt);

/*        getbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getbranch.clearFocus();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FetchNotes.this);

                alertDialog.setTitle("Choose branch");  // title of the alert dialog

                final String[] listItems = new String[]{"CO", "IF", "ME", "AE", "CE"};

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;

                        getbranch.setText(listItems[which]);
                        selectedBranch = listItems[which];

                        dialog.dismiss();
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle Cancel button click
                    }
                });

                AlertDialog customAlertDialog = alertDialog.create();
                customAlertDialog.show();
            }
        });

        getsem=findViewById(R.id.semBtnnotesget);

        getsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getsem.clearFocus();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FetchNotes.this);


                alertDialog.setTitle("Choose branch");

                final String[] listItems = new String[]{"SEM1", "SEM2", "SEM3", "SEM4", "SEM5", "SEM6"};

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {

                    checkedItem[0] = which;

                    getsem.setText(listItems[which]);
                    selectedSem = listItems[which];

                    dialog.dismiss();
                });

                alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

                });

                AlertDialog customAlertDialog = alertDialog.create();

                customAlertDialog.show();
            }
        });*/

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

        fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(FetchNotes.this,NotesRecyclerActivity.class));
                if ((selectedBranch == null || selectedBranch.isEmpty()&&(selectedSem == null || selectedSem.isEmpty()))) {
                    Toast.makeText(FetchNotes.this, "Select Branch & Semester", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedBranch == null || selectedBranch.isEmpty()) {
                    // Show toast message for not selecting a date
                    Toast.makeText(FetchNotes.this, "Select Branch", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                if (selectedSem == null || selectedSem.isEmpty()) {
                    // Show toast message for not selecting a subject
                    Toast.makeText(FetchNotes.this, "Select Semester", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                Intent intent = new Intent(FetchNotes.this,NotesRecyclerActivity.class);
                intent.putExtra("branch",selectedBranch);
                intent.putExtra("sem",selectedSem);
                startActivity(intent);
            }
        });
    }

    void showBranchPicker(){
        branchEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FetchNotes.this);

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

    void showSemPicker(){
        semEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FetchNotes.this);


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