package com.rja.etaThetaTau;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;

public class ScriptFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private WebView webView;
    private String script;
    private String script2012 = "<html><body><p>Error, 2012 script did not load</p></body></html>";
    private String script2013 = "<html><body><p>Error, 2013 script did not load</p></body></html>";
    private String script2014 = "<html><body><p>Error, 2014 script did not load</p></body></html>";
    private ParseConfig config1;
    private String userType;

    public static ScriptFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ScriptFragment fragment = new ScriptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
        SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", 0);
        userType = settings.getString("user", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_script, container, false);
        webView = (WebView)view.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        loadWebViewWithHtml("<html><body bgcolor=\"#0C4E76\"><font color=\"white\" face=\"Helvetica Neue Light\"><p>Error, could not load. Make sure you have a network connection.</p></body></html>");

        getParseConfig();

        final LinearLayout selectYearLayout = (LinearLayout) view.findViewById(R.id.tonight_years_layout);
        selectYearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectYearLayout.setVisibility(View.GONE);
                return false;
            }
        });

        final Button selectYear = (Button) view.findViewById(R.id.script_year_button);
        selectYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.tonight_years_layout).setVisibility(View.VISIBLE);
            }
        });
        Button select2012 = (Button) view.findViewById(R.id.script_2012_button);
        select2012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.tonight_years_layout).setVisibility(View.GONE);

                loadWebViewWithHtml(script2012);
                selectYear.setText("2012");
            }
        });
        Button select2013 = (Button) view.findViewById(R.id.script_2013_button);
        select2013.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.tonight_years_layout).setVisibility(View.GONE);

                loadWebViewWithHtml(script2013);
                selectYear.setText("2013");
            }
        });
        Button select2014 = (Button) view.findViewById(R.id.script_2014_button);
        select2014.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.findViewById(R.id.tonight_years_layout).setVisibility(View.GONE);

                loadWebViewWithHtml(script2014);
                selectYear.setText("2014");
            }
        });
        Button select2015 = (Button) view.findViewById(R.id.script_2015_button);
        select2015.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.findViewById(R.id.tonight_years_layout).setVisibility(View.GONE);

                loadWebViewWithHtml(script);
                selectYear.setText("2015");
            }
        });

        return view;
    }

    private void getParseConfig() {
        Log.d("TAG", "Getting the latest config...");
        ParseConfig.getInBackground(new ConfigCallback() {
            @Override
            public void done(ParseConfig config, ParseException e) {
                if (e == null) {
                    Log.d("TAG", "Yay! Config was fetched from the server.");
                } else {
                    Log.e("TAG", "Failed to fetch. Using Cached Config.");
                    config = ParseConfig.getCurrentConfig();
                }

                config1 = config;

                // Get the message from config or fallback to default value
                if (userType.equals("guest")){
                    script = config1.getString("guestScript");
                }
                else {
                    script = config1.getString("script");
                }
                script2012 = config1.getString("script2012");
                script2013 = config1.getString("script2013");
                script2014 = config1.getString("script2014");

                setUpWebView();
                loadWebViewWithHtml(script);
            }
        });
    }

    private void setUpWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < 19) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
    }

    private void loadWebViewWithHtml(String scriptData) {
        //not sure if that is necessary
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL("", scriptData, "text/html; charset=utf-8", "UTF-8", "");
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    private class ScriptWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
