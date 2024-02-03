package com.example.siot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] localDataSet;
    private TextView textView;
    private TextView textView2;
    private OnDataChangedListener onDataChangedListener;
    private List<ArrayList<String>> data;
    private String[] attendenceList;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */


    // Step 1: Initialize the dataset of the Adapter.+
    public RecyclerAdapter(String[] dataSet, Context context, OnDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
        localDataSet = dataSet;
        data = new ArrayList<>();
        for (String name : localDataSet) {
            ArrayList<String> item = new ArrayList<>();
            item.add(name);
            item.add("Present");  // Default attendance status
            data.add(item);
        }
    }

    private void notifyDataChanged() {
        // Call the interface method to notify the activity
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChanged(data);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private Switch switchToggle;
        private LinearLayout linearLayout;
        private Button saveBtn;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.nametv);
            switchToggle = view.findViewById(R.id.attSwitch);
            linearLayout = view.findViewById(R.id.linearLayout1);


           // setLayoutColor(ContextCompat.getColor(view.getContext(), R.color.light_green));
            linearLayout.setBackground(ContextCompat.getDrawable(linearLayout.getContext(), R.drawable.list_bg_green));
                attendenceList = localDataSet;

            switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = getAbsoluteAdapterPosition();
                    // Perform some action when the Switch is checked or unchecked

                        if (isChecked) {
                            //linearLayout.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.light_red));
                            linearLayout.setBackground(ContextCompat.getDrawable(linearLayout.getContext(), R.drawable.list_bg_red));
                            if (adapterPosition != RecyclerView.NO_POSITION) {
                                String studentName = localDataSet[adapterPosition];
                                String attendanceStatus = "Absent"; // Set default value

                                // Update the attendanceList
                                updateAttendanceList(adapterPosition, studentName, attendanceStatus);
                            }
                        } else {
                            //linearLayout.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.light_green));
                            linearLayout.setBackground(ContextCompat.getDrawable(linearLayout.getContext(), R.drawable.list_bg_green));
                        }
                  //  }
                }
            });

        }
        
        private void updateAttendanceList(int position, String studentName, String attendanceStatus) {
            ArrayList<String> item = data.get(position);
            item.set(1, attendanceStatus);
            notifyDataChanged();
        }
        public TextView getTextView() {
            return textView;
        }
        private void setLayoutColor(int color) {
            linearLayout.setBackgroundColor(color);
        }
    }


    // Step 2: Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.attendence_listlayout, viewGroup, false);
        textView = view.findViewById(R.id.nametv);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(localDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
