package com.example.abcd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.widget.Spinner;

import com.example.abcd.Aboutplace.placenamelist;

import java.text.DecimalFormat;

public class stateandcity extends AppCompatActivity {

    String selectedItemText;
    String selectedcityitem;
    String[] a1,a2,a3,a4,a5,a6,a7;
    String usertype;
    String listItemname;
    String s1;

    Double a,b,c,d;

    ArrayAdapter<String> adaptercity;

    Spinner scity;
    Spinner s;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
            }
        }
    }


    public void nextstateancdcity(View view){

        Intent intent= new Intent(getApplicationContext(), placenamelist.class);
        intent.putExtra("usertype",usertype);
        intent.putExtra("listitemname",listItemname);
        intent.putExtra("identity",s1);
        intent.putExtra("State",selectedItemText);
        intent.putExtra("City",selectedcityitem);

        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stateandcity);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
                a=location.getLatitude();
                b=location.getLongitude();

                c=Double.parseDouble(new DecimalFormat("##.#######").format(a));
                d=Double.parseDouble(new DecimalFormat("##.#######").format(b));

                String s1 = String.valueOf(c);
                String s2 = String.valueOf(d);

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





        Intent i2=getIntent();
        usertype = i2.getStringExtra("usertype");
        listItemname =i2.getStringExtra("listitemname");
        s1=i2.getStringExtra("identity");

        a1=new String[]{
                "Adilabad",
                "Anantapur",
                "Chittoor",
                "Kakinada",
                "Guntur",
                "Hyderabad",
                "Karimnagar",
                "Khammam",
                "Krishna",
                "Kurnool",
                "Mahbubnagar",
                "Medak",
                "Nalgonda",
                "Nizamabad",
                "Ongole",
                "Hyderabad",

        };
        a2=new String[]{

                "Anjaw",
                "Changlang",
                "East Siang",
                "Kurung Kumey",
                "Lohit"

        };
        a3=new String[]{
                "Baksa",
                "Barpeta",
                "Bongaigaon",

        };

        String[] arraySpinner = new String[] {
                "Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry"
        };



         scity = findViewById(R.id.cityspinner);

         s = findViewById(R.id.spinnerstatex);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         s.setAdapter(adapter);

         s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);

                if(selectedItemText.equals("Andhra Pradesh")){

                    adaptercity = new ArrayAdapter<String>(stateandcity.this,
                            android.R.layout.simple_spinner_item, a1);

                }

                if(selectedItemText.equals("Arunachal Pradesh")){

                    adaptercity = new ArrayAdapter<String>(stateandcity.this,
                            android.R.layout.simple_spinner_item, a2);

                }


                if(selectedItemText.equals("Assam")){

                    adaptercity = new ArrayAdapter<String>(stateandcity.this,
                            android.R.layout.simple_spinner_item, a3);

                }

                adaptercity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                scity.setAdapter(adaptercity);

                scity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedcityitem = (String) parent.getItemAtPosition(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}