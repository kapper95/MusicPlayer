package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Album {

    @PrimaryKey
    private int aid;

    @ColumnInfo(name = "album")
    private String album;

    @ColumnInfo(name = "date")
    private long date;

    @ColumnInfo(name = "artist")
    private String artist;

    public Album() {}

    public Album(String album, long date, String artist) {
        this.album = album;
        this.date = date;
        this.artist = artist;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
