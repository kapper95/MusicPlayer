package com.example.kaspe.musicplayer;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MediaPlayerHolder {

    private MediaPlayer mediaPlayer;

    private void initializeMediaPlayer(){
        mediaPlayer = new MediaPlayer();
    }

    public void play(){
        if(mediaPlayer != null && !mediaPlayer.isPlaying()){

            mediaPlayer.start();
        }
    }

    public void pause(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void loadMedia(String path){
        initializeMediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
