package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Timingsedule extends AppCompatActivity {

    String hr1;
    String hr2;
    String min1;
    String min2;
    String am_pm1;
    String am_pm2;
    String Fromfirstshift="";
    String Tofirstshift="";
    String FirstShift;

    TextView Mon;
    TextView Tue;
    TextView Wed;
    TextView Thur;
    TextView Fri;
    TextView Sat;
    TextView Sun;

    TextView finalday;

    String DAY;
    String S1;
    String your_variable;

    TextView textView;
    TextView firstshifttextview;

    ArrayList<String> arrayList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList<String> XXX;

    SharedPreferences pref;
    String[] grpname;

    CardView cv;

    Button button;
    Button changetimebutton;
    Button cancel;

    TextView noapptextview;

    Switch switchx;

    TextView kk;


    boolean b1=true;


    ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicData");



    public  void cancel(View view){

        if (finalday.getText().toString().equals("Monday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("mon","No appointment this day!");
            editor.apply();

        }



        if (finalday.getText().toString().equals("Tuesday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("tue", "No appointment this day!");
            editor.apply();
        }

        if (finalday.getText().toString().equals("Wednesday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("wed","No appointment this day!");
            editor.apply();
        }

        if (finalday.getText().toString().equals("Thursday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("thu","No appointment this day!");
            editor.apply();
        }

        if (finalday.getText().toString().equals("Friday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("fri", "No appointment this day!");
            editor.apply();
        }

        if (finalday.getText().toString().equals("Saturday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("sun", "No appointment this day!");
            editor.apply();
        }

        if (finalday.getText().toString().equals("Sunday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("sun", "No appointment this day!");
            editor.apply();
        }



    }




    public void wow(View view){

        if(switchx.isChecked()){

            S1=finalday.getText().toString().toLowerCase();

            //            kk.setText("No Appointment");
            //            textView.setVisibility(View.INVISIBLE);
            //            firstshifttextview.setVisibility(View.INVISIBLE);

            Log.i("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy","CARD IS INVISIBLE");

            cv.setVisibility(View.INVISIBLE);
            cv.setEnabled(false);

//            query.whereEqualTo("name","Clinic1");
//
//            query.findInBackground(new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    if(e==null){
//                        if(objects.size()>0){
//                            for(ParseObject object: objects){
////                              object.put("AppointmentTimeFirstShift",FirstShift);
//                                object.put(S1, XXX);
//                                object.saveInBackground();
//                            }
//                        }
//                    }
//                }
//            });

            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);


            if (finalday.getText().toString().equals("Monday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("mon","No Appointment this day!");
                editor.apply();

            }



            if (finalday.getText().toString().equals("Tuesday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("tue", "No Appointment this day!");
                editor.apply();
            }

            if (finalday.getText().toString().equals("Wednesday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("wed","No Appointment this day!");
                editor.apply();
            }

            if (finalday.getText().toString().equals("Thursday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("thu","No Appointment this day!");
                editor.apply();
            }

            if (finalday.getText().toString().equals("Friday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("fri", "No Appointment this day!");
                editor.apply();
            }

            if (finalday.getText().toString().equals("Saturday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("sun", "No Appointment this day!");
                editor.apply();
            }

            if (finalday.getText().toString().equals("Sunday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("sun", "No Appointment this day!");
                editor.apply();
            }





        }
        else{

            //            kk.setVisibility(View.INVISIBLE);
//            textView.setVisibility(View.VISIBLE);
//            firstshifttextview.setVisibility(View.VISIBLE);

            Log.i("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt","CARD IS VISIBLE");

            cv.setVisibility(View.VISIBLE);
            cv.setEnabled(true);



            button.setVisibility(View.VISIBLE);
            button.setEnabled(true);



            if (finalday.getText().toString().equals("Monday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("mon",FirstShift);
                editor.apply();

            }



            if (finalday.getText().toString().equals("Tuesday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("tue", FirstShift);
                editor.apply();
            }

            if (finalday.getText().toString().equals("Wednesday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("wed", FirstShift);
                editor.apply();
            }

            if (finalday.getText().toString().equals("Thursday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("thu", FirstShift);
                editor.apply();
            }

            if (finalday.getText().toString().equals("Friday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("fri", FirstShift);
                editor.apply();
            }

            if (finalday.getText().toString().equals("Saturday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("sun", FirstShift);
                editor.apply();
            }

            if (finalday.getText().toString().equals("Sunday")) {

                preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                editor = preferences.edit();
                editor.putString("sun", FirstShift);
                editor.apply();
            }

        }


    }



    public  void changetime(View view){

        switchx.setVisibility(View.VISIBLE);
        switchx.setEnabled(true);


//        cancel.setVisibility(View.VISIBLE);
//        cancel.setEnabled(true);


        changetimebutton.setEnabled(false);
        changetimebutton.setVisibility(View.INVISIBLE);

        cv.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        cv.setEnabled(true);
        button.setEnabled(true);

        textView.setVisibility(View.INVISIBLE);
        firstshifttextview.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);
        firstshifttextview.setEnabled(false);

    }


    public void setAppointmentTime(View view){

        if(!finalday.getText().toString().equals("Select a Day from above")){
        dialog();
        }else{

            Toast.makeText(getApplicationContext(), "Please Select a Day", Toast.LENGTH_SHORT).show();


        }
    }


    void dialog(){

        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle("Select a Group Name");
        alt_bld.setSingleChoiceItems(grpname, -1, new DialogInterface
                .OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:




                        if(textView.getText().toString().equals("No Appointment this day!"))

                        {


                            cv.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);
                            cv.setEnabled(true);
                            button.setEnabled(true);


                            textView.setVisibility(View.INVISIBLE);
                            firstshifttextview.setVisibility(View.INVISIBLE);


                            changetimebutton.setEnabled(false);
                            changetimebutton.setVisibility(View.INVISIBLE);
                        }else {


                            textView.setVisibility(View.VISIBLE);
                            firstshifttextview.setVisibility(View.VISIBLE);

                            cv.setVisibility(View.INVISIBLE);
                            button.setVisibility(View.INVISIBLE);
                            cv.setEnabled(false);
                            button.setEnabled(false);

                            changetimebutton.setEnabled(true);
                            changetimebutton.setVisibility(View.VISIBLE);

                        }

                        break;

                    case 1:



                            textView.setVisibility(View.INVISIBLE);

                            button.setVisibility(View.VISIBLE);
                            button.setEnabled(true);


                            textView.setVisibility(View.INVISIBLE);
                            firstshifttextview.setVisibility(View.INVISIBLE);


                            cv.setVisibility(View.INVISIBLE);
//                        button.setVisibility(View.INVISIBLE);
                            cv.setEnabled(false);
//                        button.setEnabled(false);

                            changetimebutton.setEnabled(true);
                            changetimebutton.setVisibility(View.VISIBLE);







                        break;
                }
                Toast.makeText(getApplicationContext(), "Group Name = "+grpname[item], Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();


///// grpname is a array where data is stored...


    }

    public  void m1(View view) {



        switchx.setChecked(true);
        switchx.setChecked(false);

        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);

        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);



            changetimebutton.setEnabled(true);
            changetimebutton.setVisibility(View.VISIBLE);

            cv.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            cv.setEnabled(false);
            button.setEnabled(false);


            textView.setVisibility(View.VISIBLE);
            firstshifttextview.setVisibility(View.VISIBLE);
            textView.setEnabled(true);
            firstshifttextview.setEnabled(true);


            pref = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
            String name = pref.getString("mon", "No Appointment this day!");

            textView.setText(name);


            Sun.setBackgroundResource(R.color.quantum_lightblue300);
            Tue.setBackgroundResource(R.color.quantum_lightblue300);
            Wed.setBackgroundResource(R.color.quantum_lightblue300);
            Thur.setBackgroundResource(R.color.quantum_lightblue300);
            Fri.setBackgroundResource(R.color.quantum_lightblue300);
            Sat.setBackgroundResource(R.color.quantum_lightblue300);


            Mon.setBackgroundResource(R.color.quantum_bluegrey300);


            DAY = Mon.getText().toString();
            finalday.setText(DAY);

        }


    public  void m2(View view) {



        switchx.setChecked(true);
        switchx.setChecked(false);

        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);


        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);





            changetimebutton.setEnabled(true);
            changetimebutton.setVisibility(View.VISIBLE);

            cv.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            cv.setEnabled(false);
            button.setEnabled(false);


            textView.setVisibility(View.VISIBLE);
            firstshifttextview.setVisibility(View.VISIBLE);
            textView.setEnabled(true);
            firstshifttextview.setEnabled(true);


            pref = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
            String name = pref.getString("tue", "No Appointment this day!");


            textView.setText(name);

            Sun.setBackgroundResource(R.color.quantum_lightblue300);
            Mon.setBackgroundResource(R.color.quantum_lightblue300);
            Wed.setBackgroundResource(R.color.quantum_lightblue300);
            Thur.setBackgroundResource(R.color.quantum_lightblue300);
            Fri.setBackgroundResource(R.color.quantum_lightblue300);
            Sat.setBackgroundResource(R.color.quantum_lightblue300);


            Tue.setBackgroundResource(R.color.quantum_bluegrey300);


            DAY = Tue.getText().toString();
            finalday.setText(DAY);

        }




    public  void m3(View view){




        switchx.setChecked(true);
        switchx.setChecked(false);

        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);


        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);


            changetimebutton.setEnabled(true);
            changetimebutton.setVisibility(View.VISIBLE);

            cv.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            cv.setEnabled(false);
            button.setEnabled(false);


            textView.setVisibility(View.VISIBLE);
            firstshifttextview.setVisibility(View.VISIBLE);
            textView.setEnabled(true);
            firstshifttextview.setEnabled(true);


            pref = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
            String name = pref.getString("wed", "No Appointment this day!");

            Log.i("99999999999999999999999999999999999999999", name);


            textView.setText(name);

            Sun.setBackgroundResource(R.color.quantum_lightblue300);
            Tue.setBackgroundResource(R.color.quantum_lightblue300);
            Mon.setBackgroundResource(R.color.quantum_lightblue300);
            Thur.setBackgroundResource(R.color.quantum_lightblue300);
            Fri.setBackgroundResource(R.color.quantum_lightblue300);
            Sat.setBackgroundResource(R.color.quantum_lightblue300);


            Wed.setBackgroundResource(R.color.quantum_bluegrey300);


            DAY = Wed.getText().toString();
            finalday.setText(DAY);
        }



    public  void m4(View view){


        switchx.setChecked(true);
        switchx.setChecked(false);

        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);



        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);




        changetimebutton.setEnabled(true);
        changetimebutton.setVisibility(View.VISIBLE);

        cv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        cv.setEnabled(false);
        button.setEnabled(false);


        textView.setVisibility(View.VISIBLE);
        firstshifttextview.setVisibility(View.VISIBLE);
        textView.setEnabled(true);
        firstshifttextview.setEnabled(true);


        pref =  getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
        String name = pref.getString("thu", "No Appointment this day!");

        Log.i("99999999999999999999999999999999999999999",name);


        textView.setText(name);

        Sun.setBackgroundResource(R.color.quantum_lightblue300);
        Tue.setBackgroundResource(R.color.quantum_lightblue300);
        Wed.setBackgroundResource(R.color.quantum_lightblue300);
        Mon.setBackgroundResource(R.color.quantum_lightblue300);
        Fri.setBackgroundResource(R.color.quantum_lightblue300);
        Sat.setBackgroundResource(R.color.quantum_lightblue300);



        Thur.setBackgroundResource(R.color.quantum_bluegrey300);


        DAY=Thur.getText().toString();
        finalday.setText(DAY);

    }


    public  void m5(View view){


        switchx.setChecked(true);
        switchx.setChecked(false);


        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);


        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);





        changetimebutton.setEnabled(true);
        changetimebutton.setVisibility(View.VISIBLE);

        cv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        cv.setEnabled(false);
        button.setEnabled(false);

        textView.setVisibility(View.VISIBLE);
        firstshifttextview.setVisibility(View.VISIBLE);
        textView.setEnabled(true);
        firstshifttextview.setEnabled(true);


        pref =  getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
        String name = pref.getString("fri", "No Appointment this day!");

        textView.setText(name);

        Sun.setBackgroundResource(R.color.quantum_lightblue300);
        Tue.setBackgroundResource(R.color.quantum_lightblue300);
        Wed.setBackgroundResource(R.color.quantum_lightblue300);
        Thur.setBackgroundResource(R.color.quantum_lightblue300);
        Mon.setBackgroundResource(R.color.quantum_lightblue300);
        Sat.setBackgroundResource(R.color.quantum_lightblue300);



        Fri.setBackgroundResource(R.color.quantum_bluegrey300);

        DAY=Fri.getText().toString();
        finalday.setText(DAY);

    }


    public  void m6(View view){


        switchx.setChecked(true);
        switchx.setChecked(false);


        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);


        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);



        changetimebutton.setEnabled(true);
        changetimebutton.setVisibility(View.VISIBLE);

        cv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        cv.setEnabled(false);
        button.setEnabled(false);


        textView.setVisibility(View.VISIBLE);
        firstshifttextview.setVisibility(View.VISIBLE);
        textView.setEnabled(true);
        firstshifttextview.setEnabled(true);


        pref =  getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
        String name = pref.getString("sat", "No Appointment this day!");

        Log.i("99999999999999999999999999999999999999999",name);


        textView.setText(name);

        Sun.setBackgroundResource(R.color.quantum_lightblue300);
        Tue.setBackgroundResource(R.color.quantum_lightblue300);
        Wed.setBackgroundResource(R.color.quantum_lightblue300);
        Thur.setBackgroundResource(R.color.quantum_lightblue300);
        Fri.setBackgroundResource(R.color.quantum_lightblue300);
        Mon.setBackgroundResource(R.color.quantum_lightblue300);



        Sat.setBackgroundResource(R.color.quantum_bluegrey300);


        DAY=Sat.getText().toString();
        finalday.setText(DAY);

    }


    public  void m7(View view){


        switchx.setChecked(true);
        switchx.setChecked(false);

        switchx.setVisibility(View.INVISIBLE);
        switchx.setEnabled(false);


        cancel.setVisibility(View.INVISIBLE);
        cancel.setEnabled(false);


        changetimebutton.setEnabled(true);
        changetimebutton.setVisibility(View.VISIBLE);

        cv.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        cv.setEnabled(false);
        button.setEnabled(false);

        textView.setVisibility(View.VISIBLE);
        firstshifttextview.setVisibility(View.VISIBLE);
        textView.setEnabled(true);
        firstshifttextview.setEnabled(true);

        pref =  getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
        String name = pref.getString("sun", "No Appointment this day!");

        Log.i("99999999999999999999999999999999999999999",name);


        textView.setText(name);

        Mon.setBackgroundResource(R.color.quantum_lightblue300);
        Tue.setBackgroundResource(R.color.quantum_lightblue300);
        Wed.setBackgroundResource(R.color.quantum_lightblue300);
        Thur.setBackgroundResource(R.color.quantum_lightblue300);
        Fri.setBackgroundResource(R.color.quantum_lightblue300);
        Sat.setBackgroundResource(R.color.quantum_lightblue300);


        Sun.setBackgroundResource(R.color.quantum_bluegrey300);


        DAY=Sun.getText().toString();
        finalday.setText(DAY);

    }






    public void save(View view){

        String value = null;
        String your_variable;





//        your_variable = getPreferences(MODE_PRIVATE).getString("Name of variable","");
//
//        preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
//        editor = preferences.edit();
//        editor.putString("mon",FirstShift);
//        editor.putString("tue",FirstShift);
//        editor.putString("wed",FirstShift);
//        editor.putString("thu",FirstShift);
//        editor.putString("fri",FirstShift);
//        editor.putString("sat",FirstShift);
//        editor.putString("sun",FirstShift);
//        editor.apply();
//
//        pref =  getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
//        String name = pref.getString("mon", "");
//        if(!name.equalsIgnoreCase(""))
//        {
//            name = name + "  Sethi";  /* Edit the value here*/
//        }

        S1=finalday.getText().toString().toLowerCase();

        arrayList=new ArrayList<String>();

        Fromfirstshift= hr1+":"+min1+" "+am_pm1;
        Tofirstshift = hr2+":"+min2+" "+am_pm2;

        arrayList.add(Fromfirstshift);
        arrayList.add(Tofirstshift);

        FirstShift=Fromfirstshift+"---"+Tofirstshift;

//        if(noapptextview.isEnabled()){
//            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);
//
//            editor = preferences.edit();
//            editor.putString("NA","No appointment for this day");
//            editor.apply();
//
//
//        }


        if (finalday.getText().toString().equals("Monday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("mon",FirstShift);
            editor.apply();

        }



        if (finalday.getText().toString().equals("Tuesday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("tue", FirstShift);
            editor.apply();
        }

        if (finalday.getText().toString().equals("Wednesday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("wed", FirstShift);
            editor.apply();
        }

        if (finalday.getText().toString().equals("Thursday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("thu", FirstShift);
            editor.apply();
        }

        if (finalday.getText().toString().equals("Friday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("fri", FirstShift);
            editor.apply();
        }

        if (finalday.getText().toString().equals("Saturday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("sun", FirstShift);
            editor.apply();
        }

        if (finalday.getText().toString().equals("Sunday")) {

            preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

            editor = preferences.edit();
            editor.putString("sun", FirstShift);
            editor.apply();
        }


//        if(hr1!=null&&hr2!=null&&min1!=null&&min2!=null&&am_pm1!=null&&am_pm2!=null){
//
//            query.whereEqualTo("name","Clinic1");
//
//            query.findInBackground(new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    if(e==null){
//                        if(objects.size()>0){
//                            for(ParseObject object: objects){
//                                object.put("AppointmentTimeFirstShift",FirstShift);
//                                object.put(S1, arrayList);
//                                object.saveInBackground();
//                            }
//                        }
//                    }
//                }
//            });
//
//        }
//        else {
//
//            Toast.makeText
//                    (getApplicationContext(), "Please Enter a valid Time", Toast.LENGTH_SHORT)
//                    .show();
//
//        }

        Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timingsedule);


        XXX=new ArrayList<>();
        XXX.add("NoAppointment");

        cancel=findViewById(R.id.no);

        switchx=findViewById(R.id.switch1);
        switchx.setChecked(false);

        kk=findViewById(R.id.kk);
        kk.setVisibility(View.INVISIBLE);


        if(switchx.isChecked()){

            changetimebutton.setEnabled(false);
            changetimebutton.setVisibility(View.INVISIBLE);

            cv.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            cv.setEnabled(false);
            button.setEnabled(false);

            textView.setVisibility(View.INVISIBLE);
            firstshifttextview.setVisibility(View.INVISIBLE);
            textView.setEnabled(true);
            firstshifttextview.setEnabled(true);



        }



        finalday=findViewById(R.id.dayFinal);

        grpname = new String[]{"Set appointment Time on this day", "No Appointment on this day"};


        textView=findViewById(R.id.adoptdata);
        firstshifttextview=findViewById(R.id.firstshifttextview);

        Mon=findViewById(R.id.mon);
        Tue=findViewById(R.id.tue);
        Wed=findViewById(R.id.wed);
        Thur=findViewById(R.id.thur);
        Fri=findViewById(R.id.fri);
        Sat=findViewById(R.id.sat);
        Sun=findViewById(R.id.sun);

        cv=findViewById(R.id.cv);

        button=findViewById(R.id.save);
        changetimebutton=findViewById(R.id.changebutton);




        String[] arraySpinner1 = new String[] {
                "00","01","02","03","04","05","06","07","08","09","10","11","12"

        };
        Spinner s1 = findViewById(R.id.hr1);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hr1 = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner2 = new String[] {

                "00","10","20","30","40","50"

        };
        Spinner s2 = findViewById(R.id.min1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                min1 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner3 = new String[] {
                "AM","PM"

        };
        Spinner s3 = findViewById(R.id.am_pm1);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter3);

        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                am_pm1 = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        String[] arraySpinner4 = new String[] {

                "00","01","02","03","04","05","06","07","08","09","10","11","12"

        };
        Spinner s4 = findViewById(R.id.hr2);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(adapter4);

        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hr2 = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner5 = new String[] {

                "00","10","20","30","40","50"


        };
        Spinner s5 = findViewById(R.id.min2);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner5);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s5.setAdapter(adapter5);

        s5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                min2= (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner6 = new String[] {

                "AM","PM"

        };
        Spinner s6 = findViewById(R.id.am_pm2);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner6);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s6.setAdapter(adapter6);

        s6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                am_pm2= (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}