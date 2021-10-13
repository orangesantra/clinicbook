package com.example.abcd.hosclidocdetailslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abcd.BookAppointments.BookAppointment;
import com.example.abcd.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DoctorsDetails extends AppCompatActivity {

    CardView c1;
    CardView c6;
    CardView c7;
    ImageView a1;
    Button button;

    String doctorid;
    String doctorname;
    String s1;
    String d="Doctordata";

    String experiance;
    String specialization;
    String location;
    String department;
    String associated;
    String contactno;
    String mailid;
    String detailedabout;

    TextView doctornametextview;
    TextView locationtextview;
    TextView Asoociatedwithtextview;
    TextView departmenttextview;
    TextView departmentup;


    public void booknowdoctor(View view){
        c1.animate().translationYBy(-1000).setDuration(500);
        button.setEnabled(false);
        c6.setEnabled(false);
        c7.setEnabled(false);
    }

    public  void  closed(View view){

        c1.animate().translationYBy(1000).setDuration(250);
        button.setEnabled(true);

        c6.setEnabled(true);
        c7.setEnabled(true);
    }

    public void visitd(View view){

        Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
        intent.putExtra("Doctorname",doctorname);
        intent.putExtra("Objectid",doctorid);
        intent.putExtra("identity", s1);
        intent.putExtra("service",d);
        intent.putExtra("department",department);
        intent.putExtra("location",location);
        startActivity(intent);

        c1.animate().translationYBy(1000).setDuration(250);
        button.setEnabled(true);

        c6.setEnabled(true);
        c7.setEnabled(true);

    }

    public void moredetailsdoctors(View view){

        Intent intent = new Intent(getApplicationContext(), Doctormoredetails.class);
        intent.putExtra("experience",experiance);
        intent.putExtra("specialization",specialization);
        intent.putExtra("name",doctorname);
        intent.putExtra("Email",mailid);
        intent.putExtra("Contactno",contactno);
        intent.putExtra("detailedabout",detailedabout);
        intent.putExtra("identity",s1);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_details);

        Intent i2=getIntent();
        doctorname = i2.getStringExtra("itemname");
        doctorid = i2.getStringExtra("itemid");
        s1 = i2.getStringExtra("identity");


        doctornametextview=findViewById(R.id.doctornametextview);
        locationtextview=findViewById(R.id.locationtextviewD);
        departmenttextview=findViewById(R.id.departmenttextviewD);
        Asoociatedwithtextview=findViewById(R.id.Associatedtextview);
        departmentup=findViewById(R.id.departmentup);

        a1=findViewById(R.id.imageViewcrossd);
        c1=findViewById(R.id.cardviewdx);
        c1.setY(1000);

        c6=findViewById(R.id.cardView6);
        c7=findViewById(R.id.cardView7);

        button=findViewById(R.id.booknowbuttondoctor);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctordata");
        if(doctorid!=null){
            query.getInBackground(doctorid, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if(e==null&&object!=null){

                        location=object.getString("location");
                        associated=object.getString("Associatedwith");
                        department=object.getString("Department");
                        experiance=object.getString("Experience");
                        specialization=object.getString("Specialization");
                        mailid=object.getString("Email");
                        contactno=object.getString("Contactno");
                        detailedabout=object.getString("Detailedabout");

                    }

                    doctornametextview.setText(doctorname);
                    locationtextview.setText(location);
                    Asoociatedwithtextview.setText(associated);
                    departmenttextview.setText(department);
                    departmentup.setText(department);

                }

            });}


    }
}