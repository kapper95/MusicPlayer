package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Track {

    @PrimaryKey
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

}
