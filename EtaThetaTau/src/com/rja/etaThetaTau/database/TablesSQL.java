package com.rja.etaThetaTau.database;


/**
 * Created by rjaylward on 9/22/15
 */
public class TablesSQL {

//    public static final String CREATE_SONGS_TABLE = "CREATE TABLE " + FieldNames.SONGS + " ( " +
//            Table.Songs.OBJECT_ID + " TEXT PRIMARY KEY, " +
//            Table.Songs.TITLE + " TEXT, " +
//            Table.Songs.YOUTUBE_URL + " TEXT, " +
//            Table.Songs.SPOTIFY_URL + " TEXT, " +
//            Table.Songs.ITUNES_URL + " TEXT, " +
//            Table.Songs.CREATED_AT + " INTEGER " + ");";
//
//    public static final String CREATE_NOTIFICATIONS_TABLE = "CREATE TABLE " + FieldNames.NOTIFICATIONS + " ( " +
//            Table.Notifications.OBJECT_ID + " TEXT PRIMARY KEY, " +
//            Table.Notifications.ALERT + " TEXT, " +
//            Table.Notifications.CREATED_AT + " INTEGER " + ");";
//
//    public static final String CREATE_TUMBLR_TALKS_TABLE = "CREATE TABLE " + Table.TumblrTalks.TABLE_NAME + " ( " +
//            Table.TumblrTalks.OBJECT_ID + " INTEGER PRIMARY KEY," +
//            Table.TumblrTalks.TITLE + " TEXT, " +
//            Table.TumblrTalks.SUBTITLE + " TEXT, " +
//            Table.TumblrTalks.IMAGE_URL + " TEXT, " +
//            Table.TumblrTalks.AUDIO_URL + " TEXT, " +
//            Table.TumblrTalks.BASE_URL + " TEXT, " +
//            Table.TumblrTalks.DESCRIPTION + " TEXT, " +
//            Table.TumblrTalks.SOURCE + " TEXT, " +
//            Table.TumblrTalks.SERIES + " TEXT, " +
//            Table.TumblrTalks.ORIGINAL_LINK + " TEXT, " +
//            Table.TumblrTalks.SERIES_IMAGE_URL + " TEXT, " +
//            Table.TumblrTalks.TYPE + " TEXT, " +
//            Table.TumblrTalks.THUMBNAIL_URL + " TEXT, " +
//            Table.TumblrTalks.PERMALINK_URL + " TEXT, " +
//            Table.TumblrTalks.TAGS + " TEXT, " +
//            Table.TumblrTalks.TIMESTAMP + " INTEGER, " +
//            "UNIQUE (" + Table.TumblrTalks.OBJECT_ID + "," + Table.TumblrTalks.TITLE + ")" + ");";

    public static final String CREATE_SCRIPTS_TABLE = "CREATE TABLE " + Table.Scripts.TABLE_NAME + " ( " +
            Table.Scripts.OBJECT_ID + " TEXT PRIMARY KEY, " +
            Table.Scripts.TITLE + " TEXT, " +
            Table.Scripts.TAG + " TEXT, " +
            Table.Scripts.HTML_SCRIPT + " TEXT " + ");";

    public static final String CREATE_HOT_FEED_ITEMS_TABLE = "CREATE TABLE " + Table.HotFeedItems.TABLE_NAME + " ( " +
            Table.HotFeedItems.ID + " TEXT PRIMARY KEY," +
            Table.HotFeedItems.DATE + " TEXT," +
            Table.HotFeedItems.IMAGES + " TEXT," +
            Table.HotFeedItems.LINK_TITLE + " TEXT," +
            Table.HotFeedItems.LINK_URL + " TEXT," +
            Table.HotFeedItems.LINK_IMAGE + " TEXT," +
            Table.HotFeedItems.LOCATION_LATITUDE + " INTEGER," +
            Table.HotFeedItems.LOCATION_LONGITUDE + " INTEGER," +
            Table.HotFeedItems.LOCATION_TITLE + " TEXT," +
            Table.HotFeedItems.MAIN_IMAGE + " TEXT," +
            Table.HotFeedItems.MESSAGE + " TEXT," +
            Table.HotFeedItems.REMINDER + " TEXT," +
            Table.HotFeedItems.SNACKS_DRINKS + " TEXT," +
            Table.HotFeedItems.SNACKS_SALTY + " TEXT," +
            Table.HotFeedItems.SNACKS_SWEET + " TEXT," +
            Table.HotFeedItems.TAG + " INTEGER," +
            Table.HotFeedItems.THEME + " TEXT," +
            Table.HotFeedItems.TITLE + " TEXT," +
            Table.HotFeedItems.TYPE + " TEXT " + ");";

    public static final String[] TABLE_NAMES = {Table.Scripts.TABLE_NAME};

    public static String deleteTable(String tableName) {
        return "DROP TABLE IF EXISTS " + tableName;
    }
}

