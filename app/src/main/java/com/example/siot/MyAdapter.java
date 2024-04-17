package com.example.siot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    private List<String> nameList;
    private List<String> authorList; // New list for time
    private List<String> semList; // New list for status

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
        notifyDataSetChanged();
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
        notifyDataSetChanged();
    }

    public void setSemList(List<String> semList) {
        this.semList = semList;
        notifyDataSetChanged();
    }


    public MyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.bnameView.setText(items.get(position).getBname());
        holder.nametv.setText(nameList.get(position));
        holder.authortv.setText(authorList.get(position));
        holder.semtv.setText(semList.get(position));
        holder.imageView.setImageResource(R.drawable.book);
    }

    @Override
    public int getItemCount() {
        if (nameList != null) {
            return nameList.size();
        } else {
            return 0; // or any default value depending on your logic
        }
    }
}
