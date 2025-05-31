package com.example.assignment1;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.assignment1.Utils;

@Entity (tableName = "Category2")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private  int id;

    @ColumnInfo(name = "categoryID")
    private String categoryId;

    @ColumnInfo(name = "category_name")
    private String name;
    @ColumnInfo(name = "event_count")
    private int eventCount;
    @ColumnInfo(name = "category_status")
    private boolean isActive;

    @ColumnInfo(name = "category_location")
    private String categoryLocation;

    public Category(String categoryId, String name, int eventCount, boolean isActive, String categoryLocation) {
        this.categoryId = categoryId;
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
        this.categoryLocation = categoryLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getCategoryLocation() {
        return categoryLocation;
    }

    public void setCategoryLocation(String categoryLocation) {
        this.categoryLocation = categoryLocation;
    }
}

