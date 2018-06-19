package com.example.kaspe.musicplayer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kaspe.musicplayer.database.Album;
import com.example.kaspe.musicplayer.database.AppDatabase;
import com.example.kaspe.musicplayer.database.Track;

import java.io.File;

import static android.content.ContentValues.TAG;

public class MediaScanner extends AsyncTask<String, Void, Void> {

    private Context context;

    public MediaScanner(Context applicationContext) {
        super();
        context = applicationContext;
    }

    @Override
    protected Void doInBackground(String... strings) {
        File file = new File(strings[0]);
        findSubFolders(file);
        return null;
    }

    private void findSubFolders(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i< files.length; i++){
            File subFile = files[i];
            Log.d(TAG, "findSubFolders: " + subFile.getName());
            if(subFile.isDirectory())
                findSubFolders(subFile);
            else{
                saveIfMediaFile(subFile);
            }
        }
    }

    private void saveIfMediaFile(File file) {
        int lastDot = file.getName().lastIndexOf(".");
        String extension = file.getName().substring(lastDot+1);
        if(!isMediaFile(extension)) return;

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(file.getAbsolutePath());

        String albumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        int number = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER));
        String date = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
        int discNr = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER));
        int duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        long lastModified =  file.lastModified();

        Album album = new Album();
        album.setAlbum(albumName);
        album.setArtist(artist);
        album.setDate(date);

        Track track = new Track();
        track.setAlbum(albumName);
        track.setDisc(discNr);
        track.setDuration(duration);
        track.setLastModified(lastModified);
        track.setNumber(number);
        track.setTitle(title);
        track.setPath(file.getAbsolutePath());

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database").build();

        Album albumFromDB = db.dao().findAlbum(albumName);

        Log.d(TAG, "saveIfMediaFile: " + albumFromDB);

        db.dao().insertAlbum(album);
        db.dao().insertTrack(track);
    }

    private boolean isMediaFile(String extension){
        String[] extensions = {"mp3", "flac", "3gp", "aac", "wav", "ogg"};
        boolean isMedia = false;
        for (int i = 0; i< extensions.length; i++) {
            if(extensions[i].equalsIgnoreCase(extension)){
                isMedia = true;
                continue;
            }
        }
        return isMedia;
    }


}
