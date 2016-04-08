package com.rja.etaThetaTau.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.VolleyError;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.api.ApiHelper;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.objects.Script;
import com.rja.etaThetaTau.objects.ScriptsResponse;
import com.rja.etaThetaTau.volley.VolleyRequestListener;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/23/16
 */
public class ScriptsFragment extends BaseFragment {

    @Bind(R.id.fs_webView)
    WebView mWebView;

    ArrayList<Script> mScripts;

    private static final String MIME_TYPE = "text/html; charset=utf-8";
    private static final String ENCODING = "UTF-8";
    private static final String ERROR_HTML = "<html><body bgcolor=\"#0C4E76\"><font color=\"white\" face=\"Helvetica Neue Light\">" +
            "<p>Error, could not load. Make sure you have a network connection.</p></body></html>";

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_scripts;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {

    }

    @Override
    protected void initLayout(Bundle bundle) {
        setUpWebView();
        loadWebViewWithHtml(ERROR_HTML, mWebView);

        getScripts();
    }

    private void getScripts() {
        ApiHelper helper = new ApiHelper(this);
        helper.getScripts(new VolleyRequestListener<ScriptsResponse>() {

            @Override
            public void onResponse(ScriptsResponse response) {

                mScripts = response.getScripts();
                loadWebViewWithHtml(mScripts.get(0).getHtmlScript(), mWebView);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
    }

    private void setUpWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if(Build.VERSION.SDK_INT < 19) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
    }

    private void loadWebViewWithHtml(String scriptData, WebView webView) {
        webView.loadData(scriptData, MIME_TYPE, ENCODING);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.script_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.clear();

        for(int i = 0; i < mScripts.size(); i++) {
            menu.add(i, i, i, mScripts.get(i).getTag());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadWebViewWithHtml(mScripts.get(item.getGroupId()).getHtmlScript(), mWebView);
        return super.onOptionsItemSelected(item);
    }

}