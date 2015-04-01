package com.rja.etaThetaTau;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ListView;


public class SongsDetailActivity extends ListActivity {

    SongsArrayAdapter songsArrayAdapt;
    Boolean webViewIsUnloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_detail);

        Integer lengthOfArray = this.getIntent().getStringArrayExtra("videoArray").length;

        String[] nameArray = new String[lengthOfArray/2];
        String[] videoArray = new String[lengthOfArray];
        String[] videoUrlArray = new String[lengthOfArray/2];
        String[] descriptionArray = new String [lengthOfArray/2];
        videoArray = this.getIntent().getStringArrayExtra("videoArray");
        nameArray = this.getIntent().getStringArrayExtra("nameArray");

        //String audioUrl = this.getIntent().getStringExtra("audioUrl");
        //String lyrics = this.getIntent().getStringExtra("lyrics");

        String[][] cellDoubleArray = new String[lengthOfArray/2][3];

        for (int i=0; i<(lengthOfArray/2); i++) {
            cellDoubleArray[i][0] = nameArray[i];
            cellDoubleArray[i][1] = videoArray[2*i];
            cellDoubleArray[i][2] = videoArray[2*i+1];
        }

        songsArrayAdapt = new SongsArrayAdapter(this, cellDoubleArray);
        setListAdapter(songsArrayAdapt);
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_songs_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //recursiveLoopChildren(getListView());
        //formIsValid(getListView());
        //songsArrayAdapt.clear();

        //super.onBackPressed();
        //songsArrayAdapt.notifyDataSetChanged();
        //webViewIsUnloaded = true;

        onNavigateUp();
    }

    @Override
    public boolean onNavigateUp() {
        songsArrayAdapt.notifyDataSetChanged();
        webViewIsUnloaded = true;
        //WebView wv = (WebView) getListView().findViewById(R.id.choreo_web_view);
        //wv.loadUrl("about:blank");
        return super.onNavigateUp();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!webViewIsUnloaded) {
            songsArrayAdapt.notifyDataSetChanged();
            webViewIsUnloaded = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webViewIsUnloaded) {
            songsArrayAdapt.notifyDataSetChanged();
            webViewIsUnloaded = false;
        }
    }

    public void recursiveLoopChildren(ViewGroup parent) {
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursiveLoopChildren((ViewGroup) child);
                // DO SOMETHING WITH VIEWGROUP, AFTER CHILDREN HAS BEEN LOOPED
            } else {
                if (child != null) {
                    if (child instanceof WebView) {
                        ((WebView) child).loadUrl("about:blank");
                        Log.d("load","1 CLEARING VIEW");
                    }
                }
            }
        }
    }

    public boolean formIsValid(ListView layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if (v instanceof EditText) {
                //validate your EditText here
            } else if (v instanceof WebView) {
                ((WebView) v).loadUrl("about:blank");
                Log.d("load","2 CLEARING VIEW");
            } //etc. If it fails anywhere, just return false.
        }
        return true;
    }
}
