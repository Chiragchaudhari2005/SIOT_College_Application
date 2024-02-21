// AvatarSelectionActivity.java
package com.example.siot;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AvatarSelectionActivity extends AppCompatActivity {

    private GridView gridView;
    private final String[] avatarImages = {"avatar1", "avatar2","avatar3", "avatar4","avatar5", "avatar6","avatar7", "avatar8",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection);

        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new AvatarAdapter());

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Return the selected avatar resource ID to the SettingsActivity
            int selectedImageResourceId = getResources().getIdentifier(avatarImages[position], "drawable", getPackageName());

            // Pass the selected avatar resource ID directly
            setResult(RESULT_OK, getIntent().putExtra("selectedImageResourceId", selectedImageResourceId));
            finish();
        });

    }

    private class AvatarAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return avatarImages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(AvatarSelectionActivity.this);
                imageView.setLayoutParams(new GridView.LayoutParams(200, 200)); // Adjust the size as needed
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            // Get the resource ID dynamically
            int imageResourceId = getResources().getIdentifier(avatarImages[position], "drawable", getPackageName());

            if (imageResourceId != 0) {
                // Check if the resource ID is valid
                imageView.setImageResource(imageResourceId);
            } else {
                // Handle the case where the resource ID is not found
                // You may set a default image or take other appropriate action
                imageView.setImageResource(R.drawable.default_avatar);
            }

            return imageView;
        }

    }
}
