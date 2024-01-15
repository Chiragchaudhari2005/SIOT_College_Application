package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class info_tech_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tech_sem2);

        RecyclerView ifsem2rc=findViewById(R.id.info_tech_sem2);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Applied Mathematics",R.drawable.book));
        items.add(new Item("Linux Basics",R.drawable.book));
        items.add(new Item("Basic Electronics and Electrical Engineering",R.drawable.book));
        items.add(new Item("Professional Communication",R.drawable.book));
        items.add(new Item("Web Page Designing",R.drawable.book));
        items.add(new Item("Programming In 'C' ",R.drawable.book));
        items.add(new Item("Social and Life Skills",R.drawable.book));


        ifsem2rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem2rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}