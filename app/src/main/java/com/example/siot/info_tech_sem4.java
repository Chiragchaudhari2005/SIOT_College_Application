package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class info_tech_sem4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech_sem4);

        RecyclerView recyclerView=findViewById(R.id.info_tech_sem4);

        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Java Programming",R.drawable.book));
        items.add(new Item("Software Engineering",R.drawable.book));
        items.add(new Item("Database Management",R.drawable.book));
        items.add(new Item("Computer Network",R.drawable.book));
        items.add(new Item("GUI Application Development using Vb.net",R.drawable.book));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}