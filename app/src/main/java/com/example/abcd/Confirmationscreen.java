package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abcd.Aboutplace.placenamelist;
import com.example.abcd.hosclidocnamelist.Hosclidoctor;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Objects;

public class Confirmationscreen extends AppCompatActivity {


    AlertDialog.Builder builder;
    AlertDialog progressDialog;

    ImageView i1;
    ImageView i2;

    TextView status;

    TextView canceltextview;
    TextView viewlocationtextview;
    TextView date;
    TextView time;
    TextView department;
    TextView location;
    TextView name;

    ProgressBar progressBar2;
    String identity;


    ParseQuery<ParseObject> queryx = ParseQuery.getQuery("Clinicsbooked");
    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Clinicsbooked");

    String a1,a2,a3,a4,a5,clinicid,objectid;
    String k1,k2,k3,k4,k5,k6;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent= new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("identity",identity);
        startActivity(intent);
    }

        public void cancelbooking(View view){

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Are you sure about canceling this booking ?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            progressDialog = getDialogProgressBar().create();
                            progressDialog.show();

                            new CountDownTimer(4000,1000){

                                @Override
                                public void onTick(long millisUntilFinished) {

                                    if(clinicid==null) {


                                        query1.getInBackground(objectid, new GetCallback<ParseObject>() {

                                            @Override
                                            public void done(ParseObject object, ParseException e) {
                                                if (e == null && object != null) {

                                                    object.put("confirmed", "no");
                                                    object.saveInBackground();


                                                }
                                            }
                                        });
                                    }else{


                                        query1.getInBackground(clinicid, new GetCallback<ParseObject>() {

                                            @Override
                                            public void done(ParseObject object, ParseException e) {
                                                if (e == null && object != null) {

                                                    object.put("confirmed", "no");
                                                    object.saveInBackground();


                                                }
                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onFinish() {
                                    progressDialog.dismiss();
                                    i1.setImageResource(R.drawable.ic_baseline_cancel_24);
                                    progressBar2.setVisibility(View.INVISIBLE);
                                    i1.setVisibility(View.VISIBLE);

                                    status.setText("Cancelled");

                                    canceltextview.setVisibility(View.INVISIBLE);
                                    viewlocationtextview.setVisibility(View.INVISIBLE);

                                    canceltextview.setEnabled(false);
                                    viewlocationtextview.setEnabled(false);

                                }
                            }.start();
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
    }

    //progressDialog.show() and progressDialog.dismiss()


    public AlertDialog.Builder getDialogProgressBar() {

        if (builder == null) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle("Processing...");

            final ProgressBar progressBar = new ProgressBar(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(lp);
            builder.setView(progressBar);
        }
        return builder;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmationscreen);

        i1=findViewById(R.id.yes);
        i2=findViewById(R.id.no);

        status=findViewById(R.id.status);

        canceltextview=findViewById(R.id.cancelbookingtextview);
        viewlocationtextview=findViewById(R.id.viewlocationtextview);

        canceltextview.setVisibility(View.INVISIBLE);
        canceltextview.setEnabled(false);
        viewlocationtextview.setVisibility(View.INVISIBLE);
        viewlocationtextview.setEnabled(false);

        progressBar2=findViewById(R.id.progressBar2);

        date=findViewById(R.id.datetextview);
        time=findViewById(R.id.timetextview);
        department=findViewById(R.id.departmenttextview);
        location=findViewById(R.id.addresstextview);
        name=findViewById(R.id.clinicnametextview);


        Intent ix = getIntent();
        a1 = ix.getStringExtra("Date");
        a2 = ix.getStringExtra("Time");
        a3 = ix.getStringExtra("Name");
        a4 = ix.getStringExtra("Location");
        a5 = ix.getStringExtra("DepartmentBooked");
        identity=ix.getStringExtra("UniqueUserId");

        clinicid = ix.getStringExtra("ClinicId");// from main activity

        objectid = ix.getStringExtra("Objectid");// from dependent details

        if (clinicid != null) {

            new CountDownTimer(4000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                    queryx.getInBackground(clinicid, new GetCallback<ParseObject>() {

                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null && object != null) {

                            k1 = Objects.requireNonNull(object.get("Date")).toString();
                            k2 = Objects.requireNonNull(object.get("Time")).toString();
                            k3 = Objects.requireNonNull(object.get("Name")).toString();
                            k4 = Objects.requireNonNull(object.get("Location")).toString();
                            k5 = Objects.requireNonNull(object.get("DepartmentBooked")).toString();
                            k6 = Objects.requireNonNull(object.get("confirmed")).toString();

                            object.saveInBackground();



                        }
                    }
                });

                }

                @Override
                public void onFinish() {

                    date.setText(k1);
                    time.setText(k2);
                    department.setText(k4);
                    location.setText(k5);
                    name.setText(k3);
                    progressBar2.setVisibility(View.INVISIBLE);
                    i1.setVisibility(View.VISIBLE);

                    if(k6.equals("yes")) {
                        i1.setImageResource(R.drawable.ic_baseline_check_circle_25);
                        status.setText("Confirmed");
                        canceltextview.setVisibility(View.VISIBLE);
                        canceltextview.setEnabled(true);
                        viewlocationtextview.setVisibility(View.VISIBLE);
                        viewlocationtextview.setEnabled(true);
                    }
                    else{
                        i1.setImageResource(R.drawable.ic_baseline_cancel_24);
                        status.setText("Cancelled");
                        canceltextview.setVisibility(View.INVISIBLE);
                        canceltextview.setEnabled(false);
                        viewlocationtextview.setVisibility(View.INVISIBLE);
                        viewlocationtextview.setEnabled(false);
                    }

                }
            }.start();
        }

        else{

            new CountDownTimer(2000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    i1.setImageResource(R.drawable.ic_baseline_check_circle_25);
                    i1.setVisibility(View.VISIBLE);
                    progressBar2.setVisibility(View.INVISIBLE);
                    status.setText("Confirmed");
                    canceltextview.setVisibility(View.VISIBLE);
                    canceltextview.setEnabled(true);
                    viewlocationtextview.setVisibility(View.VISIBLE);
                    viewlocationtextview.setEnabled(true);


                }
            }.start();

            date.setText(a1);
            time.setText(a2);
            department.setText(a4);
            location.setText(a5);
            name.setText(a5);

        }

    }
}