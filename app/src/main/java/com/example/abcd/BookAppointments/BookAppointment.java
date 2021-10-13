package com.example.abcd.BookAppointments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.abcd.AppointmentTimeClinic;
import com.example.abcd.BookAppointmentTime;
import com.example.abcd.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BookAppointment extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView DummyTextView;
    TextView name;
    TextView locationname;
    TextView departmentname;
    String date;
    String uniqueuserid;

    String clinicname;
    String doctorname;

    String objectid;
    int k;

    String service;
    String department;
    String location;

    Intent intent;

    JSONArray mon,tue,wed,thur,fri,sat,sun;

    ArrayList<String> arrayList;

    ArrayList<String> daylist;
    ArrayList<String> datelist;



    HashMap<String, String> a = new HashMap<>();
    HashMap<String, String> b = new HashMap<>();

    DateTimeFormatter timeFormatter;

    String p1,p2,p3,p4,p5,p6,p7;


    public  void  dateselected(View view){

        k=view.getId();
        DummyTextView=findViewById(k);
        date=DummyTextView.getText().toString();

        if(service.equals("ClinicData")){
            intent.putExtra("name",clinicname);
        }

        if(service.equals("Doctordata")){
            intent.putExtra("name",doctorname);
        }

        intent.putExtra("service",service);
        intent.putExtra("department",department);
        intent.putExtra("location",location);
        intent.putExtra("date",date);



        date= date.substring(0,date.indexOf("-")-4);

        if(date.equals("Monday")){

            intent.putExtra("fs",p1);

        }

        if(date.equals("Tuesday")){

            intent.putExtra("fs",p2);

        }


        if(date.equals("Wednesday")){

            intent.putExtra("fs",p3);

        }


        if(date.equals("Thursday")){

            intent.putExtra("fs",p4);

        }


        if(date.equals("Friday")){

            intent.putExtra("fs",p5);

        }


        if(date.equals("Saturday")){

            intent.putExtra("fs",p6);

        }


        if(date.equals("Sunday")){

            intent.putExtra("fs",p7);

        }


        intent.putExtra("mondayfs",p1);
        intent.putExtra("tuesdayfs",p2);
        intent.putExtra("wednesdayfs",p3);
        intent.putExtra("thursdayfs",p4);
        intent.putExtra("fridayfs",p5);
        intent.putExtra("saturdayfs",p6);
        intent.putExtra("sundayfs",p7);
        intent.putExtra("identity",uniqueuserid);
        intent.putExtra("Objectid",objectid);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);



        Intent i2 = getIntent();
        clinicname = i2.getStringExtra("clinicname");
        doctorname =i2.getStringExtra("Doctorname");
        objectid = i2.getStringExtra("Objectid");
        service=i2.getStringExtra("service");
        department=i2.getStringExtra("department");
        location=i2.getStringExtra("location");

        uniqueuserid=i2.getStringExtra("identity");

        intent = new Intent(getApplicationContext(), AppointmentTimeClinic.class);
        intent.putExtra("clinicname",clinicname);

        mon=new JSONArray();
        tue=new JSONArray();
        wed=new JSONArray();
        thur=new JSONArray();
        fri=new JSONArray();
        sat=new JSONArray();
        sun=new JSONArray();

        timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

        String startTime = "08:00 AM";
        String endTime = "04:00 PM";

        LocalTime start = LocalTime.parse(startTime, timeFormatter);
        LocalTime end = LocalTime.parse(endTime, timeFormatter);

        Duration diff = Duration.between(start, end);

        long hours = diff.toHours();
        long minutes = diff.minusHours(hours).toMinutes();
        String totalTimeString = String.format("%02d:%02d", hours, minutes);

        textView1=findViewById(R.id.firstdate);
        textView2=findViewById(R.id.seconddate);
        textView3=findViewById(R.id.thirddate);
        textView4=findViewById(R.id.forthdate);
        textView5=findViewById(R.id.fifthdate);
        textView6=findViewById(R.id.sixthdate);
        name=findViewById(R.id.cliname);
        locationname=findViewById(R.id.locationname);
        departmentname=findViewById(R.id.departname);

        if(service.equals("ClinicData")){
            name.setText(clinicname);

        }else{
            name.setText(doctorname);
        }

        locationname.setText(location);
        departmentname.setText(department);

        arrayList= new ArrayList<>();
        daylist=new ArrayList<>();
        datelist=new ArrayList<>();

//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
        SimpleDateFormat x = new SimpleDateFormat("EEEE");
        SimpleDateFormat y = new SimpleDateFormat("dd-MMM-yyyy");
        for (int i = 0; i < 7; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i+1);
            String day = x.format(calendar.getTime());
            String date=y.format(calendar.getTime());
            daylist.add(day);
            datelist.add(date);


        }
        Log.i("99999999999", daylist.toString());
        Log.i("666666666666", datelist.toString());

         a = new LinkedHashMap<>();
        for(int i = 0; i < datelist.size(); i++) {
            a.put(daylist.get(i), datelist.get(i));
        }



        ParseQuery<ParseObject> query = ParseQuery.getQuery(service);
        if(objectid!=null){
            query.getInBackground(objectid, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if(e==null&&object!=null){

                        if(object.getJSONArray("monday")!=null) {

                            String y1;
                            String t1,t2;


                            mon = Objects.requireNonNull(object.getJSONArray("monday"));

                            y1= mon.toString();
                            y1= y1.substring(2,y1.indexOf("]") - 1);

                            if(!y1.equals("NoAppointment")) {

                                try {
                                    t1=mon.get(0).toString();
                                    t2=mon.get(1).toString();

                                    p1=t1+"---"+t2;

                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();

                                    long interval = hours+minutes;
                                    String xy =Long.toString(interval);
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;

                                        intent.putExtra("timediffmon",99);
                                    }



                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Monday");

                            }

                        }


                        if(object.getJSONArray("tuesday")!=null) {

                            String y2;
                            String t1,t2;


                            tue = Objects.requireNonNull(object.getJSONArray("tuesday"));
                            y2=tue.toString();

                            y2= y2.substring(2,y2.indexOf("]") - 1);

                            if(!y2.equals("NoAppointment")) {

                                try {
                                    t1= tue.get(0).toString();
                                    t2= tue.get(1).toString();

                                    p2=t1+"---"+t2;


                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }



                                    intent.putExtra("timedifftue",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Tuesday");
                            }



                        }


                        if(object.getJSONArray("wednesday")!=null) {

                            String y3;
                            String t1,t2;


                            wed = Objects.requireNonNull(object.getJSONArray("wednesday"));
                            y3=wed.toString();

                            y3= y3.substring(2,y3.indexOf("]") - 1);

                            if(!y3.equals("NoAppointment")) {

                                try {
                                    t1= wed.get(0).toString();
                                    t2= wed.get(1).toString();

                                    p3=t1+"---"+t2;


                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }


                                    intent.putExtra("timediffwed",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Wednesday");
                            }



                        }


                        if(object.getJSONArray("thursday")!=null) {

                            String y4;
                            String t1,t2;


                            thur= Objects.requireNonNull(object.getJSONArray("thursday"));
                            y4=thur.toString();

                            y4= y4.substring(2,y4.indexOf("]") - 1);

                            if(!y4.equals("NoAppointment")) {

                                try {
                                    t1= thur.get(0).toString();
                                    t2= thur.get(1).toString();

                                    p4=t1+"---"+t2;


                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }


                                    intent.putExtra("timediffthur",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Thursday");
                            }


                        }

                        if(object.getJSONArray("friday")!=null) {

                            String y5;
                            String t1,t2;


                            fri = Objects.requireNonNull(object.getJSONArray("friday"));
                            y5=fri.toString();

                            y5= y5.substring(2,y5.indexOf("]") - 1);

                            if(!y5.equals("NoAppointment")) {

                                try {
                                    t1= fri.get(0).toString();
                                    t2= fri.get(1).toString();

                                    p5=t1+"---"+t2;

                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }


                                    intent.putExtra("timedifffri",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Friday");
                            }

                        }


                        if(object.getJSONArray("saturday")!=null) {

                            String y6;
                            String t1,t2;


                            sat = Objects.requireNonNull(object.getJSONArray("saturday"));
                            y6=sat.toString();

                            y6= y6.substring(2,y6.indexOf("]") - 1);

                            if(!y6.equals("NoAppointment")) {

                                try {
                                    t1= sat.get(0).toString();
                                    t2= sat.get(1).toString();

                                    p6=t1+"---"+t2;


                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }


                                    intent.putExtra("timediffsat",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Saturday");
                            }

                        }


                        if(object.getJSONArray("sunday")!=null) {

                            String y7;
                            String t1,t2;

                            sun = Objects.requireNonNull(object.getJSONArray("sunday"));
                            y7=sun.toString();

                            y7= y7.substring(2,y7.indexOf("]") - 1);

                            if(!y7.equals("NoAppointment")) {

                                try {
                                    t1= sun.get(0).toString();
                                    t2= sun.get(1).toString();

                                    p7=t1+"---"+t2;


                                    LocalTime start = LocalTime.parse(t1, timeFormatter);
                                    LocalTime end = LocalTime.parse(t2, timeFormatter);

                                    Duration diff = Duration.between(start, end);

                                    long hours = diff.toHours();
                                    long minutes = diff.minusHours(hours).toMinutes();
                                    String totalTimeString = String.format("%02d:%02d", hours, minutes);

                                    if(hours<0){
                                        hours=hours*-1;
                                    }


                                    intent.putExtra("timediffsun",hours);
                                    Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }

                                arrayList.add("Sunday");
                            }


                        }



                        ArrayList<String> common=new ArrayList<>(daylist);
                        common.retainAll(arrayList);

                        ArrayList<String> uncommon = new ArrayList<>(daylist);
                        uncommon.removeAll(common);

                        ArrayList<String> newUncommon = new ArrayList<>(uncommon);

                        a.keySet().removeAll(newUncommon);

                        ArrayList<String> keys = Collections.list(Collections.enumeration(a.keySet()));
                        ArrayList<String> valueList = Collections.list(Collections.enumeration(a.values()));


                        ArrayList<String> summation =new ArrayList<>();
                        int i1=summation.size();
                        summation.clear();



                        String sum;

                        for(int i=0;i<keys.size();i++){
                            sum=keys.get(i)+", "+valueList.get(i);
                            summation.add(sum);
                        }

                        String z1,z2,z3,z4,z5,z6,z7;

                        z1 = summation.get(0);
                        textView1.setText(z1);

                        z2 = summation.get(1);
                        textView2.setText(z2);

                        z3=summation.get(2);
                        textView3.setText(z3);

                        z4 = summation.get(3);
                        textView4.setText(z4);


                        if(4<summation.size()){

                            z5=summation.get(4);
                            textView5.setText(z5);
                        }

                        else {
                            Log.i("kkkkk","jjjjjjjj");
                        }

                        if(5<summation.size()){

                            z6 = summation.get(5);
                            textView6.setText(z6);

                        }

                        else {
                            Log.i("kkkkk","jjjjjjjj");
                        }


                    }


                }

            });}




//        ParseQuery<ParseObject> query =ParseQuery.getQuery(service);
//        query.whereEqualTo("name",clinicname);
//
//        query.setLimit(5);
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e==null){
//                    if(objects.size()>0){
//
//                        for(ParseObject object: objects){
//
//
//                            if(object.getJSONArray("monday")!=null) {
//
//                                String y1;
//                                String t1,t2;
//
//
//                                mon = Objects.requireNonNull(object.getJSONArray("monday"));
//
//                               y1= mon.toString();
//                               y1= y1.substring(2,y1.indexOf("]") - 1);
//
//                                if(!y1.equals("NoAppointment")) {
//
//                                    try {
//                                        t1=mon.get(0).toString();
//                                        t2=mon.get(1).toString();
//
//                                        p1=t1+"---"+t2;
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//
//                                        long interval = hours+minutes;
//                                        String xy =Long.toString(interval);
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//
//                                            intent.putExtra("timediffmon",99);
//                                        }
//
//
//
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Monday");
//
//                                }
//
//                            }
//
//
//                            if(object.getJSONArray("tuesday")!=null) {
//
//                                String y2;
//                                String t1,t2;
//
//
//                                tue = Objects.requireNonNull(object.getJSONArray("tuesday"));
//                                y2=tue.toString();
//
//                                y2= y2.substring(2,y2.indexOf("]") - 1);
//
//                                if(!y2.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= tue.get(0).toString();
//                                        t2= tue.get(1).toString();
//
//                                        p2=t1+"---"+t2;
//
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//
//                                        intent.putExtra("timedifftue",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Tuesday");
//                                }
//
//
//
//                            }
//
//
//                            if(object.getJSONArray("wednesday")!=null) {
//
//                                String y3;
//                                String t1,t2;
//
//
//                                wed = Objects.requireNonNull(object.getJSONArray("wednesday"));
//                                y3=wed.toString();
//
//                                y3= y3.substring(2,y3.indexOf("]") - 1);
//
//                                if(!y3.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= wed.get(0).toString();
//                                        t2= wed.get(1).toString();
//
//                                        p3=t1+"---"+t2;
//
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//                                        intent.putExtra("timediffwed",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",Long.toString(hours));
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Wednesday");
//                                }
//
//
//
//                            }
//
//
//                            if(object.getJSONArray("thursday")!=null) {
//
//                                String y4;
//                                String t1,t2;
//
//
//                                thur= Objects.requireNonNull(object.getJSONArray("thursday"));
//                                y4=thur.toString();
//
//                                y4= y4.substring(2,y4.indexOf("]") - 1);
//
//                                if(!y4.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= thur.get(0).toString();
//                                        t2= thur.get(1).toString();
//
//                                        p4=t1+"---"+t2;
//
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//                                        intent.putExtra("timediffthur",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Thursday");
//                                }
//
//
//                            }
//
//                            if(object.getJSONArray("friday")!=null) {
//
//                                String y5;
//                                String t1,t2;
//
//
//                                fri = Objects.requireNonNull(object.getJSONArray("friday"));
//                                y5=fri.toString();
//
//                                y5= y5.substring(2,y5.indexOf("]") - 1);
//
//                                if(!y5.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= fri.get(0).toString();
//                                        t2= fri.get(1).toString();
//
//                                        p5=t1+"---"+t2;
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//                                        intent.putExtra("timedifffri",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Friday");
//                                }
//
//                            }
//
//
//                            if(object.getJSONArray("saturday")!=null) {
//
//                                String y6;
//                                String t1,t2;
//
//
//                                sat = Objects.requireNonNull(object.getJSONArray("saturday"));
//                                y6=sat.toString();
//
//                                y6= y6.substring(2,y6.indexOf("]") - 1);
//
//                                if(!y6.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= sat.get(0).toString();
//                                        t2= sat.get(1).toString();
//
//                                        p6=t1+"---"+t2;
//
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//                                        intent.putExtra("timediffsat",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Saturday");
//                                }
//
//                            }
//
//
//                            if(object.getJSONArray("sunday")!=null) {
//
//                                String y7;
//                                String t1,t2;
//
//                                sun = Objects.requireNonNull(object.getJSONArray("sunday"));
//                                y7=sun.toString();
//
//                                y7= y7.substring(2,y7.indexOf("]") - 1);
//
//                                if(!y7.equals("NoAppointment")) {
//
//                                    try {
//                                        t1= sun.get(0).toString();
//                                        t2= sun.get(1).toString();
//
//                                        p7=t1+"---"+t2;
//
//
//                                        LocalTime start = LocalTime.parse(t1, timeFormatter);
//                                        LocalTime end = LocalTime.parse(t2, timeFormatter);
//
//                                        Duration diff = Duration.between(start, end);
//
//                                        long hours = diff.toHours();
//                                        long minutes = diff.minusHours(hours).toMinutes();
//                                        String totalTimeString = String.format("%02d:%02d", hours, minutes);
//
//                                        if(hours<0){
//                                            hours=hours*-1;
//                                        }
//
//
//                                        intent.putExtra("timediffsun",hours);
//                                        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",totalTimeString);
//
//                                    } catch (JSONException ex) {
//                                        ex.printStackTrace();
//                                    }
//
//                                    arrayList.add("Sunday");
//                                }
//
//
//                            }
//
//
//
//                            ArrayList<String> common=new ArrayList<>(daylist);
//                            common.retainAll(arrayList);
//
//                            ArrayList<String> uncommon = new ArrayList<>(daylist);
//                            uncommon.removeAll(common);
//
//                            ArrayList<String> newUncommon = new ArrayList<>(uncommon);
//
//                            a.keySet().removeAll(newUncommon);
//
//                            ArrayList<String> keys = Collections.list(Collections.enumeration(a.keySet()));
//                            ArrayList<String> valueList = Collections.list(Collections.enumeration(a.values()));
//
//
//                            ArrayList<String> summation =new ArrayList<>();
//                            int i1=summation.size();
//                            summation.clear();
//
//
//
//                            String sum;
//
//                            for(int i=0;i<keys.size();i++){
//                                sum=keys.get(i)+", "+valueList.get(i);
//                                summation.add(sum);
//                            }
//
//                            String z1,z2,z3,z4,z5,z6,z7;
//
//                                    z1 = summation.get(0);
//                                    textView1.setText(z1);
//
//                                    z2 = summation.get(1);
//                                    textView2.setText(z2);
//
//                                    z3=summation.get(2);
//                                    textView3.setText(z3);
//
//                                    z4 = summation.get(3);
//                                    textView4.setText(z4);
//
//
//                                    if(4<summation.size()){
//
//                                        z5=summation.get(4);
//                                        textView5.setText(z5);
//                                    }
//
//                                    else {
//                                        Log.i("kkkkk","jjjjjjjj");
//                                    }
//
//                                    if(5<summation.size()){
//
//                                    z6 = summation.get(5);
//                                    textView6.setText(z6);
//
//                                    }
//
//                                    else {
//                                        Log.i("kkkkk","jjjjjjjj");
//                                    }
//
//
//
//
//                                Log.i("1111111111111111111111111111111111111111111",arrayList.toString());
//                                Log.i("222222222222222222222222222222222222222222",a.toString());
//                                Log.i("3333333333333333333333333333333333333333333",keys.toString());
//                                Log.i("3333333333333333333333333333333333333333333",valueList.toString());
//                                Log.i("44444444444444444444444444444444444444444444",uncommon.toString());
//                                Log.i("55555555555555555555555555555555555555555555",summation.toString());
//                                Log.i("55555555555555555555555555555555555555555555",Integer.toString(summation.size()));
//
//                        }
//
//                    }
//
//
//                }
//
//            }
//        });



    }
}