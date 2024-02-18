package com.example.siot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FetchNotes extends AppCompatActivity {

    Button getbranch, getsem, fetchbtn;
    String selectedBranch, selectedSem;
    final int[] checkedItem = {-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_notes);
        fetchbtn=findViewById(R.id.fetchfilebtn);
        getbranch=findViewById(R.id.branchBtnnotesget);

        getbranch.setOnClickListener(new View.OnClickListener() {
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
        });

        fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FetchNotes.this,NotesRecyclerActivity.class));
            }
        });
    }
}