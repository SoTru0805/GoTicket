package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Event")
public class Event {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "event_id")
    private String eventId;
    @ColumnInfo(name = "event_name")
    private String eventName;
    @ColumnInfo(name = "category_id")
    private String categoryID;
    @ColumnInfo(name = "tickets_available")
    private int ticketsAvailable;
    @ColumnInfo(name = "event_status")
    private boolean isActive;




    public Event(String eventId, String eventName, String categoryID, int ticketsAvailable, boolean isActive) {
        this.eventName = eventName;
        this.eventId = eventId;
        this.categoryID = categoryID;
        this.ticketsAvailable = ticketsAvailable;
        this.isActive = isActive;
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return eventName;
    }

    public String getCategoryId() {
        return categoryID;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
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

    public void setId(int id) {
        this.id = id;
    }

}
