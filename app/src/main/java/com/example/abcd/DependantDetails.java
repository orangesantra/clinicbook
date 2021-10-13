package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DependantDetails extends AppCompatActivity {

    String s1,s2,s3,s4,x1;
    TextView datefinal;
    TextView timefinal;
    TextView locationfinal;
    TextView departmentfinal;
    TextView appointmentdetails;

    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    ArrayList<String> arrayList4;

    EditText e1;
    EditText e2;
    EditText e3;

    String nameofdependent,age,contactnumber;

    String useruniqueId;

    String objectid;

    String service;

    String k1="null";

    ParseObject entity = new ParseObject("Clinicsbooked");
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Clinicsbooked");


    public void confirmBooking(View view){

        nameofdependent=e1.getText().toString();
        age=e2.getText().toString();
        contactnumber=e3.getText().toString();


        if(nameofdependent.equals("") || age.equals("")  || contactnumber.equals("") || k1.equals("")) {

            Toast.makeText(getApplicationContext(),"Please fill details above!", Toast.LENGTH_LONG).show();

        }
        else {
            for(int i=0;i<arrayList2.size();i++){

                    if (useruniqueId.equals(arrayList1.get(i)) && s1.equals(arrayList2.get(i)) && s4.equals(arrayList3.get(i))) {

                        Toast.makeText(getApplicationContext(), "You can't do two bookings for same day!", Toast.LENGTH_LONG).show();

                    } else {

                        if(contactnumber.equals(arrayList4.get(i))){

                            Toast.makeText(getApplicationContext(), "Enter the correct contact number!", Toast.LENGTH_LONG).show();

                        }
                        else {

                            entity.put("DependentName", nameofdependent);
                            entity.put("DependentAge", age);
                            entity.put("DependentContactNo", contactnumber);
                            entity.put("UniqueUserId", useruniqueId);

                            entity.put("Name", s1);
                            entity.put("DepartmentBooked", s3);
                            entity.put("Location", s2);
                            entity.put("Service",service);
                            entity.put("Date", s4);
                            entity.put("Time", x1);
                            entity.put("DependentGender", k1);

                            entity.saveInBackground();


                            Intent intent = new Intent(getApplicationContext(), Confirmationscreen.class);
                            intent.putExtra("DependentName",nameofdependent);
                            intent.putExtra("DependentAge",age);
                            intent.putExtra("DependentContactNo", contactnumber);
                            intent.putExtra("UniqueUserId", useruniqueId);
                            intent.putExtra("Objectid",objectid);

                            intent.putExtra("Name", s1);
                            intent.putExtra("DepartmentBooked", s3);
                            intent.putExtra("Location", s2);
                            intent.putExtra("Date", s4);
                            intent.putExtra("Time", x1);
                            intent.putExtra("DependentGender", k1);
                            startActivity(intent);
                        }
                    }
            }
        }
    }

    public void onRadioButtonClicked(View view) {


        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.DependentMale:
                if (checked) {
                    k1="Male";
                }

                break;
            case R.id.DependentFemale:
                if (checked){
                    k1="Female";
                }

                break;
            case R.id.Dependantothers:
                if (checked){
                    k1="Others";
                }

                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependant_details);


        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            arrayList1.add(object.getString("UniqueUserId"));
                            arrayList2.add(object.getString("Name"));
                            arrayList3.add(object.getString("Date"));
                            arrayList4.add(object.getString("DependentContactNo"));

                            object.saveInBackground();
                        }
                    }
                }

            }
        });


        timefinal= findViewById(R.id.timefinal);
        departmentfinal= findViewById(R.id.departmentfinal);
        datefinal= findViewById(R.id.datefinal);
        locationfinal=findViewById(R.id.locationfinal);
        appointmentdetails=findViewById(R.id.Appointmentdetails);

        appointmentdetails.setPaintFlags(appointmentdetails.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        e1=findViewById(R.id.nameofdependent);
        e2=findViewById(R.id.age);
        e3=findViewById(R.id.contactnumber);

        Intent i2=getIntent();
        s1=i2.getStringExtra("name");
        s2=i2.getStringExtra("department");
        s3=i2.getStringExtra("location");
        s4=i2.getStringExtra("date");
        x1=i2.getStringExtra("time");
        service=i2.getStringExtra("service");
        useruniqueId=i2.getStringExtra("identity");
        objectid=i2.getStringExtra("Objectid");


        timefinal.setText(x1);
        departmentfinal.setText(s3);
        datefinal.setText(s4);
        locationfinal.setText(s2);

    }
}