package com.rja.etaThetaTau;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.rja.etaThetaTau.service.AudioQueue;
import com.rja.etaThetaTau.values.Preferences;

public class ParseApplication extends MultiDexApplication {

  @Override
  public void onCreate() {
    super.onCreate();


    // Add your initialization code here
    Parse.initialize(this, "vZKXFYYVYzIkptBQ4cgCHQo6abYAXS9ip7YDVhBo", "uC71mF9Bugtxll23PQRLOhLmOVzr7S9I0nYf8fV6");

      ParseInstallation.getCurrentInstallation().saveInBackground();

      ParsePush.subscribeInBackground("android", new SaveCallback() {

          @Override
          public void done(ParseException e) {

              if(e == null) {
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

      Preferences.initialize(getApplicationContext());
      AudioQueue.initialize();

  }
}
