package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Album.class, Folder.class, Track.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}
