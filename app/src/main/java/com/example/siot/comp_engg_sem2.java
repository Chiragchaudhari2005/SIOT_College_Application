package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class comp_engg_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_engg_sem2);

        RecyclerView compsem2rc=findViewById(R.id.comp_engg_sem2);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Programming in 'C'",R.drawable.book));
        items.add(new Item("Applied Mathematics",R.drawable.book));
        items.add(new Item("Linux Basics",R.drawable.book));
        items.add(new Item("Web Page Designing",R.drawable.book));
        items.add(new Item("Professional Communication",R.drawable.book));
        items.add(new Item("Basic Electrical and Electronics Engineering",R.drawable.book));

        compsem2rc.setLayoutManager(new LinearLayoutManager(this));
        compsem2rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}