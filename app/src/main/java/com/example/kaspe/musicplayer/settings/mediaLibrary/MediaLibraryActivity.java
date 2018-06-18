package com.example.kaspe.musicplayer.settings.mediaLibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kaspe.musicplayer.R;

public class MediaLibraryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_library);
        initializeUI();
    }

    private void initializeUI() {
        Button addFolderButton = findViewById(R.id.button_add_folder);

        addFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaLibraryActivity.this, AddFolderActivity.class);
                startActivity(intent);
            }
        });
    }
}
