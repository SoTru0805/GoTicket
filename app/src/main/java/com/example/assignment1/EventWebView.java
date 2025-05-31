package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EventWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_web_view);

        WebView webView = findViewById(R.id.WebView);

        // get country name from Intent
        String eventWeb = getIntent().getExtras().getString("selectedEventWeb");


        // compile the Wikipedia URL, which will be used to load into WebView
        String googlePageURL = "https://www.google.com/search?q=" + eventWeb;

        // set new WebView Client for the WebView
        // This gives the WebView ability to be load the URL in the current WebView
        // instead of navigating to default web browser of the device
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(googlePageURL);
    }
}