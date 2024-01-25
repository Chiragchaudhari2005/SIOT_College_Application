package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class mech_engg_sem1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mech_engg_sem1);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView mechsem1rc=findViewById(R.id.mech_engg_sem1);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("English"));
        items.add(new Item("Basic Science-PHY"));
        items.add(new Item("Basic Science-CHEM"));
        items.add(new Item("Basic Maths"));
        items.add(new Item("Workshop Practice"));
        items.add(new Item("Engineering Graphics"));
        items.add(new Item("Fundamentals of ICT"));

        mechsem1rc.setLayoutManager(new LinearLayoutManager(this));
        mechsem1rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}