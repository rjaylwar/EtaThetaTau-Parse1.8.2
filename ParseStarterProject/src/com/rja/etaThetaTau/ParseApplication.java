package com.rja.etaThetaTau;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    //Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "vZKXFYYVYzIkptBQ4cgCHQo6abYAXS9ip7YDVhBo", "uC71mF9Bugtxll23PQRLOhLmOVzr7S9I0nYf8fV6");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

      ParsePush.subscribeInBackground("android", new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if (e == null) {
                  Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
              } else {
                  Log.e("com.parse.push", "failed to subscribe for push", e);
              }
          }
      });

      ParsePush.subscribeInBackground("global", new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if (e == null) {
                  Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
              } else {
                  Log.e("com.parse.push", "failed to subscribe for push", e);
              }
          }
      });

  }
}
