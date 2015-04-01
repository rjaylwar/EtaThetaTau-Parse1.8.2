package com.rja.etaThetaTau;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class LyricsDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_detail);

        String title = getIntent().getStringExtra("title");
        String audioUrl = getIntent().getStringExtra("audioUrl");
        String lyrics = getIntent().getStringExtra("lyrics");


        TextView lyricsTitleView = (TextView) findViewById(R.id.lyrics_title_textView);
        lyricsTitleView.setText(title);
        WebView webView = (WebView) findViewById(R.id.lyrics_webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < 19) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //error
            }
        });
        webView.loadDataWithBaseURL("", lyrics, "text/html; charset=utf-8", "UTF-8", "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lyrics_detail, menu);
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
        WebView webView = (WebView) findViewById(R.id.lyrics_webView);
        webView.loadDataWithBaseURL("", "<html><body bgcolor=\"#0C4E76\"></body></html>", "text/html; charset=utf-8", "UTF-8", "");
        super.onBackPressed();
    }

    @Override
    public boolean onNavigateUp() {
        WebView webView = (WebView) findViewById(R.id.lyrics_webView);
        webView.loadDataWithBaseURL("", "<html><body bgcolor=\"#0C4E76\"></body></html>", "text/html; charset=utf-8", "UTF-8", "");
        return super.onNavigateUp();
    }
}
