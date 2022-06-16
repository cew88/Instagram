package com.example.instagram;

import android.app.Application;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ParseApplication extends Application {
    public static final String TAG = "ParseApplication";

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FBXRxYhelb8NmeD2A2l6TjLdSngXf7z9kxPioI5J")
                .clientKey("bn6X0uBR9b5ZwKUMYeP6JHIUiXNuNO4UITSCLpvw")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
