package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class info_tech_sem6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech_sem6);

        RecyclerView ifsem6rc=findViewById(R.id.info_tech_sem6);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Management",R.drawable.book));
        items.add(new Item("Mobile Application Development",R.drawable.book));
        items.add(new Item("Emerging Trends in Computer and Information Technology",R.drawable.book));
        items.add(new Item("Wireless and Mobile Networks",R.drawable.book));
        items.add(new Item("Network and information Security",R.drawable.book));
        items.add(new Item("Capstone Project- Execution",R.drawable.book));

        ifsem6rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem6rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}