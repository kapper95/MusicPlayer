package com.example.kaspe.musicplayer.settings.mediaLibrary;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kaspe.musicplayer.R;
import com.example.kaspe.musicplayer.database.AppDatabase;
import com.example.kaspe.musicplayer.database.Folder;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MediaLibraryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_library);
        initializeUI();
    }

    LinearLayout linearLayout;

    private void initializeUI() {
        Button addFolderButton = findViewById(R.id.button_add_folder);

        linearLayout = findViewById(R.id.linearlayout_media_library);




       new FolderTask().execute();



        addFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaLibraryActivity.this, AddFolderActivity.class);
                startActivity(intent);
            }
        });
    }

    private class FolderTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... Voids) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database").build();

            List<Folder> folders = db.dao().getFolders();

            if(folders != null)
                for(Folder folder : folders){
                    TextView textView = new TextView(MediaLibraryActivity.this);
                    textView.setText(folder.getPath());
                    linearLayout.addView(textView);
                }
            return null;
        }
    }
}
