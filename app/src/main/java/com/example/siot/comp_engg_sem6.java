package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class comp_engg_sem6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_engg_sem6);

        RecyclerView compsem6rc=findViewById(R.id.comp_engg_sem6);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Management",R.drawable.book));
        items.add(new Item("Programming with Python",R.drawable.book));
        items.add(new Item("Mobile Application Development",R.drawable.book));
        items.add(new Item("Emerging Trends in Computer and Information Technology",R.drawable.book));
        items.add(new Item("Network and Information Security",R.drawable.book));
        items.add(new Item("Entrepreneuship Development",R.drawable.book));
        items.add(new Item("Capstone Project- Execution",R.drawable.book));

        compsem6rc.setLayoutManager(new LinearLayoutManager(this));
        compsem6rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}