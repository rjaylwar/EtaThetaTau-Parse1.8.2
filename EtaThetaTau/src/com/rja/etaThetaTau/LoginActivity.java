package com.rja.etaThetaTau;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParsePush;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LoginActivity extends Activity {

    TextView tv;
    long diff;
    long milliseconds;
    long endTime;
    ParseConfig config1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseConfig.getCurrentConfig() != null) {
        config1 = ParseConfig.getCurrentConfig();}

        ParseConfig.getInBackground(new ConfigCallback() {
            @Override
            public void done(ParseConfig config, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("TAG", "Yay! Config was fetched from the server.");
                    config1 = config;
                } else {
                    Log.e("TAG", "Failed to fetch. Using Cached Config.");
                    config1 = ParseConfig.getCurrentConfig();
                }


            }
        });

        tv = (TextView) findViewById(R.id.login_countdown_text_view);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        formatter.setLenient(false);


        String oldTime = "15.03.2016, 19:30";
        Date oldDate;
        try {
            oldDate = formatter.parse(oldTime);
            milliseconds = oldDate.getTime();

            //long startTime = System.currentTimeMillis();
            // do your work...
            long endTime=System.currentTimeMillis();

            diff = Math.abs(endTime-milliseconds);

            //Log.e("day", "miliday"+diff);
            long seconds = (long) (diff / 1000) % 60 ;
            //Log.e("secnd", "miliday"+seconds);
            long minutes = (long) ((diff / (1000*60)) % 60);
            //Log.e("minute", "miliday"+minutes);
            long hours   = (long) ((diff / (1000*60*60)) % 24);
            //Log.e("hour", "miliday"+hours);
            long days = (int)((diff / (1000*60*60*24)) % 365);
            //Log.e("days", "miliday"+days);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Long serverUptimeSeconds = Math.abs((milliseconds - System.currentTimeMillis()) / 1000);

        String serverUptimeText = String.format("%d days %d hours %d minutes %d seconds",
                serverUptimeSeconds / 86400,
                (serverUptimeSeconds % 86400) / 3600 ,
                ((serverUptimeSeconds % 86400) % 3600 ) / 60,
                ((serverUptimeSeconds % 86400) % 3600 ) % 60
        );


        Log.v("jjj", "miliday"+serverUptimeText);
        MyCount counter = new MyCount(milliseconds,1000);
        counter.start();

        final EditText editText = (EditText) findViewById(R.id.login_edit_text);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (editText.getText().toString().equals(config1.getString("password"))) {
                        Intent detailIntent = new Intent(getBaseContext(), TabsActivity.class);
                        detailIntent.putExtra("user", "hottie");
                        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("user", "hottie");
                        editor.commit();
                        startActivity(detailIntent);
                    } else if (editText.getText().toString().contains("+")) {
                        ParsePush.subscribeInBackground(editText.getText().toString().replace("+", ""));
                        editText.setText(R.string.successfully_subscribed);
                    } else if (editText.getText().toString().contains("-")) {
                        ParsePush.unsubscribeInBackground(editText.getText().toString().replace("-", ""));
                        editText.setText(R.string.sucessfully_unsubscribed);
                    } else {
                        editText.setText(R.string.incorrect_password);
                    }
                }
                return false;
            }
        });

        Button guestButton = (Button) findViewById(R.id.login_guest_button);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getBaseContext(), TabsActivity.class);
                detailIntent.putExtra("user", "guest");

                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("user","guest");
                editor.commit();

                startActivity(detailIntent);
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv.setText("done!");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            Long serverUptimeSeconds =
                    Math.abs((millisUntilFinished - System.currentTimeMillis()) / 1000);

            String serverUptimeText =
                    String.format("%dd:%dh:%dm:%ds",
                            serverUptimeSeconds / 86400,
                            ( serverUptimeSeconds % 86400) / 3600 ,
                            ((serverUptimeSeconds % 86400) % 3600 ) / 60,
                            ((serverUptimeSeconds % 86400) % 3600 ) % 60
                    );

            tv.setText(serverUptimeText);
        }
    }
}
