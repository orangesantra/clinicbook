package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.abcd.EveryoneDetail.Details;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import static com.example.abcd.R.*;

public class placeinformation extends AppCompatActivity {

    String usertype="";
    String geometry="";
    TextView textView8;
    TextView textView11;
    TextView textview15;
    String substring ;
    String substring1 ;
    String substring2 ;
    String substringphoto;
    TextView appointmenttextview;
    String usertypeer="";
    String listItemname="";
    String u1;
    String l1;
    Double rating;
    RatingBar simpleRatingBar;
    ImageView imageView;
    Bitmap myImage;
    Bitmap myBitmap;
    String photo;
    String photoreference;


    public void downloadImage(View view) {



    }

    public void appointment (View view){
        Intent intent= new Intent(getApplicationContext(), Details.class);
        intent.putExtra("usertype",u1);
        intent.putExtra("listitemname",l1);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_placeinformation);

        simpleRatingBar = (RatingBar) findViewById(id.ratingBar);


        imageView = findViewById(id.imageView6);
        textView8 = findViewById(id.textView8);
        textView11 = findViewById(id.textView11);
        textview15 = findViewById(id.textView15);
        appointmenttextview = findViewById(id.textView12);

        Intent i2=getIntent();
        usertype = i2.getStringExtra("listitem");
        geometry = i2.getStringExtra("listitemgeometry");
        usertypeer = i2.getStringExtra("usertype");
        listItemname =i2.getStringExtra("listitemname");



        photo =i2.getStringExtra("photo");
        if(photo!="notavailable"&&photo.contains("photo_reference")) {
            substringphoto = photo.substring(photo.indexOf("photo_reference") + 18, photo.indexOf("width") - 3);
        }else{
            substringphoto="chill";
        }


        ImageDownloader task = new ImageDownloader();
        if(substringphoto!="chill") {

            StringBuilder photoPlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?maxwidth=5184&photoreference=");
            photoPlaceUrl.append(substringphoto);
            photoPlaceUrl.append("&key=AIzaSyDkcKmhN0HlvU8UXrepOtxx3jOJ_nm_4-M");


        try {
            task.execute(photoPlaceUrl.toString()).get();
            imageView.setImageBitmap(myBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else {

            imageView.setImageResource(drawable.flower1);
        }

        photoreference=photo;


        rating =i2.getDoubleExtra("rating",0);
        if(rating<=5.0) {
            simpleRatingBar.setVisibility(View.VISIBLE);
            simpleRatingBar.setRating(rating.floatValue());
            textview15.setText(rating.toString());
        }else{
            simpleRatingBar.setVisibility(View.INVISIBLE);
            textview15.setVisibility(View.INVISIBLE);
        }

        u1=usertypeer;
        l1=listItemname;


        textView8.setText(usertype);


        if(geometry!="location not found"&&geometry.contains("viewport")){
        substring=geometry.substring(geometry.indexOf("location"),geometry.indexOf("viewport"));}
        else{
            substring="nothing";
        }

        if(substring!="nothing"){
        substring1=substring.substring(substring.indexOf("lat")+5,substring.indexOf(","));
        substring2=substring.substring(substring.indexOf("lng")+5,substring.indexOf("}"));
        }else {
            substring1="no";
            substring2="no";

        }



        Log.i("lalallallalalallalalallaallallalalla",substring);
        Log.i("777777777777777777777777777777777777",substring1);
        Log.i("888888888888888888888888888888888888",substring2);

if(substring1!="no"&&substring2!="no"){
        double d1 = Double.parseDouble(substring1);
        double d2 = Double.parseDouble(substring2);

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address1="Couldn't find adderss :(";
        try{
            List<Address> listaddresses = geocoder.getFromLocation(d1,d2,1);
            if(listaddresses !=null && listaddresses.size() >0){
                Log.i("placeInfo",listaddresses.get(0).toString());
                address1 ="";
                if(listaddresses.get(0).getAdminArea() != null){
                    address1 +=listaddresses.get(0).getAdminArea() + "\n ";

                }
                if(listaddresses.get(0).getSubAdminArea() != null){
                    address1 +=listaddresses.get(0).getSubAdminArea() + "\n ";

                }
                if(listaddresses.get(0).getLocality() != null){
                    address1 +=listaddresses.get(0).getLocality() + ",";
                }

                if(listaddresses.get(0).getThoroughfare() != null){
                    address1 +=listaddresses.get(0).getThoroughfare() + ",";
                }

                if(listaddresses.get(0).getPostalCode() != null){
                    address1 +=listaddresses.get(0).getPostalCode() + ".";
                }




            }
           textView11.setText("Address : "+ address1);
            Log.i("Place Location",address1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }else {
    textView11.setText("Address not available for this location");
}
    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        //bitmap is the way to return our image
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream in = connection.getInputStream();

                 myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}