package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class NotesRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String branch,sem,mainFolder,subFolder;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_recycler_view);

        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        sem = intent.getStringExtra("sem");

        setFolders();
        Toast.makeText(this, "m="+mainFolder+"s="+subFolder, Toast.LENGTH_SHORT).show();
        //Log.d(mainFolder, "main: "+mainFolder+"sub: "+subFolder);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(mainFolder).child(subFolder);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String filename=snapshot.getKey(); //returns the file name
                String url= snapshot.getValue().toString(); //returns url for file name

                ((NotesAdapter)recyclerView.getAdapter()).update(filename,url);
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

        recyclerView=findViewById(R.id.recyclernotes);

        recyclerView.setLayoutManager(new LinearLayoutManager(NotesRecyclerActivity.this));
        NotesAdapter notesAdapter=new NotesAdapter(recyclerView,NotesRecyclerActivity.this, new ArrayList<String>(), new ArrayList<String>(),branch,sem);
        recyclerView.setAdapter(notesAdapter);
    }

    void setFolders(){
        if("CO".equals(branch) && "SEM1".equals(sem)){
            mainFolder = "COMP_ENGG";
            subFolder = "sem1";
        }
        else if("CO".equals(branch) && "SEM6".equals(sem)){
            mainFolder = "COMP_ENGG";
            subFolder = "sem6";
        }
    }
}
