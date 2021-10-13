package com.example.abcd.loginfeature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abcd.Listresult;
import com.example.abcd.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class loginsignup extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

//    Boolean loginmodeactive = false;

    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    private static final int RC_SIGN_IN = 1;

    String s1,s2,s4;
    static String uniqueuserid;



    ParseObject entity = new ParseObject("Clinicsbooked");

    public  void redirectIfLoggedIn(){
        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), Listresult.class);
            intent.putExtra("identity",uniqueuserid);
            startActivity(intent);
        }
    }

    public void login(View view){

        Intent intent=new Intent(getApplicationContext(), signuponly.class);
        startActivity(intent);

    }

    public  void signup(View view){
        EditText usernameEditText = findViewById(R.id.useredittext1);
        EditText passwordeditText = findViewById(R.id.passwordtextview1);

//        if(loginmodeactive){
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordeditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Log.i("Info","User logged in");
                        redirectIfLoggedIn();
                    }else{
                        String message = e.getMessage();
                        if(message.toLowerCase().contains("java")){
                            message = e.getMessage().substring(e.getMessage().indexOf(" "));
                        }
                        Toast.makeText(loginsignup.this,message,Toast.LENGTH_LONG).show();
                    }
                }
            });


        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsignup);

        setTitle("Whatsapp Login");
//        redirectIfLoggedIn();

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(final GoogleSignInResult result){
        if(result.isSuccess()){



//            try{
//                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
//            }catch (NullPointerException e){
//                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
//            }

            new CountDownTimer(4000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                    GoogleSignInAccount account=result.getSignInAccount();
                    s1=account.getDisplayName();
                    s2=account.getEmail();
                    uniqueuserid=account.getId();

                }

                @Override
                public void onFinish() {

                    gotoProfile();

                }
            }.start();


        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile(){


        ParseUser user = new ParseUser();
        user.setUsername(s1);
        user.setEmail(s2);
        user.setPassword(uniqueuserid);

        Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",s1);
        Log.i("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",s2);

//        redirectIfLoggedIn();

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    redirectIfLoggedIn();
                } else {

                    //Login situation
                    ParseUser.logInInBackground(s1, uniqueuserid, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if(user !=null){
                                Log.i("Login","ok");

                                Log.i("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",s1);
                                Log.i("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",s2);

                                showuserlist();
                            }else {
                                Toast.makeText(loginsignup.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                    String message = e.getMessage();
                    if(message.toLowerCase().contains("java")){
                        message = e.getMessage().substring(e.getMessage().indexOf(" "));
                    }
                    Toast.makeText(loginsignup.this,message,Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void showuserlist(){
        Intent intent = new Intent(getApplicationContext(),Listresult.class);
        intent.putExtra("identity",uniqueuserid);
        startActivity(intent);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}