package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class comp_engg_sem5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_engg_sem5);

        RecyclerView compsem5rc=findViewById(R.id.comp_engg_sem5);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Environmental Studies"));
        items.add(new Item("Operating Systems"));
        items.add(new Item("Advance Java Programming"));
        items.add(new Item("Software Testing"));
        items.add(new Item("Client Side Scripting"));
        items.add(new Item("Industrial Training"));
        items.add(new Item("Capstone Project Planning"));

        compsem5rc.setLayoutManager(new LinearLayoutManager(this));
        compsem5rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}