package com.example.abcd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserId extends AppCompatActivity {

    EditText editText;
    String s1;



    public  void  saveid(View view){

        if(s1!=null) {

            s1 = editText.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Listresult.class);
            intent.putExtra("uniqueUserId", s1);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Please set a Id first!", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_id);

        editText=findViewById(R.id.useridedittext);

    }
}