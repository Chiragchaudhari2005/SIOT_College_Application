package com.example.siot;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class stuattmain extends AppCompatActivity {
    private String branch, date, subject, name;
    private long sem;
    private TextView dateTv, nameTv;
    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private AttendanceAdapter adapter;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stuattmain);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        branch = intent.getStringExtra("branch");
        sem = intent.getLongExtra("sem", 0);
        date = intent.getStringExtra("selectedDate");
        subject = intent.getStringExtra("selectedSubject");

        dateTv = findViewById(R.id.dateTv);
        recyclerView = findViewById(R.id.recyclerView);
        nameTv = findViewById(R.id.nameTv);

          if (date != null && name != null && subject != null) {
              dateTv.setText(date);
              nameTv.setText(name);

              firestore = FirebaseFirestore.getInstance();
              adapter = new AttendanceAdapter(); // Create your custom adapter
              recyclerView.setLayoutManager(new LinearLayoutManager(this));
              recyclerView.setAdapter(adapter);

              retrieveAttendance(date, name, subject);
          } else {
              Toast.makeText(this, "Some values are null", Toast.LENGTH_SHORT).show();
              // Handle the case where some values are null, perhaps by finishing the activity or displaying an error message
          }
    }

    void retrieveAttendance(String date, String name, String subject) {
        // Construct the Firestore query based on conditions

        //Toast.makeText(this, "name="+name, Toast.LENGTH_SHORT).show();
        Query query;
        if ("All Subjects".equals(subject)) {
            query = firestore.collection("TYCOATT")
                    .whereEqualTo("date", date)
                    .whereIn(name, Arrays.asList("Present", "Absent"));
        } else {
            query = firestore.collection("TYCOATT")
                    .whereEqualTo("date", date)
                    .whereEqualTo("subject", subject)
                    .whereIn(name, Arrays.asList("Present", "Absent"));
        }

        // Execute the query
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            // Inside the onComplete method of the query
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> subjectsList = new ArrayList<>();
                    List<String> timeList = new ArrayList<>();
                    List<String> statusList = new ArrayList<>();

                    for (DocumentSnapshot document : task.getResult()) {
                        // Assuming "subject", "time", and "status" are the fields you want to display
                        String subjectValue = document.getString("subject");
                        String timeValue = document.getString("time");
                        String statusValue = document.getString(name); // Use the name field as the key

                        subjectsList.add(subjectValue);
                        timeList.add(timeValue);
                        statusList.add(statusValue);
                    }

                    // Update the RecyclerView with the retrieved data
                    adapter.setSubjectsList(subjectsList);
                    adapter.setTimeList(timeList);
                    adapter.setStatusList(statusList);
                    adapter.notifyDataSetChanged();
                } else {
                    String errorMessage = "Error retrieving data: " + task.getException().getMessage();
                    Toast.makeText(stuattmain.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
