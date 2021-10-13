package com.example.abcd.hosclidocdetailslist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abcd.R;
import com.example.abcd.SharedPref;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

import static android.R.layout.simple_list_item_1;

public class Clinicdetails extends AppCompatActivity {

    String ClinicName;
    String ClinicId;
    String s1;
    String location;


    ArrayList DepartmentNameList;
    ArrayAdapter arrayAdapter;
    String listitemDepartmentName;

    TextView textView;
    TextView textView51;

    TextView bookmark;

    ListView listView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Boolean b=true;

    public void moreinfocli(View view){
        Intent intent = new Intent(getApplicationContext(),moreinfocli.class);
        startActivity(intent);

        
    }

    public void bookmark(View view){

        preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);



        if(b=true){


            editor = preferences.edit();
            editor.putString("Bookmarked", "yes");
            editor.apply();
        
        b=false;
        }else{
            editor = preferences.edit();
            editor.putString("Bookmarked", "no");
            editor.apply();

            b=true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicdetails);


        listView = findViewById(R.id.departmentslistview);

        bookmark = findViewById(R.id.bookmark);

        textView = findViewById(R.id.ClinicNameTextview);
        textView51 = findViewById(R.id.textView51);

        final Intent i2=getIntent();
        ClinicName = i2.getStringExtra("itemname");
        ClinicId = i2.getStringExtra("itemid");

        s1 = i2.getStringExtra("identity");

        textView.setText(ClinicName);
        textView51.setText(ClinicId);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicData");
        if(ClinicId!=null){
            query.getInBackground(ClinicId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if(e==null&&object!=null){


                        DepartmentNameList= new ArrayList(Objects.requireNonNull(object.getList("DepartmentName")));
                        location=object.getString("cliniclocation");


                        arrayAdapter= new ArrayAdapter<String>(Clinicdetails.this, android.R.layout.simple_list_item_1, DepartmentNameList);

                        listView.setAdapter(arrayAdapter);


                        arrayAdapter.notifyDataSetChanged();

                    }

                }

            });}

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listitemDepartmentName= (String) DepartmentNameList.get(position);

                Intent intent = new Intent(getApplicationContext(),Clinicmoredetails.class);
                intent.putExtra("DepartmentItemName",listitemDepartmentName);
                intent.putExtra("identity", s1);
                intent.putExtra("clinicname",ClinicName);
                intent.putExtra("Objectid",ClinicId);
                intent.putExtra("location",location);


                startActivity(intent);



            }
        });

    }
}