package com.example.kaspe.musicplayer;

import android.Manifest;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kaspe.musicplayer.database.AppDatabase;
import com.example.kaspe.musicplayer.database.Folder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    private final int PREMISSION_REQUEST_CODE_EXTERNAL_STORAGE = 0;
    private MediaPlayerHolder mediaPlayerHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PREMISSION_REQUEST_CODE_EXTERNAL_STORAGE);
        }
        else initializeUI();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PREMISSION_REQUEST_CODE_EXTERNAL_STORAGE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                initializeUI();
            }
        }
    }

    private void initializeUI() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/01. Billy Talent - Big Red Gun.flac");

        mediaPlayerHolder = new MediaPlayerHolder();

        mediaPlayerHolder.loadMedia(file.getAbsolutePath());
        Button settingsButton = findViewById(R.id.button_settings);
        Button playButton = findViewById(R.id.button_play);
        Button pauseButton = findViewById(R.id.button_pause);


        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(file.getAbsolutePath());

        Log.d(TAG, "initializeUI: album " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
        Log.d(TAG, "initializeUI: album artist " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST));
        Log.d(TAG, "initializeUI: artist " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
        Log.d(TAG, "initializeUI: nr " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER));
        Log.d(TAG, "initializeUI: date " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE));
        Log.d(TAG, "initializeUI: disc nr " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER));
        Log.d(TAG, "initializeUI: duration " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        Log.d(TAG, "initializeUI: Title " + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));

        Log.d(TAG, "initializeUI: size " + file.length());
        Log.d(TAG, "initializeUI: size parent " + file.getParentFile().lastModified());


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerHolder.play();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayerHolder.pause();
            }
        });

        new MediaSannerStartTask().execute();
    }

    private class MediaSannerStartTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database").build();
            List<Folder> folders = db.dao().getFolders();
            for (Folder folder: folders) {
                new MediaScanner(getApplicationContext()).execute(folder.getPath());
            }
            return null;
        }
    }
}
