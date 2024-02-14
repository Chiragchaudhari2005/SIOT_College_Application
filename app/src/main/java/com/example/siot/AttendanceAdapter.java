package com.example.siot;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    private List<String> subjectsList;
    private List<String> timeList; // New list for time
    private List<String> statusList; // New list for status

    public void setSubjectsList(List<String> subjectsList) {
        this.subjectsList = subjectsList;
    }

    // Add methods to set timeList and statusList
    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendence_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String subject = subjectsList.get(position);
        String time = timeList.get(position); // Retrieve time for the current position
        String status = statusList.get(position); // Retrieve status for the current position

        holder.subjectTextView.setText(subject);
        String convertedTime = convertTo12HourFormat(time); // Use the 'time' variable
        holder.timeTextView.setText(convertedTime); // Set time in the TextView
        holder.statusTextView.setText(status); // Set status in the TextView

        if ("Present".equals(status)) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.linearLayout.getContext(), R.drawable.list_bg_green));
        } else {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.linearLayout.getContext(), R.drawable.list_bg_red));
        }
    }

    // Helper method to convert time to 12-hour format
    private String convertTo12HourFormat(String time) {
        try {
            // Assuming time is in 24-hour format (HH:mm)
            return DateFormat.format("hh:mm a", new SimpleDateFormat("HH:mm").parse(time)).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return time; // Return the original time in case of error
        }
    }

    @Override
    public int getItemCount() {
        return subjectsList != null ? subjectsList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectTextView;
        public TextView timeTextView; // TextView for time
        public TextView statusTextView; // TextView for status
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView); // Initialize time TextView
            statusTextView = itemView.findViewById(R.id.statusTextView); // Initialize status TextView
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
