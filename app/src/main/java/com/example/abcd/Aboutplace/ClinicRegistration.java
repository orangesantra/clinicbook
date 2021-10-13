package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abcd.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class ClinicRegistration extends AppCompatActivity {


    EditText e1,e2,e3,clinicwebsite,clinicesd;
    EditText minimumdoctors,maximumdoctors;
    Button buttonupload;

    EditText firstshiftclinic,firstshiftclinicto;

    CheckBox c1,c2,c3,c4,c5,c6,c7;
    ArrayList arrayList;

    String selectedItemText1,selectedItemText2;

    public void clinicreg(View view){

        arrayList.add(firstshiftclinic.getText().toString());
        arrayList.add(firstshiftclinicto.getText().toString());

        ParseUser user = new ParseUser();
        user.setUsername(e1.getText().toString());
        user.put("Address1",e2.getText().toString());
        user.put("Address2",e3.getText().toString());
        user.put("Minimumdoctors",minimumdoctors.getText().toString());
        user.put("Maximumdoctors",maximumdoctors.getText().toString());
        user.put("FirstShift",arrayList);
        user.put("Clincicwebsite",clinicwebsite);
        user.put("Clinicestablisment",clinicesd);


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    ParseUser.logOut();
                    Log.i("Info", "user signed up");

                } else {
                    String message = e.getMessage();
                    if(message.toLowerCase().contains("java")){
                        message = e.getMessage().substring(e.getMessage().indexOf(" "));
                    }

                    Toast.makeText(ClinicRegistration.this,"This use ",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_registration);

        firstshiftclinic=findViewById(R.id.firstshiftclinic);
        firstshiftclinicto=findViewById(R.id.firstshiftclinicto);

        arrayList = new ArrayList<String>();



        c1 =  findViewById(R.id.chkmon);
        c2 =  findViewById(R.id.chktue);
        c3 =  findViewById(R.id.chkwed);
        c4 =  findViewById(R.id.chkthu);
        c5 =  findViewById(R.id.chkfri);
        c6 =  findViewById(R.id.chksat);
        c7 =  findViewById(R.id.chksun);

        minimumdoctors=findViewById(R.id.minimumdoctors);
        maximumdoctors=findViewById(R.id.maximumdoctors);

        buttonupload=findViewById(R.id.clinicdocumentupload);

        e1=findViewById(R.id.clinicnametextviewReg);
        e2=findViewById(R.id.Address1reg);
        e3=findViewById(R.id.Address2reg);
        clinicwebsite=findViewById(R.id.clinicwebsite);
        clinicesd=findViewById(R.id.clinicesdablisment);

        buttonupload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivity(intent);
            }
        });


        String[] arraySpinner = new String[] {
          "AM","PM"
        };
        Spinner s = findViewById(R.id.spinnerampmclinic1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText1 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] arraySpinner2 = new String[] {
                "AM","PM"
        };
        Spinner s2 = findViewById(R.id.spinnerampmclinic2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText2 = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}