package com.example.siot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class notes_staff1 extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] arr = {"CO5I", "CO4I", "CO3I", "CO2I", "Hello", "world", "Hello", "world", "Hello", "world", "Hello", "world", "Hello", "world"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_staff1);
        Intent intent = getIntent();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter c = new CustomAdapter(arr);
        recyclerView.setAdapter(c);

    }
}
