package com.example.siot;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class attendence_list extends AppCompatActivity implements OnDataChangedListener {

    private String branch,sem,subject,date,collectionName;
    private RecyclerView recyclerView;
    private Button saveBtn;
    private RecyclerAdapter recyclerAdapter;
    private String[] namesArray;
    List<ArrayList<String>> updatedAttendenceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_list);

        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        sem = intent.getStringExtra("sem");
        subject = intent.getStringExtra("subject");
        date = intent.getStringExtra("date");

        recyclerView = findViewById(R.id.recyclerView);
        saveBtn = findViewById(R.id.saveBtn);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        setCollectionName(branch,sem);
        retrieveNameList();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    void setCollectionName(String branch,String sem){
        if ("CO".equals(branch)) {
            if ("SEMESTER6".equals(sem) || "SEMESTER5".equals(sem)) {
                collectionName = "TYCO";
            } else if ("4SEMESTER".equals(sem) || "SEMESTER3".equals(sem)) {
                collectionName = "SYCO";
            } else if ("SEMESTER2".equals(sem) || "SEMESTER1".equals(sem)) {
                collectionName = "FYCO";
            }
        }
    }

    void retrieveNameList(){
        // Code to retrieve namelist from Firebase for all documents in the collection

        CollectionReference collectionRef = FirebaseFirestore.getInstance().collection(collectionName);
        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> nameList = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        if (name != null) {
                            nameList.add(name);
                        }
                    }

                    // Convert the list to an array if needed
                    namesArray = nameList.toArray(new String[0]);

                    recyclerView.setLayoutManager(new LinearLayoutManager(attendence_list.this));
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(namesArray, recyclerView.getContext(),attendence_list.this);
                    recyclerView.setAdapter(recyclerAdapter);
                } else {
                    // Handle errors in retrieving the documents
                    Exception exception = task.getException();
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    void showConfirmationDialog() {
        //Code for alert dialog which is displayed after clicking on the save btn
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure to save attendance in the database?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String collection = getCollectionName(branch, sem);
                updateIntoDatabase(collection,collectionName,subject);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    String getCollectionName(String branch, String sem){
        if ("CO".equals(branch)) {
            if ("SEMESTER6".equals(sem) || "SEMESTER5".equals(sem)) {
                collectionName = "TYCOATT";
            } else if ("4SEMESTER".equals(sem) || "SEMESTER3".equals(sem)) {
                collectionName = "SYCOATT";
            } else if ("SEMESTER2".equals(sem) || "SEMESTER1".equals(sem)) {
                collectionName = "FYCOATT";
            }
        }
        return collectionName;
    }

    @Override
    public void onDataChanged(List<ArrayList<String>> newData) {
        for (ArrayList<String> item : newData) {
            String studentName = item.get(0);
            String attendanceStatus = item.get(1);
            updatedAttendenceList = newData;
        }
    }

    void updateIntoDatabase(String collection, String collectionNorm,String subject) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new document with an automatically generated ID
        DocumentReference documentReference = db.collection(collection).document();

        // Create a map to store the data
        Map<String, Object> data = new HashMap<>();

        String currentDate = getCurrentDate();
        String currentDay = getCurrentDay();
        String currentTime = getCurrentTime();

        // Add date, day, and time to the map
        data.put("date", currentDate);
        data.put("day", currentDay);
        data.put("time", currentTime);
        data.put("subject",subject);

        for (ArrayList<String> item : updatedAttendenceList) {
            String studentName = item.get(0);
            String attendanceStatus = item.get(1);

            // Add the data to the map
            data.put(studentName, attendanceStatus);

            CollectionReference collectionReference = FirebaseFirestore.getInstance().collection(collectionNorm);

            // Fetch student's phone number from "TYCO" collection
            collectionReference.whereEqualTo("name", studentName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String phoneNumber = document.getString("phoneNumber");
                            if (phoneNumber != null && attendanceStatus.equals("Absent")) {
                                sendNotification(phoneNumber, "You were absent for today's "+subject+" lecture");
                            }
                        }
                    } else {
                        // Handle errors
                        Exception exception = task.getException();
                        if (exception != null) {
                            exception.printStackTrace();
                        }
                    }
                }

            });
        }

        // Set the map as the data for the document
        documentReference.set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(attendence_list.this, "Attendance saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle errors
                            Exception exception = task.getException();
                            if (exception != null) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void sendNotification(String phoneNumber, String message) {
        // Implement FCM logic to send a notification to the student using their phone number
        // You can use Firebase Cloud Messaging APIs to send notifications.
        // Refer to the official documentation for implementation details: https://firebase.google.com/docs/cloud-messaging
        // Note: Implementing FCM requires server-side code for sending notifications.

        // For demonstration purposes, assuming you have a server that sends FCM messages
        // The 'to' field should contain the FCM token of the device corresponding to the phone number

        FirebaseFirestore.getInstance().collection("TYCO")
                .whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String fcmToken = document.getString("fcmToken");
                                if (fcmToken != null) {
                                    // Here, you would typically send the FCM message to your server
                                    // For demonstration purposes, just printing the message
                                    System.out.println("Sending FCM message to: " + fcmToken);
                                    System.out.println("Message: " + message);
                                    Toast.makeText(attendence_list.this, "sms sent!", Toast.LENGTH_SHORT).show();
                                    // You can use your server to send FCM messages with the provided fcmToken and message
                                    // Refer to the official documentation for server-side FCM implementation
                                    // https://firebase.google.com/docs/cloud-messaging/server
                                }
                            }
                        } else {
                            // Handle errors
                            Exception exception = task.getException();
                            if (exception != null) {
                                exception.printStackTrace();
                            }
                        }
                    }
                });
    }


    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    private String getCurrentDay() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return dayFormat.format(Calendar.getInstance().getTime());
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return timeFormat.format(Calendar.getInstance().getTime());
    }


}
