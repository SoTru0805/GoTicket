package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.assignment1.provider.CategoryViewModel;
import com.example.assignment1.provider.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class NewEvent extends AppCompatActivity {
    SharedPreferences sP;
    EditText etEventName;
    TextView gestureType;
    EditText etCategoryID;
    EditText etTicketsAvailable;
    EditText editTextEventId;
    Switch isActiveSwitch;
    DrawerLayout drawerLayout1;
    NavigationView navigationView;
    Gson gson = new Gson();
    private EventViewModel eventViewModel;
    private CategoryViewModel categoryViewModel;
    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout1 = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout1, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout1.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        MyNavigationListener myNavigationListener = new MyNavigationListener();
        navigationView.setNavigationItemSelectedListener(myNavigationListener);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecordSave();
            }
        });
        loadData();


        // Initialize UI elements
        etEventName = findViewById(R.id.EventName);
        etCategoryID = findViewById(R.id.EventCategoryID);
        etTicketsAvailable = findViewById(R.id.Tickets);
        isActiveSwitch = findViewById(R.id.IsActiveEventForm);
        editTextEventId = findViewById(R.id.EventID);
        sP = getSharedPreferences(KeyStore.FILE_NAME, MODE_PRIVATE);


        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);


        View touchpad = findViewById(R.id.view2);

        gestureType = findViewById(R.id.gestureType);

        CustomGestureDetection customGestureDetector = new CustomGestureDetection();

        mDetector = new GestureDetectorCompat(this, customGestureDetector);
        mDetector.setOnDoubleTapListener(customGestureDetector);

        touchpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    class CustomGestureDetection extends GestureDetector.SimpleOnGestureListener{
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            Log.d("longPress", "onLongPress: ");
            clearEventForm();
            gestureType.setText("onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            Log.d("doubleTap", "onDoubleTap: ");
            saveRecordSave();
            gestureType.setText("onDoubleTap");
            return true;
        }

    }

    private void saveRecordSave() {
        String eventCatIdString = etCategoryID.getText().toString();


        categoryViewModel.setCategoryCountUpdate(eventCatIdString,1);


        //categoryArrayList = newEventCategories;
        String eventIdString = autoGeneratedId();
        String eventNameString = etEventName.getText().toString();
        String eventTicketString = etTicketsAvailable.getText().toString();
        int eventTicketInteger = Integer.parseInt(eventTicketString);
        boolean currentSwitchState = isActiveSwitch.isChecked();
        editTextEventId.setText(eventCatIdString);

        Event newEventCategory = new Event(eventIdString,eventNameString,eventCatIdString,eventTicketInteger,currentSwitchState);
        EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        eventViewModel.insert(newEventCategory);



        Toast.makeText(this, "Event saved: " + eventIdString + " to " + eventCatIdString, Toast.LENGTH_LONG).show();

        loadData();

    }


    private String autoGeneratedId() {
        char letter = (char) ('A' + new Random().nextInt(26));
        char letter1 = (char) ('A' + new Random().nextInt(26));
        Random random = new Random();
        int randomNumber = random.nextInt(99999);
        String randomId = "E" + letter + letter1;

        return randomId + "-" + randomNumber;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.OptMenuClearEvent) {
            clearEventForm();
        } else if (item.getItemId() == R.id.OptMenuDeleteCat) {
            deleteAllCategories();
        } else if (item.getItemId() == R.id.OptMenuDeleteAllEvent) {
            deleteAllEvents();
        }
        return true;
    }

    private void clearEventForm() {
        updateFields("", "", "", false);
        editTextEventId.setText("");
    }

    private void updateFields(String _eventName, String _categoryId, String _ticketsAvailable, boolean _isActive) {
        etEventName.setText(_eventName);
        etCategoryID.setText(_categoryId);
        etTicketsAvailable.setText(_ticketsAvailable);
        isActiveSwitch.setChecked(_isActive);
    }

    private void deleteAllCategories() {
        categoryViewModel.deleteAllCategory();
        loadData();
    }


    private void deleteAllEvents() {

        eventViewModel.deleteAllEvent();
        loadData();
    }

    private void loadData(){ //Loading data (From sample code)
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentCategoryList.newInstance("", ""))
                .commit();
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) { //From sample code
            int itemId = item.getItemId();

            // Handle navigation item clicks here
            if (itemId == R.id.DrawerViewCat) {
                Intent intent = new Intent(getApplicationContext(), ListCategoryActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.DrawerAddCat) {
                onCategoryBtnClick(null);
            } else if (itemId == R.id.DrawerViewEvents) {
                Intent intent = new Intent(getApplicationContext(), ListEventActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.DrawerLogout) {
                Intent intent = new Intent(getApplicationContext(), LoginActitvity.class);
                startActivity(intent);
                finish();
            }

            // Close the navigation drawer after handling selection
            drawerLayout1.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    public void onCategoryBtnClick(View view){
        Intent intent=new Intent(this, NewEventCategory.class);
        startActivity(intent);
    }





}
