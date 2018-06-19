package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "albums")
public class Album {

    @PrimaryKey
    @ColumnInfo(name = "albumid")
    private int aid;

    @ColumnInfo(name = "album")
    private String album;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "artist")
    private String artist;

    public Album() {}

    public Album(int aid, String album, String date, String artist) {
        this.aid = aid;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
