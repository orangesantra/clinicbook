package com.example.abcd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abcd.Aboutplace.placenamelist;
import com.example.abcd.Article.Articleactivity;
import com.example.abcd.Myappointmentpackage.Myappointmentlist;
import com.example.abcd.loginfeature.loginsignup;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    Double a,b,c,d;
    String s1,s2;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> namelist = new ArrayList<>();
    HashSet<String> locationlist = new LinkedHashSet<>();
    HashSet<String> photolist = new LinkedHashSet<>();
    HashSet<String> underlocationlist= new LinkedHashSet<>();
    HashSet<Double> RAtinglist= new LinkedHashSet<>();
    ArrayList<String> locationlist1;
    ArrayList<String> photolist1;
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
    TextView txtname;
    TextView txtemail;
/////////////////////////////////////////////////
    Button logoutBtn;
    TextView userName,userEmail,userId;
    ImageView profileImage;
    ImageView imageview;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
////////////////////////////////////////////////
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    LocationManager locationManager;
    LocationListener locationListener;

    ArrayList<String> appointmentnamelist;
    ArrayList<String> addresslist;
    ArrayList<String> objectIdlist;

    ArrayList<String>  appointmentnamelistXX;
    ArrayList<String>  addresslistXX;
    ArrayList<String>  objectIdlistxx;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//if that dialog box is clicked "yes".

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
            }
        }
    }



    public void myappointmentlist(View view){

        ParseQuery<ParseObject> query =ParseQuery.getQuery("Clinicsbooked");
        query.whereEqualTo("UniqueUserId",s1);

        query.setLimit(5);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){

                        for(ParseObject object: objects){

                            appointmentnamelist.add(object.getString("Name"));
                            addresslist.add(object.getString("Location"));
                            objectIdlist.add(object.getObjectId());
                        }

                        appointmentnamelistXX=new ArrayList<>(appointmentnamelist);
                        addresslistXX=new ArrayList<>(addresslist);
                        objectIdlistxx=new ArrayList<>(objectIdlist);

                        appointmentnamelist.clear();
                        addresslist.clear();
                        objectIdlist.clear();

                        Intent intent = new Intent(getApplicationContext(), Myappointmentlist.class);
                        intent.putExtra("appointmentnamelist",appointmentnamelistXX);
                        intent.putExtra("addresslist",addresslistXX);
                        intent.putExtra("objectlist",objectIdlistxx);
                        intent.putExtra("UniqueUserId",s1);

                        startActivity(intent);

                    }


                }

            }
        });


    }

    public  void showarticlelist(View view){
        Intent intent = new Intent(getApplicationContext(), Articleactivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentnamelist= new ArrayList<String>();
        addresslist=new ArrayList<String>();
        objectIdlist=new ArrayList<>();


        Intent i2=getIntent();
        s1=i2.getStringExtra("identity");


        imageview=(ImageView)findViewById(R.id.imageView);

        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,  this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
//                        new ResultCallback<Status>() {
//                            @Override
//                            public void onResult(Status status) {
//                                if (status.isSuccess()){
////                                    gotoMainActivity();
//                                    Intent intent = new Intent(getApplicationContext(), loginsignup.class);
//                                    startActivity(intent);
//                                }else{
//                                    Toast.makeText(getApplicationContext(),"Session not close", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//            }
//        });
//
//        Intent i2=getIntent();
//        usertype = i2.getStringExtra("usertype");
//        listItemname =i2.getStringExtra("listitemname");

        setupToolbarMenu();
        setupNavigationDrawerMenu();

//        listView = findViewById(R.id.listView);
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namelist);
////        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                int k=locationlist1.size();
//                int j=RAtinglist1.size();
//                int p=photolist1.size();
//
//                Intent intent = new Intent(getApplicationContext(), placeinformation.class);
//                listitemname=namelist.get(i);
//
//                if(p>i&&photolist1.get(i)!=null) {
//                    photoitemname = photolist1.get(i);
//                }else{
//                    photoitemname="notavailable";
//                }
//
//                if(j>=i&&RAtinglist1.get(i)!=null){
//                    ratingvalue=RAtinglist1.get(i);
//                }else {
//                    ratingvalue=7.0;
//                }
//
//
//                if(k>=i&&locationlist1.get(i)!=null){
//                    listitemlocation= locationlist1.get(i);
//                }
//                else {
//                    listitemlocation="location not found";
//                }
//
//                selectedType = "Patient/Relatives";
//                intent.putExtra("listitem",listitemname);
//                intent.putExtra("listitemgeometry",listitemlocation);
//                intent.putExtra("usertype",selectedType);
//                intent.putExtra("listitemname",usertype);
//                intent.putExtra("rating",ratingvalue);
//                intent.putExtra("photo",photoitemname);
//
//
//
//                startActivity(intent);
//            }
//        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.i("Location",location.toString());
//                a=location.getLatitude();
//                b=location.getLongitude();
//
//                c=Double.parseDouble(new DecimalFormat("##.#######").format(a));
//                d=Double.parseDouble(new DecimalFormat("##.#######").format(b));
//
//                String s1 = String.valueOf(c);
//                String s2 = String.valueOf(d);
//
//                Log.i("AAAAAAAAA",c.toString());
//
//
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }


    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();

            String s1= account.getDisplayName();
            txtname.setText(s1);

            String s2=account.getEmail();
            txtemail.setText(s2);

            try{
                Glide.with(this).load(account.getPhotoUrl()).into(imageview);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }

        }else{
            gotoMainActivity();
        }
    }

    private void gotoMainActivity(){
        Intent intent=new Intent(this, Listresult.class);
        startActivity(intent);
    }

    private void gotologinsignup(){
        Intent intent=new Intent(this, loginsignup.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void setupToolbarMenu() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Navigation View");
    }

    private void setupNavigationDrawerMenu() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);

        View headerView = navigationView.getHeaderView(0);
        txtname = (TextView) headerView.findViewById(R.id.txvName);
        txtemail=headerView.findViewById(R.id.txvEmail);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);//"this" is the context of activity from which it is called
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,// ActionBarDrawerToggle will give berger menu in toolbar.
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        mDrawerLayout.addDrawerListener(drawerToggle);//for attaching drawer layout with berger menu.
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {//this function is called when a menu item is clicked

//		menuItem.setCheckable(true);
//		menuItem.setChecked(true);  // This helps to know which Menu Item is Clicked

        String itemName = (String) menuItem.getTitle();//fetching the menu item that is clicked

        Toast.makeText(MainActivity.this, itemName + " Clicked", Toast.LENGTH_SHORT).show();

        closeDrawer();

        switch (menuItem.getItemId()) {

            case R.id.item_logout:
                ParseUser.logOut();
                gotologinsignup();

                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()){
                                    gotologinsignup();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;

            case R.id.item_bag:
                // Put your item specific Code here
                break;
        }

        return true;
    }

    // Close the Drawer
    private void closeDrawer() {//this function specifies that drawqer will close to start of the screen
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    // Open the Drawer
    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else
            super.onBackPressed();
    }



    public void showlist(View view){

        Intent intent =new Intent(getApplicationContext(), stateandcity.class);
        intent.putExtra("identity",s1);
    startActivity(intent);

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
        DownloadTask task = new DownloadTask();
        //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+c.toString()+","+d.toString()+"radius=20000&type=restaurant&keyword=cruise&key=AIzaSyC7qPcw_RJc0viL6MosFzGkgo6P0lBu6yE").get();
        //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();
        //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+s1+","+s2+"&radius=2000000&type=hospital&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();
        task.execute(googlePlaceUrl.toString()).get();
        //task.execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+s1+","+s2+"&rankby=distance&type=food&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M").get();


    }catch (Exception e){
        e.printStackTrace();
    }
}



    public  class DownloadTask extends AsyncTask<String,Void, String>{
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
                Double Rating =  jsonPart.optDouble("rating");

                if (!name.equals("")) {
                    namelist.add(name);
                }

                if (!photo.equals("")) {
                    photolist.add(photo);
                }
                photolist1 = new ArrayList<>(photolist);


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
}