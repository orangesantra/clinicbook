package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.abcd.R;

public class clinicwebpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicwebpage);

        WebView webView = findViewById(R.id.clinicwebpage_private);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent i2 = getIntent();
        webView.loadUrl(i2.getStringExtra("clinicweburl"));
    }
}