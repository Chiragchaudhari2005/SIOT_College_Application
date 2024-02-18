// SettingsActivity.java
package com.example.siot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class settings extends AppCompatActivity {

    private Button avatarBtn;
    private Button logoutBtn;
    private static final int REQUEST_CODE_AVATAR_SELECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        avatarBtn = findViewById(R.id.avatarBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        avatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AvatarSelectionActivity to choose an avatar
                Intent intent = new Intent(settings.this, AvatarSelectionActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AVATAR_SELECTION);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log out user from Firebase
                FirebaseAuth.getInstance().signOut();

                // Navigate to the login screen
                Intent intent = new Intent(settings.this, screen_one.class);
                // Clear the back stack so that the user cannot go back to the SettingsActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AVATAR_SELECTION && resultCode == RESULT_OK) {
            // Handle the selected avatar name
            String selectedAvatar = data.getStringExtra("selectedAvatar");

            // Do something with the selected avatar name (e.g., update UI or save it)
            Toast.makeText(this, "Selected Avatar: " + selectedAvatar, Toast.LENGTH_SHORT).show();

            updateAvatarInFirestore(selectedAvatar);
        }
    }

    private void updateAvatarInFirestore(String selectedAvatar) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();

            // Assuming "users" is the collection name in Firestore
            // Replace "avatar" with the actual field name in your user document
            FirebaseFirestore.getInstance().collection("TYCO").document(userId)
                    .update("avatar", selectedAvatar)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Update successful
                            Toast.makeText(settings.this, "Avatar updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Update failed
                            Toast.makeText(settings.this, "Failed to update avatar", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}
