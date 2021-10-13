package com.example.abcd.loginfeature;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class server extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("y9XdiCeLXh5spngAYxTBsUYkuIDqvU9r2FvQ4ygV")
                // if defined
                .clientKey("Rgk4NtTkJdnIIcedJxpUTIx7HiMYlsTyzlqswkmK")
                .server("https://parseapi.back4app.com/")
                .build()
        );

//
//    ParseObject object = new ParseObject("ExampleObject");
//    object.put("myNumber", "123");
//    object.put("myString", "rob");
//
//    object.saveInBackground(new SaveCallback() {
//      @Override
//      public void done(ParseException ex) {
//        if (ex == null) {
//          Log.i("Parse Result", "Successful!");
//        } else {
//          Log.i("Parse Result", "Failed" + ex.toString());
//        }
//      }
//    });

}
}