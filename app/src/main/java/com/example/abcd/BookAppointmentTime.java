package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class BookAppointmentTime extends AppCompatActivity {

    Spinner spinner;
    TextView t1,t2,t3;
    TextView t4,t5,t6;
    TextView t7,t8,t9;
    TextView t10,t11,t12;
    TextView t13,t14,t15;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment_time);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);
        t6=findViewById(R.id.t6);
        t7=findViewById(R.id.t7);
        t8=findViewById(R.id.t8);
        t9=findViewById(R.id.t9);
        t10=findViewById(R.id.t10);
        t11=findViewById(R.id.t11);
        t12=findViewById(R.id.t12);
        t13=findViewById(R.id.t13);
        t14=findViewById(R.id.t14);
        t15=findViewById(R.id.t15);

        final View[] previousView = new View[1];

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView previousText = (TextView) previousView[0];
                TextView curText = (TextView) v;
                // If the clicked view is selected, deselect it
                if (curText.isSelected()) {
                    curText.setSelected(false);
                    curText.setTextColor(Color.parseColor("#1B1919"));

                } else { // If this isn't selected, deselect  the previous one (if any)
                    if (previousText != null && previousText.isSelected()) {
                        previousText.setSelected(false);
                        previousText.setTextColor(Color.parseColor("#1B1919"));

                    }
                    curText.setSelected(true);
                    curText.setTextColor(Color.parseColor("#1081B8"));

                    previousView[0] = v;
                }

            }
        };

        t1.setOnClickListener(clickListener);
        t2.setOnClickListener(clickListener);
        t3.setOnClickListener(clickListener);
        t4.setOnClickListener(clickListener);
        t5.setOnClickListener(clickListener);
        t6.setOnClickListener(clickListener);
        t7.setOnClickListener(clickListener);
        t8.setOnClickListener(clickListener);
        t9.setOnClickListener(clickListener);
        t10.setOnClickListener(clickListener);
        t11.setOnClickListener(clickListener);
        t12.setOnClickListener(clickListener);
        t13.setOnClickListener(clickListener);
        t14.setOnClickListener(clickListener);
        t15.setOnClickListener(clickListener);











        spinner=findViewById(R.id.timespinner);

        String[] arraySpinner = new String[] {
                "Morning","After-Noon","Evening"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////////////////////////////////////////////////////////////////////////



    }
}