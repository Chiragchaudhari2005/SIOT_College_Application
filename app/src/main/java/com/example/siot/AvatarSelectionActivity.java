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
    private final int[] avatarImages = {R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg,R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg,R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_selection);

        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new AvatarAdapter());

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Return the selected avatar to the SettingsActivity
            int selectedAvatar = avatarImages[position];
            setResult(RESULT_OK, getIntent().putExtra("selectedAvatar", selectedAvatar));
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

            imageView.setImageResource(avatarImages[position]);
            return imageView;
        }
    }
}
