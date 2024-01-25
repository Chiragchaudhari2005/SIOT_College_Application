package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class auto_engg_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_engg_sem2);

        RecyclerView autosem2rc=findViewById(R.id.auto_engg_sem2);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("English"));
        items.add(new Item("Basic Science-PHY"));
        items.add(new Item("Basic Science-CHEM"));
        items.add(new Item("Basic Maths"));
        items.add(new Item("Workshop Practice"));
        items.add(new Item("Engineering Graphics"));
        items.add(new Item("Fundamentals of ICT"));

        autosem2rc.setLayoutManager(new LinearLayoutManager(this));
        autosem2rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}