package com.example.siot;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> urls=new ArrayList<>();

    public void update(String fname, String url)
    {
        items.add(fname);
        urls.add(url);
        notifyDataSetChanged();
    }

    public NotesAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls=urls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pdf_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameOfFile.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfFile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfFile=itemView.findViewById(R.id.notesText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=recyclerView.getChildLayoutPosition(view);
                    Intent intent=new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(urls.get(position)),"application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}
