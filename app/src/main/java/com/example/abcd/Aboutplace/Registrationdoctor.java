package com.example.abcd.Aboutplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abcd.EveryoneDetail.officialdetails;
import com.example.abcd.Listresult;
import com.example.abcd.R;
import com.example.abcd.loginfeature.signuponly;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.DecimalFormat;

public class Registrationdoctor extends AppCompatActivity {


    public static final int PICK_IMAGE = 1;

    Button buttonupload;
    String selectedItemText;

    Double a1,b1,c1,d1;

    ParseObject entity = new ParseObject("DoctorRegistration");

    String name;
    String contactnumber;
    String a,b,c;

    EditText firstname;
    EditText secondname;
    EditText phonenumber;
    EditText emailid;

    EditText asoociatedclinic;
    EditText associatedhospital;

    EditText experiencetextview;

    EditText Addharnumber;
    EditText bankaccountnumber;
    EditText pannumber;

    String gender;


    LocationManager locationManager;
    LocationListener locationListener;

    public void doctormapaddress(View view){

        Intent intent = new Intent(getApplicationContext(),mapdoctor.class);
        startActivity(intent);

    }

    public void studentpage(View view){

        Intent intent = new Intent(getApplicationContext(),studentpage.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
            }
        }
    }


    public void uploadDocumentdoctor(View view){



    }

    public void submitdata(View view){

        a=firstname.getText().toString();
        b=secondname.getText().toString();
        c=phonenumber.getText().toString();

        name=a+" "+b;
        
        ParseUser user = new ParseUser();
        user.setUsername(name);
        user.setPassword(c);
        user.setEmail(emailid.getText().toString());
        user.put("experience",experiencetextview.getText().toString());
        user.put("UserType","Doctor");
        user.put("Speciality",selectedItemText);
        user.put("Addharnumber",Addharnumber.getText().toString());
        user.put("Bankaccountnumber",bankaccountnumber.getText().toString());

        if(!(asoociatedclinic.getText().toString() ==null)){
            user.put("AssociatedClinic",asoociatedclinic.getText().toString());

        }

        if(!associatedhospital.getText().toString().equals(null)){
            user.put("AssociatedHospital",associatedhospital.getText().toString());

        }



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

                    Toast.makeText(Registrationdoctor.this,"This use ",Toast.LENGTH_LONG).show();
                }
            }
        });


        entity.put("Name", name);
        entity.put("conatactnumber",c);


        entity.saveInBackground();






    }

    public  void redirectIfLoggedIn(){
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), officialdetails.class);
            startActivity(intent);
        }
    }




    public void uploadimagedoctor(View view){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }

    public void buttonclickedregister(View view) {


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.DependentMale:
                if (checked) {

                    gender="Male";

                }

                break;
            case R.id.DependentFemale:
                if (checked){

                    gender="Female";

                }

                break;
            case R.id.Dependantothers:
                if (checked){

                    gender="others";

                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationdoctor);

        Addharnumber=findViewById(R.id.addaharnumber);
        bankaccountnumber=findViewById(R.id.bankaccountnumber);
        pannumber=findViewById(R.id.pannumber);




        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
                a1=location.getLatitude();
                b1=location.getLongitude();

//                c1=Double.parseDouble(new DecimalFormat("##.#######").format(a));
//                d1=Double.parseDouble(new DecimalFormat("##.#######").format(b));
//
//                String s1 = String.valueOf(c1);
//                String s2 = String.valueOf(d1);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


        redirectIfLoggedIn();

        firstname=findViewById(R.id.FirstNameD);
        secondname=findViewById(R.id.LastnameD);
        phonenumber=findViewById(R.id.PhonenoD);
        emailid=findViewById(R.id.EmailidD);
        asoociatedclinic=findViewById(R.id.associatedclinic);
        associatedhospital=findViewById(R.id.associatedhospital);

        experiencetextview=findViewById(R.id.experianceeregD);

        buttonupload=findViewById(R.id.documentbutton);

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
       "A","B","C"
        };
        Spinner s = findViewById(R.id.specializationspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    }
