package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class comp_engg_sem3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_engg_sem3);

        RecyclerView compsem3rc=findViewById(R.id.comp_engg_sem3);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Object Oriented Programming Using C++"));
        items.add(new Item("Data Structure Using C"));
        items.add(new Item("Computer Graphics"));
        items.add(new Item("Database Management System"));
        items.add(new Item("Digital Techniques"));

        compsem3rc.setLayoutManager(new LinearLayoutManager(this));
        compsem3rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}