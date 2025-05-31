package com.example.assignment1.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment1.Category;
import com.example.assignment1.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    // reference to CardRepository
    private DataRepository repository;
    // private class variable to temporary hold all the items retrieved and pass outside of this class
    private LiveData<List<Event>> allEventsLiveData;

    public EventViewModel(@NonNull Application application) {
        super(application);

        // get reference to the repository class
        repository = new DataRepository(application);

        // get all items by calling method defined in repository class
        allEventsLiveData = repository.getAllEvent();
    }


    public LiveData<List<Event>> getAllCards() {
        return allEventsLiveData;
    }


    public void insert(Event event) {
        repository.insertEvent(event);
    }

    public void deleteAllEvent() {
        repository.deleteAllEvent();
    }
}