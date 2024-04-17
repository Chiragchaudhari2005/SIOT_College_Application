package com.example.siot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class semesterList extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> items;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semesterlist);

        Intent intent = getIntent();
        String branch = intent.getStringExtra("branch");
        String sem = intent.getStringExtra("sem");

        recyclerView = findViewById(R.id.recylerview);
        List<Item> items=new ArrayList<Item>();
        myAdapter = new MyAdapter(getApplicationContext()); // Initialize the MyAdapter


        retrieveBookList(sem,branch);
    }

    void retrieveBookList(String sem, String branch) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = null;

        if ("CO".equals(branch) && ("SEM 5".equals(sem) || "SEM 6".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("TYCO");
            query = Collection;
        }
        else if ("CO".equals(branch) && ("SEM 3".equals(sem) || "SEM 4".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("SYCO");
            query = Collection;
        }
        else if ("CO".equals(branch) && ("SEM 1".equals(sem) || "SEM 2".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("FYCO");
            query = Collection;
        }
        else if ("IF".equals(branch) && ("SEM 5".equals(sem) || "SEM 6".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("TYIF");
            query = Collection;
        }
        else if ("IF".equals(branch) && ("SEM 3".equals(sem) || "SEM 4".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("SYIF");
            query = Collection;
        }
        else if ("IF".equals(branch) && ("SEM 1".equals(sem) || "SEM 2".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("FYIF");
            query = Collection;
        }
        else if ("ME".equals(branch) && ("SEM 5".equals(sem) || "SEM 6".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("TYME");
            query = Collection;
        }
        else if ("ME".equals(branch) && ("SEM 3".equals(sem) || "SEM 4".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("SYME");
            query = Collection;
        }
        else if ("ME".equals(branch) && ("SEM 1".equals(sem) || "SEM 2".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("FYME");
            query = Collection;
        }
        else if ("AT".equals(branch) && ("SEM 5".equals(sem) || "SEM 6".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("TYAT");
            query = Collection;
        }
        else if ("AT".equals(branch) && ("SEM 3".equals(sem) || "SEM 4".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("SYAT");
            query = Collection;
        }
        else if ("AT".equals(branch) && ("SEM 1".equals(sem) || "SEM 2".equals(sem))) {
            CollectionReference Collection = firestore.collection("LIBRARY").document(branch).collection("FYAT");
            query = Collection;
        }



        if (query != null) {
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<String> nameList = new ArrayList<>();
                        List<String> authorList = new ArrayList<>();
                        List<String> semList = new ArrayList<>();

                        for (DocumentSnapshot document : task.getResult()) {
                            String nameValue = document.getString("name");
                            String authorValue = document.getString("author");
                            String semValue = document.getString("sem");

                            nameList.add(nameValue);
                            authorList.add(authorValue);
                            semList.add(semValue);
                        }


                        // Update the data in the existing MyAdapter instance
                        myAdapter.setNameList(nameList);
                        myAdapter.setAuthorList(authorList);
                        myAdapter.setSemList(semList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(semesterList.this));
                        recyclerView.setAdapter(myAdapter);
                    } else {
                        String errorMessage = "Error retrieving data: " + task.getException().getMessage();
                        Toast.makeText(semesterList.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}