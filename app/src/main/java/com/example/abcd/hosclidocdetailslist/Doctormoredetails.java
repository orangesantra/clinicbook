package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.abcd.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Doctormoredetails extends AppCompatActivity {

    String experiance;
    String specialization;
    String name;
    String Email;
    String contactnoD;
    String detailedabout;
    String s1;

    TextView drxyz;
    TextView experiancetextbview;
    TextView specializationtextview;
    TextView contactno;
    TextView mailid;
    TextView detailedabouttextview;

    CheckBox checkBox;
    RatingBar ratingbar;
    Double rating;

    String aw1,aw2,aw3;
    TextView award1,award2,award3;


    ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicData");

    public void callnowdoctor(View view){

    }


    public void bookmark(View view){

        if(checkBox.isChecked()){
            checkBox.setText("Bookmark");
        }else{
            checkBox.setText("Bookmarked");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctormoredetails);

        ratingbar = findViewById(R.id.ratingBardoc);


        checkBox = findViewById(R.id.bookmark);

        drxyz = findViewById(R.id.drxyz);
        experiancetextbview = findViewById(R.id.experiance);
        specializationtextview = findViewById(R.id.specializtion);
        contactno = findViewById(R.id.contacttinfo);
        mailid = findViewById(R.id.emailidinfo);
        detailedabouttextview = findViewById(R.id.detailedabout);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {

                        for (ParseObject object : objects) {

                            rating=object.getDouble("Rating");

                            aw1=object.getString("Award1");
                            aw2=object.getString("Award2");
                            aw3=object.getString("Award3");


                            ratingbar.setRating(rating.floatValue());


                            award1.setText(aw1);
                            award2.setText(aw2);
                            award3.setText(aw3);



                        }


                    }


                }
            }
        }
        );
        
                Intent i2 = getIntent();

                experiance = i2.getStringExtra("experience");
                specialization = i2.getStringExtra("specialization");
                name = i2.getStringExtra("name");
                Email = i2.getStringExtra("Email");
                contactnoD = i2.getStringExtra("Contactno");
                detailedabout = i2.getStringExtra("detailedabout");
                s1 = i2.getStringExtra("identity");
                rating = i2.getDoubleExtra("Rating", 0);


                drxyz.setText(name);
                experiancetextbview.setText(experiance);
                specializationtextview.setText(specialization);
                contactno.setText(contactnoD);
                mailid.setText(Email);
                detailedabouttextview.setText(detailedabout);


            }
        }