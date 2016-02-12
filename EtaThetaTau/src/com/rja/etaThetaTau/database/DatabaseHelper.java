package com.rja.etaThetaTau.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rja.etaThetaTau.objects.HotFeedItem;

import java.util.ArrayList;

/**
 * Created by rjaylward on 9/22/15
 */
public class DatabaseHelper {

    private ContentResolver mContentResolver;
    public static final int DATABASE_VERSION = 1;
    DatabaseProvider.OpenDatabaseHelper mOpenDatabaseHelper;
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        mContentResolver = context.getApplicationContext().getContentResolver();
        mOpenDatabaseHelper = new DatabaseProvider.OpenDatabaseHelper(context);
    }

    public static DatabaseHelper getInstance(Context context) {
        if(databaseHelper == null)
            databaseHelper = new DatabaseHelper(context.getApplicationContext());

        return databaseHelper;
    }

    public synchronized void addHotFeedItem(HotFeedItem hotFeedItem) {
        mContentResolver.insert(Table.HotFeedItems.CONTENT_URI, hotFeedItem.toContentValues());
    }

    public synchronized void addHotFeedItems(ArrayList<HotFeedItem> hotFeedItems) {
        ArrayList<ContentValues> valuesArray = new ArrayList<>();

        for(HotFeedItem hotFeedItem : hotFeedItems) {
            valuesArray.add(hotFeedItem.toContentValues());
        }

        mContentResolver.bulkInsert(Table.HotFeedItems.CONTENT_URI, valuesArray.toArray(new ContentValues[hotFeedItems.size()]));
    }

    public synchronized ArrayList<HotFeedItem> getAllHotFeedItems() {
        ArrayList<HotFeedItem> hotFeedItems = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Table.HotFeedItems.TABLE_NAME; /*+ " ORDER BY " + Table.HotFeedItems.CREATED_AT + " DESC"*/
        SQLiteDatabase db = mOpenDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    HotFeedItem hotFeedItem = new HotFeedItem().fromCursor(cursor);

                    if(hotFeedItem != null)

                        hotFeedItems.add(hotFeedItem);
                } while (cursor.moveToNext());
            }
        } finally {
            if(cursor != null)
                cursor.close();
        }

        return hotFeedItems;
    }

    public synchronized void updateHotFeedItem(HotFeedItem hotFeedItem) {
        mContentResolver.update(Table.HotFeedItems.CONTENT_URI, hotFeedItem.toContentValues(), Table.HotFeedItems.ID + " = '" + hotFeedItem.getId() + "'", null);
    }

    public synchronized void deleteHotFeedItem(String id) {
        mContentResolver.delete(Table.HotFeedItems.CONTENT_URI, Table.HotFeedItems.ID + " == '" + id + "'", null);
    }
}