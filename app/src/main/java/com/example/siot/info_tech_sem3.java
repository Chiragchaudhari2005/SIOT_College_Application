package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        items.add(new Item("Object Oriented Programming Using C++"));
        items.add(new Item("Data Structures Using C"));
        items.add(new Item("Principles of Database"));
        items.add(new Item("Data Communication"));
        items.add(new Item("Digital Techniques and Microprocessor"));
        items.add(new Item("Applied Multimedia Techniques"));


        ifsem3rc.setLayoutManager(new LinearLayoutManager(this));
        ifsem3rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}