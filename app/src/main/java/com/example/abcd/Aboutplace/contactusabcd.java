package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abcd.R;

public class contactusabcd extends AppCompatActivity {

    TextView textView;
    TextView textView1;

    public void whatsappreg(View view){
        String url = "https://api.whatsapp.com/send?phone="+"4454565879";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void bookap(View view){

        Toast.makeText(contactusabcd.this, "Functionality not available now!",Toast.LENGTH_SHORT).show();


    }

    public void goback(View view){
        Intent intent = new Intent(getApplicationContext(),exploremore.class);
        startActivity(intent);
    }


    public void newsletter(View view){

        //opennesletter
    }

    public void emailreg(View view){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "some@email.address" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));

    }

    public void callreg(View view){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:123 456789"));
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactusabcd);

        textView=findViewById(R.id.bappoin);
        textView1=findViewById(R.id.goback);
    }
}