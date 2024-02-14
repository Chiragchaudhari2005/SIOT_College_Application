package com.example.siot;

import static com.example.siot.R.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

    Button selectfile, upload, branchnotes, semesternotes;
    TextView notifcation;
    Uri pdfUri;

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
        semesternotes=findViewById(R.id.semBtnnotes);



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
    }

    private void uploadFile(Uri pdfUri)
    {

        final String FileName=System.currentTimeMillis()+"";
        StorageReference reference=storage.getReference();

        reference.child("COMP_ENGG").child("sem6").child(FileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url=taskSnapshot.getUploadSessionUri().toString();

                DatabaseReference reference1=database.getReference();
                reference1.child(FileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(NotesUpload.this,"File Uploaded Successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(NotesUpload.this,"File Upload Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NotesUpload.this,"File Upload Failed",Toast.LENGTH_SHORT).show();
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