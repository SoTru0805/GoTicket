package com.example.assignment1.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.assignment1.Category;
import com.example.assignment1.Event;

import java.util.List;

public class DataRepository {

    private EventDAO eventDAO;
    private CategoryDAO catDAO;
    private LiveData<List<Event>> allEventLiveData;
    private LiveData<List<Category>> allCategoryLiveData;

    DataRepository(Application application) {
        ClassDatabase db = ClassDatabase.getDatabase(application);

        eventDAO = db.eventDAO();
        catDAO = db.categoryDAO();

        allEventLiveData = eventDAO.getAllItems();
        allCategoryLiveData = catDAO.getAllItems();
    }

    /**
     * Repository method to get all cards
     * @return LiveData of type List<Item>
     */
    LiveData<List<Event>> getAllEvent() {
        return allEventLiveData;
    }

    LiveData<List<Category>> getAllCategory(){
        return allCategoryLiveData;
    }


    void insertEvent(Event event) {
        ClassDatabase.databaseWriteExecutor.execute(() -> eventDAO.addItem(event));
    }

    void insertCategory(Category category){
        ClassDatabase.databaseWriteExecutor.execute(() -> catDAO.addItem(category));
    }

    LiveData<Category> getCategoryById(String categoryId) {
        return catDAO.getCategoryById(categoryId);
    }

    void deleteAllCategory() {
        ClassDatabase.databaseWriteExecutor.execute(() -> catDAO.deleteAllCategory());
    }

    void deleteAllEvent(){
        ClassDatabase.databaseWriteExecutor.execute(() -> eventDAO.deleteAllEvent());
    }

    void updateCategoryCount(String categoryID, int countUpdate){
        ClassDatabase.databaseWriteExecutor.execute(() -> catDAO.updateCategoryCount(categoryID, countUpdate));
    }

}

