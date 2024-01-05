package com.example.siot;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class signup_activity extends AppCompatActivity {

    private EditText name_edtxt,enrollment_edtxt,branch_edtxt,email_edtxt,birthdate_edtxt,phone_edtxt;
    private Button registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        name_edtxt = findViewById(R.id.name_edtxt);
        enrollment_edtxt = findViewById(R.id.enrollment_edtxt);
        branch_edtxt = findViewById(R.id.branch_edtxt);
        email_edtxt = findViewById(R.id.email_edtxt);
        birthdate_edtxt = findViewById(R.id.birthdate_edtxt);
        phone_edtxt = findViewById(R.id.phone_edtxt);
        registerbtn = findViewById(R.id.registerbtn);

        String Name = name_edtxt.getText().toString();
        String Enrollment = enrollment_edtxt.getText().toString();
        String Branch = branch_edtxt.getText().toString();
        String Email = email_edtxt.getText().toString();
        String Birthdate = birthdate_edtxt.getText().toString();
        String phone = phone_edtxt.getText().toString();





    }
}
