package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutInfoPage extends AppCompatActivity {

    TextView aboutusdes;
    TextView emailaboutus;
    TextView phonenoaboutus;
    TextView addressabourus;
    EditText askdout;

    String s;
    String s1;
    String s2;
    String s3;

    String web;

    public void frequentlyaskedquestion(View view){}

    public void facebook(View view){

        web="https://www.facebook.com/photo?fbid=342891780564806&set=gm.1421067771607562";



        Intent intent= new Intent(getApplicationContext(),facebookpage.class);
        intent.putExtra("web",web);
        startActivity(intent);




    }




    public void instagram(View view){

        web="https://www.instagram.com/p/BlCF6CrH7F1/?utm_source=ig_embed&ig_rid=7c056f61-0939-4df1-b201-360335e40263";



        Intent intent= new Intent(getApplicationContext(),facebookpage.class);
        intent.putExtra("web",web);
        startActivity(intent);

    }

    public void submitdout(View view){

        ParseObject entity = new ParseObject("Doubtsection");

        entity.put("Doubt",askdout.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info_page);

        aboutusdes=findViewById(R.id.adoutusdescriotion);
        emailaboutus=findViewById(R.id.emailaboutus);
        phonenoaboutus=findViewById(R.id.phonenoaboutus);
        addressabourus=findViewById(R.id.addressaboutus);
        askdout=findViewById(R.id.askdout);



        ParseQuery<ParseObject> query1 =ParseQuery.getQuery("AboutUs");

        query1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            s=object.getString("AboutUs");
                            s1=object.getString("EmailAboutUs");
                            s2=object.getString("PhoneNo");
                            s3=object.getString("Address");



                        }


                    }


                }

            }
        });

        aboutusdes.setText(s);

    }
}