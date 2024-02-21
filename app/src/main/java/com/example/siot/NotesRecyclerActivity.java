package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class NotesRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String branch, sem, mainFolder, subFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_recycler_view);

        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        sem = intent.getStringExtra("sem");

        setFolders();
        Toast.makeText(this, "m=" + mainFolder + "s=" + subFolder, Toast.LENGTH_SHORT).show();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(mainFolder).child(subFolder);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String filename = snapshot.getKey(); //returns the file name
                String url = snapshot.getValue().toString(); //returns url for file name

                Toast.makeText(NotesRecyclerActivity.this, "file=" + filename + "url=" + url, Toast.LENGTH_SHORT).show();

                downloadPdfFromStorage(filename, url);
                ((NotesAdapter) recyclerView.getAdapter()).update(filename, url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        recyclerView = findViewById(R.id.recyclernotes);

        recyclerView.setLayoutManager(new LinearLayoutManager(NotesRecyclerActivity.this));
        NotesAdapter notesAdapter = new NotesAdapter(recyclerView, NotesRecyclerActivity.this, new ArrayList<String>(), new ArrayList<String>(), branch, sem);
        recyclerView.setAdapter(notesAdapter);
    }

    void setFolders() {
        if ("CO".equals(branch) && "SEM1".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem1";
        } else if ("CO".equals(branch) && "SEM2".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem2";
        }
        else if ("CO".equals(branch) && "SEM3".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem3";
        }
        else if ("CO".equals(branch) && "SEM4".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem4";
        }
        else if ("CO".equals(branch) && "SEM5".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem5";
        }
        else if ("CO".equals(branch) && "SEM6".equals(sem)) {
            mainFolder = "COMP_ENGG";
            subFolder = "sem6";
        }
    }

    void downloadPdfFromStorage(String filename, String url) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(url);

        // Create a local file to save the PDF
        File localFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), filename + ".pdf");

        storageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            Log.d("NotesRecyclerActivity", "PDF download successful");
            // TODO: Handle the downloaded PDF file, e.g., open it using a PDF viewer
        }).addOnFailureListener(exception -> {
            Log.e("NotesRecyclerActivity", "PDF download failed: " + exception.getMessage());
            // TODO: Handle the failure, e.g., show an error message to the user
        });
    }
}
