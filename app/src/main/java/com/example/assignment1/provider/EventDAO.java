package com.example.assignment1.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1.Event;

import java.util.List;

@Dao
public interface EventDAO {
    // // Specifies a database query to retrieve all items from the "items" table. (referenced A.3.4)
    @Query("select * from Event")
    LiveData<List<Event>> getAllItems(); // Returns a LiveData object containing a list of Item objects.

    // Indicates that this method is used to insert data into the database.
    @Insert
    void addItem(Event item); // Method signature for inserting an Item object into the database.

    @Query("DELETE FROM Event")
    void deleteAllEvent();

    @Update
    void updateItem(Event item);

}
