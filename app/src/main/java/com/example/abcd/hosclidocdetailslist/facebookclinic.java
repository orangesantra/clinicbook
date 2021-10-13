package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.abcd.R;

public class facebookclinic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebookclinic);

        WebView webView = findViewById(R.id.webpagecli);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
//        webView.loadData(intent.getStringExtra("content"), "text/html", "UTF-8");
        webView.loadUrl(intent.getStringExtra("facebookpage"));
    }
}