package com.example.siot;

import static com.example.siot.R.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NotesUpload extends AppCompatActivity {

    Button selectfile, upload, branchnotes, semBtn;
    TextView notifcation;
    String selectedBranch,selectedSem;
    Uri pdfUri;
    String mainFolder = new String();
    String subFolder = new String();

    FirebaseStorage storage; //used to upload the files
    FirebaseDatabase database; //used to store the URL of files uploaded

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_notes_upload);

        storage=FirebaseStorage.getInstance(); //returns an object or instance of firebase storage
        database=FirebaseDatabase.getInstance(); //returns an object or instance of firebase database

        selectfile=findViewById(R.id.selectfilebtn);
        upload=findViewById(R.id.Uploadbtn);
        notifcation=findViewById(R.id.notification);
        branchnotes=findViewById(R.id.branchBtnnotes);
        semBtn=findViewById(R.id.semBtnnotes);
        final int[] checkedItem = {-1};



        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(NotesUpload.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(NotesUpload.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfUri!=null)
                {
                    uploadFile(pdfUri);
                }
                else
                {
                   Toast.makeText(NotesUpload.this,"select a file",Toast.LENGTH_SHORT).show();
                }
            }
        });

        branchnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                branchnotes.clearFocus();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NotesUpload.this);

                alertDialog.setTitle("Choose branch");  // title of the alert dialog

                final String[] listItems = new String[]{"CO", "IF", "ME", "AE", "CE"};

                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;

                        branchnotes.setText(listItems[which]);
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

        semBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    semBtn.clearFocus();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NotesUpload.this);


                    alertDialog.setTitle("Choose branch");

                    final String[] listItems = new String[]{"SEM1", "SEM2", "SEM3", "SEM4", "SEM5", "SEM6"};

                    alertDialog.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {

                        checkedItem[0] = which;

                        semBtn.setText(listItems[which]);
                        selectedSem = listItems[which];

                        dialog.dismiss();
                    });

                    alertDialog.setNegativeButton("Cancel", (dialog, which) -> {

                    });

                    AlertDialog customAlertDialog = alertDialog.create();

                    customAlertDialog.show();
            }
        });

    }

    private void uploadFile(Uri pdfUri)
    {

        final String FileName=System.currentTimeMillis()+".pdf";
        final String FileName1=System.currentTimeMillis()+"";

        StorageReference reference=storage.getReference();

        Toast.makeText(this, selectedBranch, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, selectedSem, Toast.LENGTH_SHORT).show();

        if ("CO".equals(selectedBranch) && "SEM1".equals(selectedSem)){
            mainFolder = "COMP_ENGG";
            subFolder = "sem1";
        }
        else if ("CO".equals(selectedBranch) && "SEM2".equals(selectedSem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem2";
        }
        else if ("CO".equals(selectedBranch) && "SEM3".equals(selectedSem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem3";
        }
        else if ("CO".equals(selectedBranch) && "SEM4".equals(selectedSem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem4";
        }
        else if ("CO".equals(selectedBranch) && "SEM5".equals(selectedSem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem5";
        }
        else if ("CO".equals(selectedBranch) && "SEM6".equals(selectedSem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem6";
        }

        if ("IF".equals(selectedBranch) && "SEM1".equals(selectedSem)){
            mainFolder = "IF_ENGG";
            subFolder = "sem1";
        }
        else if ("IF".equals(selectedBranch) && "SEM2".equals(selectedSem)) {
            mainFolder = "IF_ENGG";
            subFolder = "sem2";
        }
        else if ("IF".equals(selectedBranch) && "SEM3".equals(selectedSem)) {
            mainFolder = "IF_ENGG";
            subFolder = "sem3";
        }
        else if ("IF".equals(selectedBranch) && "SEM4".equals(selectedSem)) {
            mainFolder = "IF_ENGG";
            subFolder = "sem4";
        }
        else if ("IF".equals(selectedBranch) && "SEM5".equals(selectedSem)) {
            mainFolder = "IF_ENGG";
            subFolder = "sem5";
        }
        else if ("IF".equals(selectedBranch) && "SEM6".equals(selectedSem)) {
            mainFolder = "IF_ENGG";
            subFolder = "sem6";
        }

        if ("ME".equals(selectedBranch) && "SEM1".equals(selectedSem)){
            mainFolder = "ME_ENGG";
            subFolder = "sem1";
        }
        else if ("ME".equals(selectedBranch) && "SEM2".equals(selectedSem)) {
            mainFolder = "ME_ENGG";
            subFolder = "sem2";
        }
        else if ("ME".equals(selectedBranch) && "SEM3".equals(selectedSem)) {
            mainFolder = "ME_ENGG";
            subFolder = "sem3";
        }
        else if ("ME".equals(selectedBranch) && "SEM4".equals(selectedSem)) {
            mainFolder = "ME_ENGG";
            subFolder = "sem4";
        }
        else if ("ME".equals(selectedBranch) && "SEM5".equals(selectedSem)) {
            mainFolder = "ME_ENGG";
            subFolder = "sem5";
        }
        else if ("ME".equals(selectedBranch) && "SEM6".equals(selectedSem)) {
            mainFolder = "ME_ENGG";
            subFolder = "sem6";
        }

        if ("AE".equals(selectedBranch) && "SEM1".equals(selectedSem)){
            mainFolder = "AE_ENGG";
            subFolder = "sem1";
        }
        else if ("AE".equals(selectedBranch) && "SEM2".equals(selectedSem)) {
            mainFolder = "AE_ENGG";
            subFolder = "sem2";
        }
        else if ("AE".equals(selectedBranch) && "SEM3".equals(selectedSem)) {
            mainFolder = "AE_ENGG";
            subFolder = "sem3";
        }
        else if ("AE".equals(selectedBranch) && "SEM4".equals(selectedSem)) {
            mainFolder = "AE_ENGG";
            subFolder = "sem4";
        }
        else if ("AE".equals(selectedBranch) && "SEM5".equals(selectedSem)) {
            mainFolder = "AE_ENGG";
            subFolder = "sem5";
        }
        else if ("AE".equals(selectedBranch) && "SEM6".equals(selectedSem)) {
            mainFolder = "AE_ENGG";
            subFolder = "sem6";
        }

        if ("CE".equals(selectedBranch) && "SEM1".equals(selectedSem)){
            mainFolder = "CE_ENGG";
            subFolder = "sem1";
        }
        else if ("CE".equals(selectedBranch) && "SEM2".equals(selectedSem)) {
            mainFolder = "CE_ENGG";
            subFolder = "sem2";
        }
        else if ("CE".equals(selectedBranch) && "SEM3".equals(selectedSem)) {
            mainFolder = "CE_ENGG";
            subFolder = "sem3";
        }
        else if ("CE".equals(selectedBranch) && "SEM4".equals(selectedSem)) {
            mainFolder = "CE_ENGG";
            subFolder = "sem4";
        }
        else if ("CE".equals(selectedBranch) && "SEM5".equals(selectedSem)) {
            mainFolder = "CE_ENGG";
            subFolder = "sem5";
        }
        else if ("CE".equals(selectedBranch) && "SEM6".equals(selectedSem)) {
            mainFolder = "CE_ENGG";
            subFolder = "sem6";
        }


        reference.child(mainFolder).child(subFolder).child(FileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Get the download URL
                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                downloadUrlTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();

                        DatabaseReference reference1 = database.getReference(mainFolder).child(subFolder).child(FileName1);
                        reference1.setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(NotesUpload.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(NotesUpload.this, "File Upload Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NotesUpload.this, "File Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        selectPdf();
       // if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           // selectPdf();
       // }// else
          //  Toast.makeText(NotesUpload.this, "Please Grant The Permission", Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {

        Intent intent=new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT); //to fetch the files
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null)
        {
            pdfUri=data.getData();
            notifcation.setText("File selected:"+data.getData().getLastPathSegment());
        }
        else
        {
            Toast.makeText(NotesUpload.this,"Please Select File",Toast.LENGTH_SHORT).show();
        }

    }

    protected void getData()
    {

    }
}