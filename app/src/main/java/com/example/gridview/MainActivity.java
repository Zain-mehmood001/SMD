package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    String[] gridViewStrings = {"Camera", "Camera Roll", "Featured", "My Videos", "Likes", "Watch Later", "Stats", "Subscriptions", "Help"};

    int[] gridViewImages = {
            R.drawable.camera, R.drawable.camera_roll, R.drawable.advertising,
            R.drawable.tv_screen, R.drawable.social_media, R.drawable.clock,
            R.drawable.stats, R.drawable.flag, R.drawable.question_mark
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        CustomAdapter adapt = new CustomAdapter(MainActivity.this, gridViewStrings, gridViewImages);
        gridView.setAdapter(adapt);

    }
}