package com.example.assignment1.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment1.Category;
import com.example.assignment1.Event;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private DataRepository repository;
    private LiveData<List<Category>> allCategoryLiveData;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        repository = new DataRepository(application);

        allCategoryLiveData = repository.getAllCategory();
    }
    public LiveData<List<Category>> getAllCategory() {
        return allCategoryLiveData;
    }

    public LiveData<Category> getCategoryById(String categoryId) {
        return repository.getCategoryById(categoryId);
    }

    public void insertCategory(Category category) {
        repository.insertCategory(category);
    }

    public void setCategoryCountUpdate(String categoryID, int countUpdate){
        repository.updateCategoryCount(categoryID, countUpdate );
    }
    public void deleteAllCategory() {
        repository.deleteAllCategory();
    }


}