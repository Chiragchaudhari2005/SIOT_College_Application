package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class semesterList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semesterlist);

        Intent intent = getIntent();
        String branch = intent.getStringExtra("branch");
        String sem = intent.getStringExtra("sem");

        RecyclerView recyclerView=findViewById(R.id.recylerview);
        List<Item> items=new ArrayList<Item>();


        if ("CO".equals(branch) && "sem1".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }
        if ("CO".equals(branch) && "sem2".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }
        if ("CO".equals(branch) && "sem3".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }
        if ("CO".equals(branch) && "sem4".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }
        if ("CO".equals(branch) && "sem5".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }
        if ("CO".equals(branch) && "sem6".equals(sem)){
            items.add(new Item("English"));
            items.add(new Item("Basic Science-PHY"));
            items.add(new Item("Basic Science-CHEM"));
            items.add(new Item("Basic Maths"));
            items.add(new Item("Workshop Practice"));
            items.add(new Item("Engineering Graphics"));
            items.add(new Item("Fundamentals of ICT"));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}