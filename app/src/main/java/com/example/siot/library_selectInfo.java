package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class library_selectInfo extends AppCompatActivity {
    private Button nextBtn;
    private EditText branchEdt,semEdt;
    private String selectedBranch,selectedSem;
    final int[] checkedItem = {-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_selectinfo);

        branchEdt = findViewById(R.id.branchEdtTxt);
        semEdt = findViewById(R.id.semEdtTxt);
        nextBtn = findViewById(R.id.nextBtn);

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
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((selectedBranch == null || selectedBranch.isEmpty()&&(selectedSem == null || selectedSem.isEmpty()))) {
                    Toast.makeText(library_selectInfo.this, "Select Branch & Semester", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedBranch == null || selectedBranch.isEmpty()) {
                    // Show toast message for not selecting a date
                    Toast.makeText(library_selectInfo.this, "Select Branch", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                if (selectedSem == null || selectedSem.isEmpty()) {
                    // Show toast message for not selecting a subject
                    Toast.makeText(library_selectInfo.this, "Select Semester", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further
                }

                Intent intent=new Intent(library_selectInfo.this, semesterList.class);
                intent.putExtra("branch",selectedBranch);
                intent.putExtra("sem",selectedSem);
                startActivity(intent);
            }
        });
    }

    void showBranchPicker(){
        branchEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(library_selectInfo.this);

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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(library_selectInfo.this);


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
