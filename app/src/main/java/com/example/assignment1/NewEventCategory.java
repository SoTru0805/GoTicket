package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.assignment1.provider.CategoryViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class NewEventCategory extends AppCompatActivity {
    SharedPreferences sP;
    Switch IsActive;
    EditText etCategoryID;
    EditText etCategoryLocation;

    EditText etCategoryName;
    EditText etEventCount;
    ArrayList<Category> categoryArrayList;
    Gson gson = new Gson();
    String eventCategoriesString;
    private CategoryViewModel categoryViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_category);
        Toolbar toolbar = findViewById(R.id.shared_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Category");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etCategoryID = findViewById(R.id.CategoryID);
        etCategoryName = findViewById(R.id.CategoryName);
        etEventCount = findViewById(R.id.EventCount);
        IsActive = findViewById(R.id.IsActive);
        etCategoryLocation = findViewById(R.id.Location);
        sP = getSharedPreferences( KeyStore.FILE_NAME, MODE_PRIVATE);

        Type type = new TypeToken<ArrayList<Category>>() {}.getType();
        categoryArrayList = gson.fromJson(eventCategoriesString, type);


        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

    }
    public void restoreCategoryDetails() {
        SharedPreferences sharedPreferences = getSharedPreferences("categories", Context.MODE_PRIVATE);

        // Restore category details from SharedPreferences
        String categoryName = sharedPreferences.getString(KeyStore.EVENT_NAME, "Default Category");
        int eventCount = sharedPreferences.getInt(KeyStore.CATEGORY_EVENT_COUNT, 0);
        boolean isActive = sharedPreferences.getBoolean(KeyStore.CATEGORY_IS_ACTIVE, false);

        // Set restored values to EditText fields and Switch
        etCategoryName.setText(categoryName);
        etEventCount.setText(String.valueOf(eventCount));
        IsActive.setChecked(isActive);
    }

    public void onSaveClick(View view) {
        Log.d("SaveClick", "onSaveClick: ");
        saveCategory();
    }

    private void saveCategory() { //From sample code
        String categoryName = etCategoryName.getText().toString();
        String categoryLocation = etCategoryLocation.getText().toString();


        if (Utils.isNumeric(categoryName) || !Utils.isAlphaNumeric(categoryName)){
            Toast.makeText(this, "Invalid category name", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Integer.parseInt(etEventCount.getText().toString());
        } catch (Exception e){
            Toast.makeText(this, "Event count, valid integer value expected", Toast.LENGTH_SHORT).show();
            return;
        }
        String categoryID = autoGenerateID();
        String catName = etCategoryName.getText().toString();
        String eventcount = etEventCount.getText().toString();
        int eventCountInteger = Integer.parseInt(eventcount);
        boolean categorySwitch = IsActive.isChecked();
        String catLocation = etCategoryLocation.getText().toString();

        Category newEventC = new Category(categoryID, catName, eventCountInteger, categorySwitch, catLocation);
        categoryViewModel.insertCategory(newEventC);
        etCategoryID.setText(newEventC.getCategoryId());


        Toast.makeText(this, "Category saved successfully: " + newEventC.getCategoryId(), Toast.LENGTH_SHORT).show();
    }
    private String autoGenerateID(){
        char letter = (char) ('A' + new Random().nextInt(26));
        char letter1 = (char) ('A' + new Random().nextInt(26)) ;
        Random random = new Random();
        int randomNumber = random.nextInt(9999);
        String randomId = "C" + letter + letter1;

        return randomId + "-" + randomNumber;
    }

}


