package com.example.timemanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;


@Dao
public interface EventDao {
    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

@Query("DELETE FROM `event table`")
    void deleteAllEvents();

@Query("SELECT * FROM `event table` ORDER BY priority DESC")
    LiveData<List<Event>> getAllEvents();

}
