package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.abcd.Howtoregister;
import com.example.abcd.MainActivity;
import com.example.abcd.R;
import com.example.abcd.abcdofficialwebsite;

public class exploremore extends AppCompatActivity {

    VideoView v;
    ImageView imageview;
    ImageView i1;
    ImageView i2;


    public void ourinvester(View view){

        Intent intent = new Intent(getApplicationContext(), ourinvester.class);
        startActivity(intent);

    }

    public void nextpage(View view){

        Intent intent = new Intent(getApplicationContext(), investorlist.class);
        startActivity(intent);
        
    }

    public void returntohomepage(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

    public void playpause(View view){

        v.start();
        imageview.setVisibility(View.INVISIBLE);
        imageview.setEnabled(false);

    }

    public void howtoopen(View view){

        Intent intent = new Intent(getApplicationContext(),howtoopen.class);
        intent.putExtra("Page","howtoopen");
        startActivity(intent);

    }

    public void howtoregister(View view){
        Intent intent = new Intent(getApplicationContext(), howtoopen.class);
        intent.putExtra("Page","howtoregister");
        startActivity(intent);

    }

    public void eligibility(View view){
        Intent intent = new Intent(getApplicationContext(),howtoopen.class);
        intent.putExtra("Page","eligibility");
        startActivity(intent);

    }

    public void serviceweprovide(View view){
        Intent intent = new Intent(getApplicationContext(),howtoopen.class);
        intent.putExtra("Page","serviceweprovide");
        startActivity(intent);

    }

    public void registeraissue(View view){
        Intent intent = new Intent(getApplicationContext(),howtoopen.class);
        intent.putExtra("Page","registeraissue");
        startActivity(intent);


    }


    public void contactusabcd(View view){

        Intent intent = new Intent(getApplicationContext(),contactusabcd.class);
        startActivity(intent);

    }

    public void officialwebsite(View view){

        Intent intent = new Intent(getApplicationContext(), abcdofficialwebsite.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploremore);

        imageview=findViewById(R.id.imageView);
        i1=findViewById(R.id.thumsup);
        i2=findViewById(R.id.thumbsdown);

        imageview.setVisibility(View.VISIBLE);
        imageview.setEnabled(true);

        v=(VideoView)findViewById(R.id.videoView);
        v.setVideoPath("http:// kaesan clinic");
        MediaController m=new MediaController(this);

        m.setAnchorView(v);// m has clarified that it will go to 'v' View
        v.setMediaController(m);//now its on 'v' View ,that it will except on not


    }
}