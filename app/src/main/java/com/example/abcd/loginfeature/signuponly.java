package com.example.abcd.loginfeature;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abcd.Listresult;
import com.example.abcd.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signuponly extends AppCompatActivity {

    EditText usernameEditText1;
    EditText passwordeditText1;
    EditText emailedittext;



    AlertDialog.Builder builder1;

    public void loginonly(View view){
        Intent intent = new Intent(getApplicationContext(),loginsignup.class);
        startActivity(intent);
    }

    public  void signuponly(View view){

        Log.i("button clicked","yessssssssssssssssssssssss");

        ParseUser user = new ParseUser();
        user.setUsername(usernameEditText1.getText().toString());
        user.setPassword(passwordeditText1.getText().toString());
        user.setEmail(emailedittext.getText().toString());


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Info", "user signed up");
                    redirectIfLoggedIn();
                } else {
                    String message = e.getMessage();
                    if(message.toLowerCase().contains("java")){
                        message = e.getMessage().substring(e.getMessage().indexOf(" "));
                    }

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                    Toast.makeText(signuponly.this,"This use ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



//    public AlertDialog.Builder getDialogProgressBar() {
//
//        if (builder == null) {
//            builder = new AlertDialog.Builder(this);
//
//            builder.setTitle("This Username or Email Id already exists!" +
//                    "( try login )");
//
//            final ProgressBar progressBar = new ProgressBar(this);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT);
//            progressBar.setLayoutParams(lp);
//            builder.setView(progressBar);
//        }
//        return builder;
//    }

    public  void redirectIfLoggedIn(){
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Listresult.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuponly);


        builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("This Username or Email Id already exist!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        usernameEditText1=findViewById(R.id.useredittext1);
        passwordeditText1=findViewById(R.id.passwordtextview1);
        emailedittext=findViewById(R.id.emailedittext);

    }
}