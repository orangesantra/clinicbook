package com.example.abcd.EveryoneDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.abcd.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Individualdetails extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    String username;
    String itemid;
    HashSet<String> arrayList1 = new HashSet<>();
    HashSet<String> arrayList2 = new HashSet<>();
    HashSet<String> arrayList3 = new HashSet<>();
    HashSet<String> arrayList4 = new HashSet<>();
    HashSet<String> arrayList5 = new HashSet<>();
    HashSet<String> arrayList6 = new HashSet<>();
    HashSet<String> idlist = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualdetails);

        textView1=findViewById(R.id.itemname);
        textView2=findViewById(R.id.itemparentname);
        textView3=findViewById(R.id.itemgender);
        textView4=findViewById(R.id.itemdateofbirth);
        textView5=findViewById(R.id.itemcontactno);
        textView6=findViewById(R.id.itemdateofappo);






        final List<Map<String,String>> patientnamewithid = new ArrayList<>();//this is the list of map objects/




        Intent i2=getIntent();
        username = i2.getStringExtra("itemname");
        itemid = i2.getStringExtra("itemid");

        textView1.setText(username);
        textView2.setText(itemid);
        Log.i("ABABABABBABABABABABABABABAB",itemid);






        ParseQuery<ParseObject> query = ParseQuery.getQuery("Data");
        if(itemid!=null){
        query.getInBackground(itemid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null&&object!=null){

                    textView3.setText(new StringBuilder().append(getString(R.string.gender)).append(object.getString("Gender")).toString());
                    textView4.setText(new StringBuilder().append(getString(R.string.parentsname)).append(object.getString("PatientParentname")).toString());
                    textView5.setText(new StringBuilder().append(getString(R.string.dateofappo)).append(object.getString("DateofAppointment")).toString());
                    textView6.setText(new StringBuilder().append(getString(R.string.contactno)).append(object.getString("Contactno")).toString());



                    Log.i("11111111111111111111111111111111111111111111111",(Objects.requireNonNull(object.getString("Patientname"))));
                    Log.i("22222222222222222222222222222222222222222222222",((Objects.requireNonNull(object.getString("Gender")))));
                    Log.i("33333333333333333333333333333333333333333333333",(Objects.requireNonNull(object.getString("PatientParentname"))));


                }
            }
        });}

        ParseQuery<ParseObject> query1 =ParseQuery.getQuery("Data");

        query1.whereEqualTo("USERTYPE","Patient/Relatives");
        query1.setLimit(5);//will take out only 1 shubham.

        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        Log.i("ppppppppppppppppppppppp",Integer.toString(objects.size()));
                        for(ParseObject object: objects){

                            Map<String,String> tweetInfo = new HashMap<>();
                            tweetInfo.put(object.getObjectId(),object.getString("Patientname"));

                            patientnamewithid.add(tweetInfo);

                            arrayList1.add(object.getString("Patientname"));
                            arrayList2.add(object.getString("Gender"));
                            arrayList3.add(object.getString("PatientParentname"));
                            arrayList4.add(object.getString("DateofAppointment"));
                            arrayList5.add(object.getString("Contactno"));
                            arrayList6.add(object.getString("Dateofbirth"));
                            idlist.add(object.getObjectId());

                        }


                        //---------------------IMPORTANT---------------------


//                        Map<String, String> m = new HashMap<String, String>();
//                        m.put("Hello", "World");
//                        m.put("Apple", "3.14");
//                        m.put("Another", "Element");
//
//                        List<String> list = new ArrayList<String>(m.keySet());
//
//                        List<String> list = new ArrayList<String>(m.values());


                        Log.i("cccccccccccccccccccccccccccccccc",patientnamewithid.toString());
                    }


                }

            }
        });



    }
}