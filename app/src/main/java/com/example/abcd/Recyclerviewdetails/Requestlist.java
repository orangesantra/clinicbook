package com.example.abcd.Recyclerviewdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.example.abcd.R;
import com.example.abcd.Timingperperson;
import com.example.abcd.Timingsedule;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class Requestlist extends AppCompatActivity {




     static ArrayList<String> newarr ;
     static ArrayList<String> idarr ;
     static ArrayList<String> agearr ;
     static ArrayList<String> timearr ;



     private ArrayList<String> list;
     private ArrayList<String> id;
     private ArrayList<String> age;
     private ArrayList<String> time;



    static HashSet<String> namearrayList = new LinkedHashSet<>();
    static HashSet<String> idlist = new LinkedHashSet<>();
    static HashSet<String> agelist = new LinkedHashSet<>();
    static HashSet<String> timebookedlist = new LinkedHashSet<>();


    public void timingperperson(View view){

        Intent intent = new Intent(getApplicationContext(), Timingperperson.class);
        startActivity(intent);
    }



     public  void timealloted(View view){

         Intent intent = new Intent(getApplicationContext(), Timingsedule.class);
         startActivity(intent);

     }

     public  void clickfor(View view){

         ParseQuery<ParseObject> query =ParseQuery.getQuery("Clinicsbooked");

         query.findInBackground(new FindCallback<ParseObject>() {
             @Override
             public void done(List<ParseObject> objects, ParseException e) {
                 if(e==null){
                     if(objects.size()>0){
                         Log.i("ppppppppppppppppppppppp",Integer.toString(objects.size()));

                         for(ParseObject object: objects){

                             idarr .add(object.getObjectId());
                             newarr.add(object.getString("DependentName"));
                             agearr.add(object.getString("DependentAge"));
                             timearr.add(object.getString("Time"));


                         }


                         new CountDownTimer(4000,1000){

                             @Override
                             public void onTick(long millisUntilFinished) {




                             }

                             @Override
                             public void onFinish() {

                                 Intent intent = new Intent(getApplicationContext(), Bookedlist.class);
                                 intent.putExtra("name",newarr);
                                 intent.putExtra("age",agearr);
                                 intent.putExtra("time",timearr);
                                 intent.putExtra("id",idarr);
                                 startActivity(intent);



                             }
                         }.start();



                     }

                 }

             }
         });

     }



        public Requestlist() {
            list = new ArrayList<>(namearrayList);
            id =new ArrayList<>(idlist);
            age=new ArrayList<>(agelist);
            time=new ArrayList<>(timebookedlist);

        }

        public ArrayList<String> getList() {
            return list;
        }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestlist);

        idarr= new ArrayList<>();
        newarr=new ArrayList<>();
        agearr=new ArrayList<>();
        timearr= new ArrayList<>();

    }
}