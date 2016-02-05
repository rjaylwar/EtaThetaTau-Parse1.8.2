package com.rja.etaThetaTau;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;


public class TabsActivity extends FragmentActivity {

    Boolean loadLyrics;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();


        setContentView(R.layout.activity_tabs);
        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        userType = settings.getString("user", "");

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                TabsActivity.this));
        viewPager.setOffscreenPageLimit(2);
        // Give the SlidingTabLayout the ViewPager
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        // Center the tabs in the layout
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);

    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.script_menu, menu);

        final MenuItem mySwitch = menu.findItem(R.id.my_switch);
        final Switch actionView = (Switch) mySwitch.getActionView();

        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (loadLyrics) {
                    loadLyrics = false;
                } else {
                    loadLyrics = true;
                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
//            case R.id.action_settings:
//                //openSettings();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void openSearch(){

    }

    public void dismissActivity(View v) {
        this.finish();
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}