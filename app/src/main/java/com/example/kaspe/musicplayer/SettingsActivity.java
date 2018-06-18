package com.example.kaspe.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.kaspe.musicplayer.settings.mediaLibrary.MediaLibraryActivity;

import static android.content.ContentValues.TAG;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeUI();
    }

    private void initializeUI() {
        TextView mediaLibraryTextView = findViewById(R.id.media_library);
        Log.d(TAG, "initializeUI: " + "test");

        mediaLibraryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MediaLibraryActivity.class);
                startActivity(intent);
            }
        });
    }
}
