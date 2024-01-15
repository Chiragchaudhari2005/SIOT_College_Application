package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class info_tech_sem3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech_sem3);

        RecyclerView ifsem3rc=findViewById(R.id.info_tech_sem3);

        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Object Oriented Programming Using C++",R.drawable.book));
        items.add(new Item("Data Structures Using C",R.drawable.book));
        items.add(new Item("Principles of Database",R.drawable.book));
        items.add(new Item("Data Communication",R.drawable.book));
        items.add(new Item("Digital Techniques and Microprocessor",R.drawable.book));
        items.add(new Item("Applied Multimedia Techniques",R.drawable.book));


        ifsem3rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem3rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}