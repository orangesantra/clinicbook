package com.example.abcd.Myappointmentpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.abcd.R;

import java.util.ArrayList;

public class Myappointmentlist extends AppCompatActivity {

    static ArrayList<String> appointmentarraylist;
    static ArrayList<String> addressarraylist;
    static ArrayList<String> objectIdlist;
    static String identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myappointmentlist);

        Intent i2=getIntent();
        identity=i2.getStringExtra("UniqueUserId");


        appointmentarraylist = (ArrayList<String>) getIntent().getSerializableExtra("appointmentnamelist");
        addressarraylist = (ArrayList<String>) getIntent().getSerializableExtra("addresslist");
        objectIdlist = (ArrayList<String>) getIntent().getSerializableExtra("objectlist");


        RecyclerView recyclerView =  findViewById(R.id.recyclerViewMyappointment);
        MyAdapterMyappointment adapter = new MyAdapterMyappointment(this, NatureModelMyappointment.getObjectList());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//for linear configuration of layout

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}