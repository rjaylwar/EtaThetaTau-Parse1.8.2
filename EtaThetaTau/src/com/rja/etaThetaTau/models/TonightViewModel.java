package com.rja.etaThetaTau.models;

import com.google.android.gms.maps.model.LatLng;
import com.rja.etaThetaTau.objects.HotFeedItem;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/12/16
 */
public interface TonightViewModel {

    HotFeedItem getHotFeedItem();
    String getMainImageUrl();

    String getTitle();
    String getMessage();

    String getReminders();
    String getTheme();

    ArrayList<String> getImageUrls();

    String getLocationDescription();
    LatLng getLatLong();

    String getActionTitle();
    String getActionImageUrl();
    String getActionUrl();
}