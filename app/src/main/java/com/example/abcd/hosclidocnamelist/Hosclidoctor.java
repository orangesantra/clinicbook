package com.example.abcd.hosclidocnamelist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abcd.R;
import com.example.abcd.Recyclerviewdetails.MyAdapter;
import com.example.abcd.Recyclerviewdetails.NatureModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Hosclidoctor extends AppCompatActivity {

    static HashSet<String> arrayList1 = new LinkedHashSet<>();
    static ArrayList<String> newarr1 ;
    static HashSet<String> idlist1 = new LinkedHashSet<>();
    static ArrayList<String> idarr1 ;
    static ArrayAdapter arrayAdapter1 ;
    private ArrayList<String> list;
    private ArrayList<String> id;
    MAdapter adapter;
    MAdapter adapter3;
    static ArrayList k;
    static ArrayList l;
    static ArrayList<String> myList;
    static ArrayList<String> myListx;

    static String s1;

    static HashSet<String> X = new LinkedHashSet<>();
    static HashSet<String> Y = new LinkedHashSet<>();
    ArrayList<String> arrX;
    ArrayList<String> arrY;



    RecyclerView recyclerView;

    static String faculty;

    public  void clickforclinic(View view){

        faculty="ClinicData";

        recyclerView.setVisibility(View.INVISIBLE);

        new CountDownTimer(4000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                recyclerView.setVisibility(View.VISIBLE);
                ad();

            }
        }.start();
        arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newarr1);
        arrayAdapter1.notifyDataSetChanged();


        k.clear();
        l.clear();
        X.clear();
        Y.clear();

        ParseQuery<ParseObject> query =ParseQuery.getQuery("ClinicData");

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){


                            X.add(object.getString("name"));
                            Y.add(object.getObjectId());
                        }

                        k.addAll(X);
                        l.addAll(Y);


                    }

                }

            }
        });


    }

    public void  clickforhospitals(View view){

        faculty="HospitalData";

        recyclerView.setVisibility(View.INVISIBLE);

        new CountDownTimer(4000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                recyclerView.setVisibility(View.VISIBLE);
                ad();

            }
        }.start();

        k.clear();
        l.clear();
        X.clear();
        Y.clear();

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Hospitaldata");

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){


                            X.add(object.getString("name"));
                            Y.add(object.getObjectId());
                        }

                        k.addAll(X);
                        l.addAll(Y);



                    }

                }

            }
        });



    }


    public  void ad(){
        adapter3 = new MAdapter(this, NatModel.getObjectList1());
        recyclerView.setAdapter(adapter3);

    }



    public  void clickfordoctors(View view){

        faculty="DoctorData";

        recyclerView.setVisibility(View.INVISIBLE);

        new CountDownTimer(4000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                recyclerView.setVisibility(View.VISIBLE);
                ad();

            }
        }.start();

        k.clear();
        l.clear();
        X.clear();
        Y.clear();

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Doctordata");

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            X.add(object.getString("name"));
                            Y.add(object.getObjectId());
                        }

                        k.addAll(X);
                        l.addAll(Y);



                    }

                }

            }
        });




    }

    public ArrayList<String> getList() {
        return list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosclidoctor);

        Intent i2=getIntent();
        faculty = i2.getStringExtra("Buttonid");
        s1=i2.getStringExtra("identity");




        ParseQuery<ParseObject> query =ParseQuery.getQuery(i2.getStringExtra("Buttonid"));

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            idlist1.add(object.getObjectId());
                            arrayList1.add(object.getString("name"));
                        }

                        if(newarr1!=null)
                        {
                            newarr1 = new ArrayList<>(arrayList1);
                            idarr1 = new ArrayList<>(idlist1);


                            Log.i("EXECUTEDDDDDDDDDDDDDDDDDDDDDDD",newarr1.toString());
                        }else{
                            Log.i("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF","NULLLLLLLLLLLL");
                        }


                        newarr1 = new ArrayList<>(arrayList1);
                        idarr1 = new ArrayList<>(idlist1);



                    }


                }

            }
        });

        myList = (ArrayList<String>) getIntent().getSerializableExtra("hos");
        myListx = (ArrayList<String>) getIntent().getSerializableExtra("hosid");


          if(myList!=null) {
              k = new ArrayList(myList);
          }

        if(myListx!=null) {
            l = new ArrayList(myListx);
        }



        recyclerView =  findViewById(R.id.rv);
        adapter = new MAdapter(this, NatModel.getObjectList1());
        recyclerView.setAdapter(adapter);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}