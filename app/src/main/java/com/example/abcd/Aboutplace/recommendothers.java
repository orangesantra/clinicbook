package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.abcd.R;

public class recommendothers extends AppCompatActivity {


    public void webrecommendothers(View view){

        Intent intent = new Intent(getApplicationContext(),webrecommendothers.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        WebView webView = findViewById(R.id.facebookgrpwebpage);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        webView.loadUrl(intent.getStringExtra("content"));
    }
}

