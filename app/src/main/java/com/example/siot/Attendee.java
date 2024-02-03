package com.example.siot;

public class Attendee {
    private String name;
    private String attendanceStatus;

    public Attendee() {
        // Default constructor required for Firestore
    }

    public Attendee(String name, String attendanceStatus) {
        this.name = name;
        this.attendanceStatus = attendanceStatus;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    // Setter methods (optional, but recommended)
    public void setName(String name) {
        this.name = name;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}

