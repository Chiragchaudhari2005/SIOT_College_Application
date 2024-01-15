package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
        items.add(new Item("Environmental Studies",R.drawable.book));
        items.add(new Item("Operating System",R.drawable.book));
        items.add(new Item("Advance Java Programming",R.drawable.book));
        items.add(new Item("Client Side Scripting",R.drawable.book));
        items.add(new Item("Industrial Training",R.drawable.book));
        items.add(new Item("Enterprenureship development",R.drawable.book));
        items.add(new Item("Capstone Project Planning",R.drawable.book));

        ifsem5rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem5rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}