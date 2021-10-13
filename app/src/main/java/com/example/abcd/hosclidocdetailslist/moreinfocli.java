package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abcd.R;
import com.example.abcd.contactclinic;
import com.example.abcd.hosclidocnamelist.Hosclidoctor;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class moreinfocli extends AppCompatActivity {

    TextView estdateclinic;
    TextView abotclinnicmore;

    TextView clinicviews;
    TextView clinicownedby;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicData");

    String s1,s2,s3,s4,s5,s6;
    
    TextView clinicweburl;

    public void contactclinic(View view){

        Intent intent = new Intent(getApplicationContext(), contactclinic.class);
        startActivity(intent);
    }


    public void  facebookclinic(View view){

        Intent intent = new Intent(getApplicationContext(),facebookclinic.class);
        intent.putExtra("facebookpage","https://www.facebook.com/aasmanpatna/");
        startActivity(intent);

    }

    public void twitterclinic(View view){

        Intent intennt = new Intent(getApplicationContext(),facebookclinic.class);
        intennt.putExtra("twitterpage","https://evergreenlogo_");
        startActivity(intennt);

        
    }

    public void clinicurl(View view){
       Intent intent = new Intent(getApplicationContext(),clinicwebpage.class);
       intent.putExtra("clinicweburl",clinicweburl.getText().toString());
       startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfocli);

        clinicownedby=findViewById(R.id.ownedby);

        estdateclinic=findViewById(R.id.estdateclinic);
        abotclinnicmore=findViewById(R.id.aboutclinicmore);
        clinicviews=findViewById(R.id.viewsaboutclinic);
        clinicweburl=findViewById(R.id.clinicweburl);

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            s1=object.getString("EstDate");
                            s2=object.getString("Aboutclinicmore");
                            s3=object.getString("Reviwsonclinic");
                            s4=object.getString("OwnedBy");
                            s5=object.getString("facebookpagelink");
                            s6=object.getString("twitterpageclinic");

                            estdateclinic.setText(s1);
                            abotclinnicmore.setText(s2);
                            clinicviews.setText(s3);

                            if(s4!=null) {
                                clinicownedby.setText(s4);
                            }else {
                                clinicownedby.setText("Information not Available");
                            }




                        }


                    }


                }

            }
        });

    }
}