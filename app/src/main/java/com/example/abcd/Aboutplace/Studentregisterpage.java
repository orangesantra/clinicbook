package com.example.abcd.Aboutplace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.abcd.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Studentregisterpage extends AppCompatActivity {

    AlertDialog.Builder builder;
    AlertDialog progressDialog;

    EditText editText;
    EditText editText1;

    public static final int PICK_IMAGE = 1;

    String s1,s2,s3,s4,s5;
    String gender;

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.studentmale:
                if (checked) {

                    gender="male";

                }

                break;
            case R.id.studentfemale:
                if (checked) {
                    gender="female";

                }

                break;
            case R.id.studentother:
                if (checked){
                    gender="other";
                }
                break;
        }
    }

    public void studentprofilepicture(View view){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);

    }

    public void studentaddharcard(View view){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);

    }


    public void submitstudentregister(View view){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Confirm Submission !");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Condirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        progressDialog = getDialogProgressBar().create();
                        progressDialog.show();

                        new CountDownTimer(4000,1000){

                            @Override
                            public void onTick(long millisUntilFinished) {



                            }

                            @Override
                            public void onFinish() {

                                Intent intent = new Intent(getApplicationContext(),submitstudentpage.class);
                                intent.putExtra("name",editText.getText().toString());
                                intent.putExtra("contactno",editText1.getText().toString());
                                intent.putExtra("emailaddress",s3);
                                intent.putExtra("collegename",s4);
                                intent.putExtra("graduationyear",s5);
                                startActivity(intent);

                            }
                        }.start();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    //progressDialog.show() and progressDialog.dismiss()


    public AlertDialog.Builder getDialogProgressBar() {

        if (builder == null) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle("Processing...");

            final ProgressBar progressBar = new ProgressBar(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(lp);
            builder.setView(progressBar);
        }
        return builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregisterpage);

         editText = findViewById(R.id.studentname);
         editText1= findViewById(R.id.studentemailaddress);

        ParseUser user = new ParseUser();
        user.setUsername(editText.getText().toString());
        user.put("Studentname",editText.getText().toString());
        user.put("Contactno",editText1.getText().toString());
        user.put("EmailAddress",s3);
        user.put("Collegename",s4);
        user.put("Graduationyear",s4);
        user.put("Gender",gender);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    ParseUser.logOut();
                    Log.i("Info", "user signed up");

                } else {
                    String message = e.getMessage();
                    if(message.toLowerCase().contains("java")){
                        message = e.getMessage().substring(e.getMessage().indexOf(" "));
                    }

                    Toast.makeText(Studentregisterpage.this,"This use ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}