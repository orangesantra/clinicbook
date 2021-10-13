package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.abcd.Investorsweb;
import com.example.abcd.R;
import com.example.abcd.paymentprocedure.Payment;

public class ourinvester extends AppCompatActivity {



    public void topinvestors(View view){
        Intent intent = new Intent(getApplicationContext(), investorlist.class);
        startActivity(intent);

    }

    public void newinvestors(View view){
        Intent intent = new Intent(getApplicationContext(), investorlist.class);
        startActivity(intent);

    }



    public void contribute(View view){
        Intent intent = new Intent(getApplicationContext(), Payment.class);
        startActivity(intent);


    }

    public void investors(View view){

        int s1 = 0;

        Intent intent = new Intent(getApplicationContext(), Investorsweb.class);

        if(s1==R.id.in1) {
            intent.putExtra("url", "http://klacklmkcmamflka");
        }

        if(s1==R.id.in2) {
            intent.putExtra("url", "http://klakcmsasfisafja");
        }

        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourinvester);
    }
}