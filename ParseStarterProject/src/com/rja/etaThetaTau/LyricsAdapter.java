package com.rja.etaThetaTau;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by rjaylward on 3/4/15.
 */
public class LyricsAdapter extends ParseQueryAdapter<ParseObject> {

    Context context;

    public LyricsAdapter(Context context) {

        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("guest_songs");
                query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
                query.orderByDescending("updatedAt");
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.songs_cell, null);
        }
        super.getItemView(object, v, parent);

        TextView titleView = (TextView) v.findViewById(R.id.song_title);
        titleView.setText(object.getString("title"));

        TextView dateView = (TextView) v.findViewById(R.id.song_subtitle);
        dateView.setText(object.getString("subtitle"));

        return v;
    }
}
