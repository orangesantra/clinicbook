package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.abcd.R;

public class submitstudentpage extends AppCompatActivity {

    TextView returnback;
    
    public void goback5legally(View view){
        Intent intent = new Intent(getApplicationContext(),Studentregisterpage.class);
        startActivity(intent);
    }

    public void goback(View view){
        Intent intent = new Intent(getApplicationContext(),Studentregisterpage.class);
        startActivity(intent);
    }

    public  void exploremore(View view){

        Intent intent = new Intent(getApplicationContext(),exploremore.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitstudentpage);

        returnback = findViewById(R.id.returntohomepage);


    }
}





