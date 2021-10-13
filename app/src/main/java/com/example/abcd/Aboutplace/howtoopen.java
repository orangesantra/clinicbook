package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.abcd.R;

public class howtoopen extends AppCompatActivity {

    String k1,k2="";
    TextView capturepanel;
    Toolbar toolbar;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void facebookgroup(View view){

        Intent intent = new Intent(getApplicationContext(),facebookgroup.class);
        intent.putExtra("facebooklink","http://adainnanan//ckdmcmcm/ckdmcmd");

        startActivity(intent);

    }

    public void telegramcommunity(View view){

        Intent intent = new Intent(getApplicationContext(),telegramcommunity.class);
        intent.putExtra("telelink","http://adainnanan//ckdmcmcm/ckdmcmd");

        startActivity(intent);

    }

    public void dicordcommunity(View view){

        Intent intent = new Intent(getApplicationContext(),discord.class);
        intent.putExtra("discordlink","http://adainnanan//ckdmcmcm/ckdmcmd");


        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.helpfulyes:
                if (checked) {
                     preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                                editor = preferences.edit();
                                editor.putString("agreed","Yes");
                                editor.apply();


                }

                break;
            case R.id.helpfulno:
                if (checked){
                    preferences = getSharedPreferences("com.example.abcd", Context.MODE_PRIVATE);

                    editor = preferences.edit();
                    editor.putString("agreed","No");
                    editor.apply();
                }

                break;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoopen);

        toolbar=findViewById(R.id.toolbarhowtoopen);
        capturepanel=findViewById(R.id.capturepanel);


        Intent i2=getIntent();
        k1= i2.getStringExtra("Page");
        k2= i2.getStringExtra("helponpage");
        toolbar.setTitle(k1);
        capturepanel.setText(k2);

        if(k2=="1"){

            capturepanel.setText("hi hello how are you good or bad not wanna tell go from my app noob audiance dont even know how to open");
        }
        if(k2=="2"){

            capturepanel.setText("U should be a indian citizen to use");

        }

        if (k2=="3"){

            capturepanel.setText("Go to home menu click on registration button.");

        }

        if(k2=="4"){

            capturepanel.setText("clincs,hospital and doctor registration");

        }

    }
}