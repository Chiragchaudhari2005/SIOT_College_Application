package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class info_tech_sem5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech_sem5);

         RecyclerView ifsem5rc=findViewById(R.id.info_tech_sem5);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Environmental Studies"));
        items.add(new Item("Operating System"));
        items.add(new Item("Advance Java Programming"));
        items.add(new Item("Client Side Scripting"));
        items.add(new Item("Industrial Training"));
        items.add(new Item("Enterprenureship development"));
        items.add(new Item("Capstone Project Planning"));

        ifsem5rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem5rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}