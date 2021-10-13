package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Timingperperson extends AppCompatActivity {

    String minutes;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicData");

    public  void savetime(View view){


        query.whereEqualTo("name","Clinic1");
        query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null){
                        if(objects.size()>0){
                            for(ParseObject object: objects){
                                object.put("timeperperson",minutes);

                                object.saveInBackground();
                            }
                        }
                    }
                }
            });


    }

    public  void canceltiming(View view){


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timingperperson);


        String[] arraySpinner1 = new String[] {
                "00","10","15","20","25","30","35","40","45","50","55","60"

        };


        Spinner s1 = findViewById(R.id.tspinner);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minutes = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}