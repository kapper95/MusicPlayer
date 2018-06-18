package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "folders")
public class Folder {

    @PrimaryKey
    @ColumnInfo(name = "folderid")
    private int id;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "last_modified")
    private long lastModified;

    public Folder(int id, String path, long lastModified) {
        this.id = id;
        this.path = path;
        this.lastModified = lastModified;
    }

    public Folder() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
