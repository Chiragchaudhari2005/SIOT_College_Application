package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class semester1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1);

        RecyclerView recyclerView=findViewById(R.id.recylerview);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("English",R.drawable.book));
        items.add(new Item("Basic Science-PHY",R.drawable.book));
        items.add(new Item("Basic Science-CHEM",R.drawable.book));
        items.add(new Item("Basic Maths",R.drawable.book));
        items.add(new Item("Workshop Practice",R.drawable.book));
        items.add(new Item("Engineering Graphics",R.drawable.book));
        items.add(new Item("Fundamentals of ICT",R.drawable.book));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}