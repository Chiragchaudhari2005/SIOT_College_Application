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
        items.add(new Item("Applied Mathematics"));
        items.add(new Item("Linux Basics"));
        items.add(new Item("Basic Electronics and Electrical Engineering"));
        items.add(new Item("Professional Communication"));
        items.add(new Item("Web Page Designing"));
        items.add(new Item("Programming In 'C' "));
        items.add(new Item("Social and Life Skills"));


        ifsem2rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem2rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}