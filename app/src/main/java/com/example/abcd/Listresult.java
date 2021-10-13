package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.abcd.Aboutplace.Registrationdoctor;
import com.example.abcd.Doctorsregistration.Doctorsregistration;
import com.example.abcd.EveryoneDetail.officialdetails;

public class Listresult extends AppCompatActivity {

    TextView textView;
    TextView textView8;
    RadioGroup rdg;
    RadioButton ss,sp;
    String selectedType=null;
    Intent searchIntent;
    String usertype="";
    String s1;


    public void onRadioButtonClicked1(View view) {

//        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i==R.id.serviceseeker){
//                    selectedType = ss.getText().toString();
//                    Log.i("llllllllllllllllllllllllllllllllll",selectedType);
//
//                }else if(i==R.id.serviceprovider){
//                    selectedType = "Doctor side";
//                    Log.i("llllllllllllllllllllllllllllllllll",selectedType);
//
//                }
//            }
//
//
//        });





//       Details obj = new Details();

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.serviceseeker:
                if (checked) {
                    selectedType = "Patient/Relatives";
                    searchIntent=new Intent(getApplicationContext(),MainActivity.class);
                    searchIntent.putExtra("usertype",selectedType);
                    searchIntent.putExtra("listitemname",usertype);
                    searchIntent.putExtra("identity",s1);

                }

                break;
            case R.id.serviceprovider:
                if (checked){
                    selectedType = "Doctor/Official";
                    searchIntent=new Intent(getApplicationContext(), Registrationdoctor.class);
                    searchIntent.putExtra("usertype",selectedType);
                    searchIntent.putExtra("listitemname",usertype);
                }

                break;
        }


        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Intent searchIntent=new Intent(getApplicationContext(),Details.class);
//                searchIntent.putExtra("usertype",selectedType);

                startActivity(searchIntent);
            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listresult);

        Toolbar toolbar =findViewById(R.id.toolbar);

        Intent i2 = getIntent();
        s1=i2.getStringExtra("identity");



        textView=findViewById(R.id.textView);
        textView8=findViewById(R.id.textView8);

        rdg = (RadioGroup) findViewById(R.id.rdg);
        ss = (RadioButton) findViewById(R.id.serviceseeker);
        sp = (RadioButton) findViewById(R.id.serviceprovider);

//        Intent i2=getIntent();//gives us back the current intent that got us here.i2 has taken information from the intentobj of earlier intent.
//        usertype = i2.getStringExtra("listitem");
//        textView8.setText(usertype);



    }
}
