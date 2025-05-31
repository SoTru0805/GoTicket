package com.example.assignment1.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    // // Specifies a database query to retrieve all items from the "items" table. (referenced A.3.4)
    @Query("select * from Category2")
    LiveData<List<Category>> getAllItems(); // Returns a LiveData object containing a list of Item objects.

    // Indicates that this method is used to insert data into the database.
    @Insert
    void addItem(Category item); // Method signature for inserting an Item object into the database.

    @Query("DELETE FROM Category2")
    void deleteAllCategory();

    @Query("SELECT * FROM Category2 WHERE categoryId = :categoryId")
    LiveData<Category> getCategoryById(String categoryId);

    @Query("update category2 set event_count = event_count+:countUpdate where categoryID = :categoryID")
    void updateCategoryCount (String categoryID, int countUpdate);


    @Update
    void update (Category category);
}
