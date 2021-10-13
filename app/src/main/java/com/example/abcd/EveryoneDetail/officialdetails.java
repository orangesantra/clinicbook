package com.example.abcd.EveryoneDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.abcd.R;
import com.example.abcd.Recyclerviewdetails.Requestlist;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class officialdetails extends AppCompatActivity {

    Intent intent;
    HashSet<String> namearrayList = new LinkedHashSet<>();
    HashSet<String> idlist = new LinkedHashSet<>();
    HashSet<String> agelist = new LinkedHashSet<>();
    HashSet<String> timebookedlist = new LinkedHashSet<>();
    public String official="";


    public  void clicktext(View view){


         intent=new Intent(getApplicationContext(), Requestlist.class);
         intent.putExtra("list",namearrayList);
         intent.putExtra("myidlist",idlist);
         intent.putExtra("agelist",agelist);
         intent.putExtra("timelist",timebookedlist);
         startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officialdetails);

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Clinicsbooked");
        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        Log.i("ppppppppppppppppppppppp",Integer.toString(objects.size()));
                        for(ParseObject object: objects){

                            idlist.add(object.getObjectId());
                            namearrayList.add(object.getString("DependentName"));
                            agelist.add(object.getString("DependentAge"));
                            timebookedlist.add(object.getString("Time"));


                        }

                    }


                }

            }
        });

        Intent i2=getIntent();
        official = i2.getStringExtra("usertype");
    }
}