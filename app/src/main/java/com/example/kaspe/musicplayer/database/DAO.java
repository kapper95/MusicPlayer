package com.example.kaspe.musicplayer.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM folders")
    List<Folder> getFolders();

    @Insert
    void insertFolder(Folder folder);

    @Delete
    void deleteFolder(Folder folder);

    @Query("SELECT * FROM albums")
    List<Album> gerAlbums();

    @Insert
    void insertAlbum(Album album);

    @Delete
    void deleteAlbum(Album album);

    @Insert
    void insertTrack(Track track);

    @Delete
    void deleteTrack(Track track);
}
