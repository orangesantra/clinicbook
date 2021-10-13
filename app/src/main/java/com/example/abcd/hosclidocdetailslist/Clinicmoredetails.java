package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.abcd.R;
import com.example.abcd.BookAppointments.BookAppointment;

public class Clinicmoredetails extends AppCompatActivity {

    CardView i1;
    Toolbar toolbar;
    String Departmentitemname;
    TextView booknowtextView;
    ImageView imageView;

    String s1;
    String c="ClinicData";

    String clinicname;
    String objectid;
    String location;

    public  void  booknow(View view){
        i1.animate().translationYBy(-1000).setDuration(500);

        booknowtextView.setEnabled(false);

    }

    public  void online(View view){

    }

    public  void  close(View view){

        i1.animate().translationYBy(1000).setDuration(250);
        booknowtextView.setEnabled(true);
    }

    public void visit(View view){

        Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
        intent.putExtra("identity",s1);
        intent.putExtra("clinicname",clinicname);
        intent.putExtra("Objectid",objectid);
        intent.putExtra("service",c);
        intent.putExtra("department",Departmentitemname);
        intent.putExtra("location",location);

        startActivity(intent);
        i1.animate().translationYBy(1000).setDuration(250);
        booknowtextView.setEnabled(true);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicmoredetails);

        booknowtextView=findViewById(R.id.booknowtextview);
        imageView=findViewById(R.id.imageViewcross);

        i1 =  findViewById(R.id.cardviewx);
        i1.setY(1000);

        toolbar=findViewById(R.id.clinicmoreDetailsToolbar);

        Intent i2=getIntent();
        clinicname=i2.getStringExtra("clinicname");
        Departmentitemname = i2.getStringExtra("DepartmentItemName");
        s1 = i2.getStringExtra("identity");
        objectid = i2.getStringExtra("Objectid");
        location = i2.getStringExtra("location");


        Log.i("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",objectid);


        toolbar.setTitle(Departmentitemname);


    }
}