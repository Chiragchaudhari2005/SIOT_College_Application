package com.example.siot;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nametv,authortv,semtv;
    ImageView imageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageview);
        nametv=itemView.findViewById(R.id.nametv);
        authortv=itemView.findViewById(R.id.authortv);
        semtv=itemView.findViewById(R.id.semtv);
    }
}
