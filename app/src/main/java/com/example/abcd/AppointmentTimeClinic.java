package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class AppointmentTimeClinic extends AppCompatActivity{
    LinearLayout linearLayoutmorning;
    LinearLayout linearLayoutafternoon;
    LinearLayout linearLayoutevening;

    TextView firstshift;
    TextView secondshift;
    TextView thirdshift;

    String x1 = null;
    String s1,s2,s3,s4;

    String uniqueuserid;

    String clinicname;

    String objectId;
    String service;

    Long mon,tue,wed,thur,fri,sat,sun;
    String fs;

    int limit=0;

    Long noofshiftsavailable;

    TextView textView;


    public void morningmethod(View view){
        linearLayoutmorning.isSelected();


            x1 = firstshift.getText().toString();

    }

    public void afternoonmethod(View view){
        linearLayoutafternoon.isSelected();


            x1 = secondshift.getText().toString();


    }

    public void eveningmethod(View view){
        linearLayoutevening.isSelected();


            x1 = thirdshift.getText().toString();


    }



    public  void nextx(View view){

        limit=limit+1;

        if(x1!=null&&limit<=noofshiftsavailable)
        {
        Intent intent = new Intent(getApplicationContext(),DependantDetails.class);
        intent.putExtra("time",x1);
        intent.putExtra("date",s4);
        intent.putExtra("name",s1);
        intent.putExtra("department",s2);
        intent.putExtra("location",s3);
        intent.putExtra("service",service);
        intent.putExtra("identity",uniqueuserid);
        intent.putExtra("Objectid",objectId);
        startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Please select an Appointment time",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_time_clinic);


        Intent i2=getIntent();
        s1=i2.getStringExtra("name");
        s2=i2.getStringExtra("department");
        s3=i2.getStringExtra("location");
        s4=i2.getStringExtra("date");
        service=i2.getStringExtra("service");


        uniqueuserid=i2.getStringExtra("identity");

        objectId=i2.getStringExtra("Objectid");

        fs=i2.getStringExtra("fs");

        mon=i2.getLongExtra("timediffmon",0)*60;
        tue=i2.getLongExtra("timedifftue",0)*60;
        wed=i2.getLongExtra("timediffwed",0)*60;
        thur=i2.getLongExtra("timediffthur",0)*60;
        fri=i2.getLongExtra("timedifffri",0)*60;
        sat=i2.getLongExtra("timediffsat",0)*60;
        sun=i2.getLongExtra("timediffsun",0)*60;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(service);
        if(objectId!=null){
            query.getInBackground(objectId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if(e==null&&object!=null){

                        String j1;
                        int i1;

                        j1= (String) object.get("timeperperson");
                        assert j1 != null;
                        i1=Integer.parseInt(j1);

                        noofshiftsavailable=wed/i1;
                        object.saveInBackground();


                    }


                }

            });}

        textView= findViewById(R.id.finaldatechoosen);

        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(mon));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(tue));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(wed));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(thur));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(fri));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(sat));
        Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",Long.toString(sun));


        textView.setText(s4);

        String p;
        p=textView.getText().toString().substring(0,3);

        linearLayoutmorning =findViewById(R.id.linearLayoutmorning);
        linearLayoutafternoon =findViewById(R.id.linearLayoutafternoon);
        linearLayoutevening =findViewById(R.id.linearLayoutevening);

        firstshift=findViewById(R.id.firstshiftvalue);
        secondshift=findViewById(R.id.secondshiftvalue);
        thirdshift=findViewById(R.id.thirdshiftvalue);

        firstshift.setText(fs);

    }
}