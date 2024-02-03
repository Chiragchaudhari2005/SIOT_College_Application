package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class attendence_selectinfo extends AppCompatActivity {

    private Button branchBtn,semBtn,subjectBtn,nextBtn;
    private String selectedBranch,selectedSem,selectedSubject,today;
    private Boolean Boolbranch = false;
    private Boolean Boolsem = false;
    private Boolean Boolsubject = false;
    private TextView dateTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_selectinfo);

        branchBtn = findViewById(R.id.branchBtn);
        semBtn = findViewById(R.id.semBtn);
        nextBtn = findViewById(R.id.nextBtn);
        subjectBtn = findViewById(R.id.subjectBtn);
        dateTv = findViewById(R.id.textView5);
        final int[] checkedItem = {-1};

        subjectBtn.setEnabled(false);

        today = getTodaysDate();
        dateTv.setText(today);

        branchBtn.setOnClickListener(new View.OnClickListener() {
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

      });

      nextBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if ((Boolbranch==true)&&(Boolsem==true)&&(Boolsubject==true)) {
                  Intent intent = new Intent(v.getContext(), attendence_list.class);
                  intent.putExtra("branch", selectedBranch);
                  intent.putExtra("sem", selectedSem);
                  intent.putExtra("subject", selectedSubject);
                  intent.putExtra("date", today);
                  startActivity(intent);
              }
              else {
                  Toast.makeText(attendence_selectinfo.this, "Select Branch,Sem,Subject!", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }

    String[] selectList(){
        String[] listItems = new String[]{};
        if (selectedBranch == "CO" && selectedSem == "SEMESTER6") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEMESTER5") {
            listItems = new String[]{"AJP", "CSS", "STE","OSY","EST"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEMESTER4") {
            listItems = new String[]{"JPR", "SEN", "MIC","GAD","DCC"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEMESTER3") {
            listItems = new String[]{"OOP", "CGR", "DTE","DSU","DMS"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEMESTER2") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        if (selectedBranch == "CO" && selectedSem == "SEMESTER1") {
            listItems = new String[]{"MAD", "PWP", "NIS","ETI","MGT","EDP"};
        }
        return listItems;
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Updated format
        return dateFormat.format(calendar.getTime());
    }
}
