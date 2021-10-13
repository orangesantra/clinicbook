package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.abcd.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class investmoredetail extends AppCompatActivity {


    TextView t1;
    TextView t2;
    TextView t3;

    TextView t4;
    TextView t5;
    TextView t6;

    RadioButton shares;
    RadioButton equity;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("Investorlist");

    public void companysharepur(View view){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investmoredetail);

        shares.setEnabled(true);
        equity.setEnabled(false);

        Intent i2=getIntent();
        i2.getStringExtra("item");
        
        t1=findViewById(R.id.fundingcomp);
        t2=findViewById(R.id.sharingcomp);
        t3=findViewById(R.id.partnerfrom);
        t4=findViewById(R.id.Classified);


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseObject object: objects){

                            t1.setText(object.getString("fundcomp"));
                            t2.setText(object.getString("sharecomp"));
                            t3.setText(object.getString("parnerfrom"));
                            t4.setText(object.getString("otherinvestment"));

                            object.saveInBackground();
                        }
                    }
                }

            }
        });

    }
}