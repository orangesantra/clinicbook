package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.abcd.R;

public class studentpage extends AppCompatActivity {


    public void studentregisterpage(View view){

        Intent intent = new Intent(getApplicationContext(),Studentregisterpage.class);
        startActivity(intent);

    }

    public void termsandconditionstu(View view){
       Intent intent = new Intent(getApplicationContext(),termsandconditionpage.class);
       startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpage);
    }
}