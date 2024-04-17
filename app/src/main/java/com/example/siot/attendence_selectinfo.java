package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class attendence_selectinfo extends AppCompatActivity {

    private Button branchBtn,semBtn,subjectBtn,nextBtn;
    private String selectedBranch,selectedSem,selectedSubject,today;
    private Boolean Boolbranch = false;
    private Boolean Boolsem = false;
    private Boolean Boolsubject = false;
    private TextView dateTv;
    private EditText subEdtTxt,branchEdt,semEdt;
    final int[] checkedItem = {-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_selectinfo);

       // branchBtn = findViewById(R.id.branchBtn);
        //semBtn = findViewById(R.id.semBtn);
        nextBtn = findViewById(R.id.nextBtn);
        //subjectBtn = findViewById(R.id.subjectBtn);
        dateTv = findViewById(R.id.textView5);
        subEdtTxt = findViewById(R.id.subEdtTxt);
        branchEdt = findViewById(R.id.branchEdtTxt);
        semEdt = findViewById(R.id.semEdtTxt);

        //subjectBtn.setEnabled(false);

        today = getTodaysDate();
        dateTv.setText(today);

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
 /*       branchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                branchBtn.clearFocus();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);

                alertDialog.setTitle("Choose branch");  // title of the alert dialog

                final String[] listItems = new String[]{"CO", "IF", "ME","AT"};

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
                    checkedItem[0] = which;

                    branchBtn.setText(listItems[which]);
                    selectedBranch = listItems[which];

                    dialog.dismiss();
                });

                alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

                });

                AlertDialog customAlertDialog = alertDialog.create();
                customAlertDialog.show();

                Boolbranch = true;
            }
        });

      semBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(Boolbranch) {

                  semBtn.clearFocus();
                  AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);


                  alertDialog.setTitle("Choose branch");

                  final String[] listItems = new String[]{"SEMESTER1", "SEMESTER2", "SEMESTER3", "SEMESTER4", "SEMESTER5", "SEMESTER6"};

                  alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {

                      checkedItem[0] = which;

                      semBtn.setText(listItems[which]);
                      selectedSem = listItems[which];
                      subjectBtn.setEnabled(true);

                      dialog.dismiss();
                  });

                  alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

                  });

                  AlertDialog customAlertDialog = alertDialog.create();

                  customAlertDialog.show();
                  Boolsem = true;
              }
              else {
                  Toast.makeText(attendence_selectinfo.this, "Select branch first!", Toast.LENGTH_SHORT).show();
              }
          }
      });

      subjectBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if ((Boolbranch == true) && (Boolsem == true)) {
                  subjectBtn.clearFocus();
                  AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);


                  alertDialog.setTitle("Choose subject");

                  String[] selectedList = selectList();

                  alertDialog.setSingleChoiceItems(selectedList, checkedItem[0], (dialog, which) -> {

                      checkedItem[0] = which;

                      subjectBtn.setText(selectedList[which]);
                      selectedSubject = selectedList[which];


                      dialog.dismiss();
                  });

                  alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

                  });

                  AlertDialog customAlertDialog = alertDialog.create();

                  customAlertDialog.show();
                  Boolsubject = true;
              }
              else {
                  Toast.makeText(attendence_selectinfo.this, "Select branch & sem!", Toast.LENGTH_SHORT).show();
              }
          }

      });*/

      nextBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if ((selectedBranch == null || selectedBranch.isEmpty()&&(selectedSem == null || selectedSem.isEmpty()) && (selectedSubject == null || selectedSubject.isEmpty()))) {
                  Toast.makeText(attendence_selectinfo.this, "Select Branch,Semester,Subject", Toast.LENGTH_SHORT).show();
                  return;
              }
              if (selectedBranch == null || selectedBranch.isEmpty()) {
                  // Show toast message for not selecting a date
                  Toast.makeText(attendence_selectinfo.this, "Select Branch", Toast.LENGTH_SHORT).show();
                  return; // Don't proceed further
              }

              if (selectedSem == null || selectedSem.isEmpty()) {
                  // Show toast message for not selecting a subject
                  Toast.makeText(attendence_selectinfo.this, "Select Semester", Toast.LENGTH_SHORT).show();
                  return; // Don't proceed further
              }

              if (selectedSubject == null || selectedSubject.isEmpty()) {
                  // Show toast message for not selecting a subject
                  Toast.makeText(attendence_selectinfo.this, "Select Subject", Toast.LENGTH_SHORT).show();
                  return; // Don't proceed further
              }

                  Intent intent = new Intent(v.getContext(), attendence_list.class);
                  intent.putExtra("branch", selectedBranch);
                  intent.putExtra("sem", selectedSem);
                  intent.putExtra("subject", selectedSubject);
                  intent.putExtra("date", today);
                  startActivity(intent);
          }
      });

    }

    void selectSubject(){

            subEdtTxt.clearFocus();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);


            alertDialog.setTitle("Choose subject");

            String[] selectedList = selectList();

            alertDialog.setSingleChoiceItems(selectedList, checkedItem[0], (dialog, which) -> {

                checkedItem[0] = which;

                subEdtTxt.setText(selectedList[which]);
                selectedSubject = selectedList[which];


                dialog.dismiss();
            });

            alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

            });

            AlertDialog customAlertDialog = alertDialog.create();

            customAlertDialog.show();
            Boolsubject = true;
    }

    String[] selectList(){
        String[] listItems = new String[]{};
        if (selectedBranch == "CO" && selectedSem == "SEM 6") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 5") {
            listItems = new String[]{"AJP", "CSS", "STE","OSY","EST"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 4") {
            listItems = new String[]{"JPR", "SEN", "MIC","GAD","DCC"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 3") {
            listItems = new String[]{"OOP", "CGR", "DTE","DSU","DMS"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 2") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEM 1") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 6") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 5") {
            listItems = new String[]{"AJP", "CSS", "STE","OSY","EST"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 4") {
            listItems = new String[]{"JPR", "SEN", "MIC","GAD","DCC"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 3") {
            listItems = new String[]{"OOP", "CGR", "DTE","DSU","DMS"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 2") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "IF" && selectedSem == "SEM 1") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 6") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 5") {
            listItems = new String[]{"AJP", "CSS", "STE","OSY","EST"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 4") {
            listItems = new String[]{"JPR", "SEN", "MIC","GAD","DCC"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 3") {
            listItems = new String[]{"OOP", "CGR", "DTE","DSU","DMS"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 2") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "ME" && selectedSem == "SEM 1") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 6") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 5") {
            listItems = new String[]{"AJP", "CSS", "STE","OSY","EST"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 4") {
            listItems = new String[]{"JPR", "SEN", "MIC","GAD","DCC"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 3") {
            listItems = new String[]{"OOP", "CGR", "DTE","DSU","DMS"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 2") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "AT" && selectedSem == "SEM 1") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        return listItems;
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Updated format
        return dateFormat.format(calendar.getTime());
    }

    void showBranchPicker(){
        branchEdt.clearFocus();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);

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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(attendence_selectinfo.this);


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
