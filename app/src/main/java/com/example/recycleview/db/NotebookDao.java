package com.example.recycleview.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotebookDao {
    @Insert
    void insert(Notebook notebook);

    @Update
    void update(Notebook notebook);

    @Delete
    void delete(Notebook notebook);

    @Query("DELETE from Notebook")
    void deleteAll();

    @Query("SELECT * from Notebook")
    LiveData<List<Notebook>> getAllNotebooks();
}
