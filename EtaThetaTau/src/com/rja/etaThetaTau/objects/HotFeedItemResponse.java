package com.rja.etaThetaTau.objects;

import android.content.Context;

import com.rja.etaThetaTau.api.ApiResponse;
import com.rja.etaThetaTau.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/11/16
 */
public class HotFeedItemResponse implements ApiResponse {

    private ArrayList<HotFeedItem> mHotFeedItems;

    @Override
    public void saveResponse(Context context) {
        DatabaseHelper.getInstance(context).addHotFeedItems(mHotFeedItems);
    }

    public void setHotFeedItems(ArrayList<HotFeedItem> hotFeedItems) {
        mHotFeedItems = hotFeedItems;
    }

    public ArrayList<HotFeedItem> getHotFeedItems() {
        return mHotFeedItems;
    }
}
