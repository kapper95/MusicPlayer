package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tracks")
public class Track {

    @PrimaryKey
    @ColumnInfo(name = "trackid")
    private int tid;

    @ColumnInfo(name = "album")
    private String album;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "disc")
    private int disc;

    @ColumnInfo(name = "duration")
    private int duration;

    @ColumnInfo(name = "last_modified")
    private long lastModified;

    @ColumnInfo(name = "path")
    private String path;

    public Track() {}

    public Track(int tid, String album, String title, int number, int disc, int duration, long lastModified, String path) {
        this.tid = tid;
        this.album = album;
        this.title = title;
        this.number = number;
        this.disc = disc;
        this.duration = duration;
        this.lastModified = lastModified;
        this.path = path;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDisc() {
        return disc;
    }

    public void setDisc(int disc) {
        this.disc = disc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
