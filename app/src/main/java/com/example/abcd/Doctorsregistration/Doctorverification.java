package com.example.abcd.Doctorsregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.abcd.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Doctorverification extends AppCompatActivity {

    TextView verifierornot;
    TextView registerdate;
    TextView clinicownername;


    String s1,s2,s3;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("ClinicRegistration");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorverification);

        verifierornot=findViewById(R.id.verifiedclinic);
        registerdate=findViewById(R.id.registerdateofclinic);
        clinicownername=findViewById(R.id.clinicownername);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            s1=object.getString("Ownername");
                            s2=object.getString("Verfiedornot");
                            s3=object.getString("DateofRegister");

                            verifierornot.setText(s3);
                            registerdate.setText(s2);
                            clinicownername.setText(s1);

                            object.saveInBackground();
                        }
                    }
                }

            }
        });


    }
}