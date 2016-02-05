package com.rja.etaThetaTau.database;

import android.net.Uri;

/**
 * Created by rjaylward on 9/22/15
 */
public class Table {

    public static final String RAW = "raw";
    public static final Uri RAW_QUERY = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + RAW);
    public static final String ORDER_KEY = "order_key";

    public static final class Requests {
        public static final String TABLE_NAME = "requests";
        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;

    }

    public static final class Notifications {
        public static final String TABLE_NAME = "notifications";
        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;

//        public static final String OBJECT_ID = FieldNames.OBJECT_ID;
//        public static final String ALERT = FieldNames.ALERT;
//        public static final String CREATED_AT = FieldNames.CREATED_AT;
    }

    public static final class Songs {
        public static final String TABLE_NAME = "songs";
        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;

//        public static final String TITLE = FieldNames.TITLE;
//        public static final String OBJECT_ID = FieldNames.OBJECT_ID;
//        public static final String YOUTUBE_URL = FieldNames.YOUTUBE_URL;
//        public static final String SPOTIFY_URL = FieldNames.SPOTIFY_URL;
//        public static final String ITUNES_URL = FieldNames.ITUNES_URL;
//        public static final String CREATED_AT = FieldNames.CREATED_AT;
    }

}

