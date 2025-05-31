package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class ListEventActivity extends AppCompatActivity {
// Adapted from Chief Examiner’s A2 code provided in the A3 Starter Project
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event2);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, FragmentListEvent.newInstance("", ""))
                .commit();

        Toolbar toolbar = findViewById(R.id.shared_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events page");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
