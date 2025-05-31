package com.example.assignment1.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignment1.Category;
import com.example.assignment1.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class, Category.class}, version = 1)
public abstract class ClassDatabase extends RoomDatabase {
    public abstract EventDAO eventDAO();
    public abstract CategoryDAO categoryDAO();
    public static final String DATABASE = "database";

    private static volatile ClassDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ClassDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClassDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ClassDatabase.class, DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
