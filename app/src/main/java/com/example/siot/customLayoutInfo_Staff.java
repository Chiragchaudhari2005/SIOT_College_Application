package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class customLayoutInfo_Staff extends AppCompatActivity {
    private Button viewBtn;
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customlayoutinfo);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customLayoutInfo_Staff.this,studentDetailsStaff.class);
                startActivity(intent);
            }
        });
   }
}

