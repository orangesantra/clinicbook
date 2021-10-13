package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.abcd.R;

public class termsandconditionpage extends AppCompatActivity {

    public void returntac(View view){

        Intent intent = new Intent(getApplicationContext(),Studentregisterpage.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandconditionpage);
    }
}