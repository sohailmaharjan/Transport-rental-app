package com.sohail.wheeler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl("https://nepalvehiclerental.com/");
        browser.getSettings().setJavaScriptEnabled(true);
    }
}