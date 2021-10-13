package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.abcd.MainActivity;
import com.example.abcd.R;
import com.example.abcd.paymentprocedure.Payment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class investorlist extends AppCompatActivity {


    String s1,s2,s3;

    Toolbar toolbar;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Investorlist");

    ArrayAdapter arrayAdapter;

    ArrayList arrayList=new ArrayList();

    public void recommendothers(View view){

        Intent intent = new Intent(getApplicationContext(),recommendothers.class);
        startActivity(intent);

    }

    public void investnow(View view){

        Intent intent = new Intent(getApplicationContext(), Payment.class);
        startActivity(intent);

    }

    public void returntohomepage(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investorlist);

        toolbar = findViewById(R.id.toolbarinvestor);
        final ListView listView = findViewById(R.id.linv);



        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),investmoredetail.class);
                intent.putExtra("item",listView.getId());
                startActivity(intent);

              
            }
        });



        query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null){
                        if(objects.size()>0){
                            for(ParseObject object: objects){

                                s1=object.getString("Ownername");
                                s2=object.getString("Verfiedornot");
                                s3=object.getString("DateofRegister");

                                arrayList.add(s1 +"      "+ s2 +"        "+ s3);

                                
                                object.saveInBackground();
                            }
                        }
                    }

                }
            });
    }
}