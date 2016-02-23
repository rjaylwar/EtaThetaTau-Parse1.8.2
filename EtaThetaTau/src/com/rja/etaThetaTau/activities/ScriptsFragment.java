package com.rja.etaThetaTau.activities;

import android.os.Build;
import android.os.Bundle;
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

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_scripts;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {

    }

    @Override
    protected void initLayout(Bundle bundle) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        loadWebViewWithHtml("<html><body bgcolor=\"#0C4E76\"><font color=\"white\" face=\"Helvetica Neue Light\"><p>Error, could not load. Make sure you have a network connection.</p></body></html>");

        getScripts();
    }

    private void getScripts() {
        ApiHelper helper = new ApiHelper(this);
        helper.getScripts(new VolleyRequestListener<ScriptsResponse>() {

            @Override
            public void onResponse(ScriptsResponse response) {
                mScripts = response.getScripts();
                loadWebViewWithHtml(mScripts.get(0).getHtmlScript());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
    }

    private void setUpWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < 19) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
    }

    private void loadWebViewWithHtml(String scriptData) {
        mWebView.loadData(scriptData, MIME_TYPE, ENCODING);
    }

}
