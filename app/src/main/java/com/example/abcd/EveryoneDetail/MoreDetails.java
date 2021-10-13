package com.example.abcd.EveryoneDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.abcd.R;
import com.example.abcd.paymentprocedure.Payment;

public class MoreDetails extends AppCompatActivity {

    public void pay(View view){
        Intent intent= new Intent(getApplicationContext(), Payment.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
    }
}