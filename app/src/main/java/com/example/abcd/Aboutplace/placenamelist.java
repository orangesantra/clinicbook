package com.example.abcd.Aboutplace;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abcd.MainActivity;
import com.example.abcd.R;
import com.example.abcd.hosclidocnamelist.Hosclidoctor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class placenamelist extends AppCompatActivity {

    Double a,b,c,d;
    String s1,s2;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> namelist = new ArrayList<>();
    HashSet<String> locationlist = new LinkedHashSet<>();
    HashSet<String> photolist = new LinkedHashSet<>();
    HashSet<String> openNowlist = new LinkedHashSet<>();
    HashSet<String> underlocationlist= new LinkedHashSet<>();
    HashSet<Double> RAtinglist= new LinkedHashSet<>();
    ArrayList<String> locationlist1;
    ArrayList<String> photolist1;
    ArrayList<String> openNowlist1;
    ArrayList<Double> RAtinglist1;
    ArrayAdapter arrayAdapter;
    ////////////////////////////////////////////////
    String listitemname="";
    String listitemlocation="";
    String usertype="";
    String listItemname="";
    String selectedType="";
    String listitemrating="";
    Double ratingvalue;
    String photoitemname;
    ListView listView;

    HashSet<String> hosname= new LinkedHashSet<>();
    HashSet<String> hosid = new LinkedHashSet<>();

    ArrayList<String> newarrhos;
    ArrayList<String> idarrhos;

    String faculty;

    String state;
    String city;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//if that dialog box is clicked "yes".

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
            }
        }
    }

    public  class DownloadTask extends AsyncTask<String,Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                Log.i("RESULT",result);
                return result;


            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }



        String geometry;
        String photo;
        String openNow;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONObject jsonObject = new JSONObject(s);


                String results = jsonObject.getString("results");

                Log.i("wwwwww",results);

                JSONArray arr = new JSONArray(results);

                for (int i=0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String name = jsonPart.getString("name");
                    geometry = jsonPart.getString("geometry");
                    photo = jsonPart.optString("photos");
                    openNow =jsonPart.optString("opening_hours");
                    Double Rating =  jsonPart.optDouble("rating");

                    if (!name.equals("")) {
                        namelist.add(name);
                    }

                    if (!photo.equals("")) {
                        photolist.add(photo);
                    }
                    photolist1 = new ArrayList<>(photolist);

                    if (!openNow.equals("")) {
                        openNowlist.add(openNow);
                    }
                    openNowlist1 = new ArrayList<>(openNowlist);


                    RAtinglist.add(Rating);
                    RAtinglist1=new ArrayList<>(RAtinglist);


                    if(!geometry.equals("")){
                        locationlist.add(geometry);
                    }
                    locationlist1=new ArrayList<>(locationlist);

                }
                arrayAdapter.notifyDataSetChanged();


                if (!namelist.equals("")) {
                    Log.i("MESSAGE",namelist.toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }

                if (!openNowlist.equals("")) {
                    Log.i("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ",openNowlist.toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }

                if (!locationlist.equals("")) {
                    Log.i("LOLOLOLOLOOLOLOOLLLOLOLLOLLOLLOLLOLLO",locationlist.toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }



                if (!underlocationlist.equals("")) {
                    Log.i("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",underlocationlist.toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }

                if (!RAtinglist.equals("")) {
                    Log.i("LAVAVLAVLAVLAVLAVLAVLALVALVLAVLAVLALVLAVLLVLLALVALVLALVAL",RAtinglist.toString());
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void showhospials(View view){

        faculty="HospitalData";

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Hospitaldata");

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            hosid.add(object.getObjectId());
                            hosname.add(object.getString("name"));
                        }



                        newarrhos = new ArrayList<>(hosname);
                        idarrhos = new ArrayList<>(hosid);
                        hosname.clear();
                        hosid.clear();

                        Intent intent = new Intent(getApplicationContext(), Hosclidoctor.class);
                        intent.putExtra("Buttonid",faculty);
                        intent.putExtra("hos",newarrhos);
                        intent.putExtra("hosid",idarrhos);
                        startActivity(intent);



                    }


                }

            }
        });




    }

    public void showdoctors(View view){

        faculty="DoctorData";

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Doctordata");

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            hosid.add(object.getObjectId());
                            hosname.add(object.getString("name"));
                        }




                        newarrhos = new ArrayList<>(hosname);
                        idarrhos = new ArrayList<>(hosid);
                        hosid.clear();
                        hosname.clear();

                        Intent intent = new Intent(getApplicationContext(), Hosclidoctor.class);
                        intent.putExtra("Buttonid",faculty);
                        intent.putExtra("hos",newarrhos);
                        intent.putExtra("hosid",idarrhos);
                        intent.putExtra("identity",s1);
                        startActivity(intent);



                    }


                }

            }
        });

    }

    public void showclinic(View view){

        faculty="ClinicData";


        ParseQuery<ParseObject> query =ParseQuery.getQuery("ClinicData");
        query.whereEqualTo("State",state);
        query.whereEqualTo("City",city);

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            hosid.add(object.getObjectId());
                            hosname.add(object.getString("name"));
                        }

                        newarrhos = new ArrayList<>(hosname);
                        idarrhos = new ArrayList<>(hosid);
                        hosname.clear();
                        hosid.clear();

                        Intent intent = new Intent(getApplicationContext(), Hosclidoctor.class);
                        intent.putExtra("Buttonid",faculty);
                        intent.putExtra("hos",newarrhos);
                        intent.putExtra("hosid",idarrhos);
                        intent.putExtra("identity",s1);
                        startActivity(intent);



                    }


                }

            }
        });


        listView.setVisibility(View.VISIBLE);

        namelist.clear();
        locationlist.clear();
        photolist.clear();
        underlocationlist.clear();
        RAtinglist.clear();



        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+c+","+d);
        googlePlaceUrl.append("&radius=20000");
        googlePlaceUrl.append("&type=hospital");
        googlePlaceUrl.append("&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M");

        try {
            placenamelist.DownloadTask task = new placenamelist.DownloadTask();
            //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+c.toString()+","+d.toString()+"radius=20000&type=restaurant&keyword=cruise&key=AIzaSyC7qPcw_RJc0viL6MosFzGkgo6P0lBu6yE").get();
            //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();
            //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+s1+","+s2+"&radius=2000000&type=hospital&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();
            task.execute(googlePlaceUrl.toString()).get();
            //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+s1+","+s2+"&rankby=distance&type=food&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();


        }catch (Exception e){
            e.printStackTrace();
        }




    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    String selectedItemText="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placenamelist);
        Intent i2=getIntent();
        usertype = i2.getStringExtra("usertype");
        listItemname =i2.getStringExtra("listitemname");
        s1=i2.getStringExtra("identity");
        state=i2.getStringExtra("State");
        city=i2.getStringExtra("City");

        String[] arraySpinner = new String[] {
                "Golambar",
                "Gaighat",
                "kumhrar",
                "Boring Road",
                "Belly Road"
        };
        Spinner s = findViewById(R.id.spinnerlocation);
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


        listView = findViewById(R.id.listView1);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namelist);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int k=locationlist1.size();
                int j=RAtinglist1.size();
                int p=photolist1.size();

                Intent intent = new Intent(getApplicationContext(), placeinformation.class);
                listitemname=namelist.get(i);

                if(p>i&&photolist1.get(i)!=null) {
                    photoitemname = photolist1.get(i);
                }else{
                    photoitemname="notavailable";
                }

                if(j>=i&&RAtinglist1.get(i)!=null){
                    ratingvalue=RAtinglist1.get(i);
                }else {
                    ratingvalue=7.0;
                }


                if(k>=i&&locationlist1.get(i)!=null){
                    listitemlocation= locationlist1.get(i);
                }
                else {
                    listitemlocation="location not found";
                }

                selectedType = "Patient/Relatives";
                intent.putExtra("listitem",listitemname);
                intent.putExtra("listitemgeometry",listitemlocation);
                intent.putExtra("usertype",selectedType);
                intent.putExtra("listitemname",usertype);
                intent.putExtra("rating",ratingvalue);
                intent.putExtra("photo",photoitemname);



                startActivity(intent);
            }
        });

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

                Log.i("AAAAAAAAA",c.toString());


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



    }



}
