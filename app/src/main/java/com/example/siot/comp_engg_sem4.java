package com.example.siot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class comp_engg_sem4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_engg_sem4);

        RecyclerView compsem4rc=findViewById(R.id.comp_engg_sem4);
        List<Item> items=new ArrayList<Item>();
        items.add(new Item("Java Programming"));
        items.add(new Item("Software Engineering"));
        items.add(new Item("Data Communication and Computer Network"));
        items.add(new Item("Microprocessor"));
        items.add(new Item("GUI Application Development using Vb.net"));

        compsem4rc.setLayoutManager(new LinearLayoutManager(this));
        compsem4rc.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}