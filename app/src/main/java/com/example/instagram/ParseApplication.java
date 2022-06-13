package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FBXRxYhelb8NmeD2A2l6TjLdSngXf7z9kxPioI5J")
                .clientKey("bn6X0uBR9b5ZwKUMYeP6JHIUiXNuNO4UITSCLpvw")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
