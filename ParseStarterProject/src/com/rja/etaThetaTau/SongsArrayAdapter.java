package com.rja.etaThetaTau;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by rjaylward on 3/4/15.
 */
public class SongsArrayAdapter extends ArrayAdapter<String[]> {
    private final Activity context;
    private final String[][] stringsForRow;
    private boolean unloadWebview = false;
    private boolean hasLoaded = false;

    static class ViewHolder {
        public WebView choreoVideoWebView;
        public TextView instructionsTextView;
        public TextView titleView;
    }

    public SongsArrayAdapter(Activity context, String[][] stringsForRow) {
        super(context, R.layout.songs_video_cell, stringsForRow);
        this.context = context;
        this.stringsForRow = stringsForRow;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder = null;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.songs_video_cell, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.choreoVideoWebView = (WebView) rowView.findViewById(R.id.choreo_web_view);
            viewHolder.instructionsTextView = (TextView) rowView.findViewById(R.id.instructions_text_view);
            viewHolder.titleView = (TextView) rowView.findViewById(R.id.choreo_cell_title);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String title = stringsForRow[position][0];
        String choreoUrl = stringsForRow[position][1];
        String instuctString = stringsForRow[position][2];

        holder.titleView.setText(title);
        holder.instructionsTextView.setText(instuctString);

        WebSettings settings = holder.choreoVideoWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT < 19) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
        holder.choreoVideoWebView.setWebChromeClient(new WebChromeClient());
        /*holder.choreoVideoWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //error
            }

        });*/
        if (unloadWebview) {
            holder.choreoVideoWebView.loadUrl("about:blank");
        } else {
        holder.choreoVideoWebView.loadUrl(choreoUrl);}
        //holder.choreoVideoWebView.setTag("webview" + position);
        return rowView;
    }

    @Override
    public void notifyDataSetChanged() {
        if (hasLoaded) {
        unloadWebview = false;
        hasLoaded = false;} else {
            unloadWebview = true;
            hasLoaded = true;
        }

        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public void clear() {
        super.clear();

    }

}