package com.rja.etaThetaTau.database;

import android.net.Uri;

import com.rja.etaThetaTau.values.FieldNames;

/**
 * Created by rjaylward on 9/22/15
 */
public class Table {

    public static final String RAW = "raw";
    public static final Uri RAW_QUERY = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + RAW);
    public static final String ORDER_KEY = "order_key";

    public static final class Scripts {
        public static final String TABLE_NAME = "scripts";
        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;

        public static final String OBJECT_ID = FieldNames.OBJECT_ID;
        public static final String TITLE = FieldNames.Script.TITLE;
        public static final String TAG = FieldNames.Script.TAG;
        public static final String HTML_SCRIPT = FieldNames.Script.HTML_SCRIPT;
    }


    public static final class HotFeedItems {
        public static final String TABLE_NAME = "hot_feed_items";
        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;

        public static final String ID = FieldNames.ID;
        public static final String DATE = FieldNames.DATE;
        public static final String IMAGES = FieldNames.IMAGES;
        public static final String LINK_TITLE = FieldNames.LINK_TITLE;
        public static final String LINK_URL = FieldNames.LINK_URL;
        public static final String LINK_IMAGE = FieldNames.LINK_IMAGE;
        public static final String LOCATION_LATITUDE = FieldNames.LOCATION_LATITUDE;
        public static final String LOCATION_LONGITUDE = FieldNames.LOCATION_LONGITUDE;
        public static final String LOCATION_TITLE = FieldNames.LOCATION_TITLE;
        public static final String MAIN_IMAGE = FieldNames.MAIN_IMAGE;
        public static final String MESSAGE = FieldNames.MESSAGE;
        public static final String REMINDER = FieldNames.REMINDER;
        public static final String SNACKS_DRINKS = FieldNames.SNACKS_DRINKS;
        public static final String SNACKS_SALTY = FieldNames.SNACKS_SALTY;
        public static final String SNACKS_SWEET = FieldNames.SNACKS_SWEET;
        public static final String TAG = FieldNames.TAG;
        public static final String THEME = FieldNames.THEME;
        public static final String TITLE = FieldNames.TITLE;
        public static final String TYPE = FieldNames.TYPE;
    }
}

