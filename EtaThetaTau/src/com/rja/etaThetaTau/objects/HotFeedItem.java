package com.rja.etaThetaTau.objects;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.database.Table;
import com.rja.etaThetaTau.util.Util;
import com.rja.etaThetaTau.values.FieldNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rjaylward on 2/7/16
 */
public class HotFeedItem {

    private String mId;

    @SerializedName(FieldNames.DATE)
    private String mDate;

    @SerializedName(FieldNames.IMAGES)
    private ArrayList<String> mImages;

    @SerializedName(FieldNames.LINK)
    private Link mLink;

    @SerializedName(FieldNames.LOCATION)
    private Location mLocation;

    @SerializedName(FieldNames.MAIN_IMAGE)
    private String mMainImage;

    @SerializedName(FieldNames.MESSAGE)
    private String mMessage;

    @SerializedName(FieldNames.REMINDER)
    private String mReminder;

    @SerializedName(FieldNames.SNACKS)
    private Snacks mSnacks;

    @SerializedName(FieldNames.TAG)
    private int mTag;

    @SerializedName(FieldNames.THEME)
    private String mTheme;

    @SerializedName(FieldNames.TITLE)
    private String mTitle;

    @SerializedName(FieldNames.TYPE)
    private String mType;


    public void setDate(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public void setImages(ArrayList<String> images) {
        mImages = images;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void setLink(Link link) {
        mLink = link;
    }

    public Link getLink() {
        return mLink;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setMainImage(String mainImage) {
        mMainImage = mainImage;
    }

    public String getMainImage() {
        return mMainImage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setReminder(String reminder) {
        mReminder = reminder;
    }

    public String getReminder() {
        return mReminder;
    }

    public void setSnacks(Snacks snacks) {
        mSnacks = snacks;
    }

    public Snacks getSnacks() {
        return mSnacks;
    }

    public void setTag(int tag) {
        mTag = tag;
    }

    public int getTag() {
        return mTag;
    }

    public void setTheme(String theme) {
        mTheme = theme;
    }

    public String getTheme() {
        return mTheme;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(Table.HotFeedItems.ID, mId);
        values.put(Table.HotFeedItems.DATE, mDate);
        values.put(Table.HotFeedItems.IMAGES, Util.printArrayListAsString(mImages));
        values.put(Table.HotFeedItems.MAIN_IMAGE, mMainImage);
        values.put(Table.HotFeedItems.MESSAGE, mMessage);
        values.put(Table.HotFeedItems.REMINDER, mReminder);

        values.put(Table.HotFeedItems.TAG, mTag);
        values.put(Table.HotFeedItems.THEME, mTheme);
        values.put(Table.HotFeedItems.TITLE, mTitle);
        values.put(Table.HotFeedItems.TYPE, mType);

        if(mLink != null)
            values.putAll(mLink.toContentValues());
        if(mLocation != null)
            values.putAll(mLocation.toContentValues());
        if(mSnacks != null)
            values.putAll(mSnacks.toContentValues());

        return values;
    }

    public HotFeedItem fromCursor(Cursor cursor) {
        HotFeedItem hotFeedItem = new HotFeedItem();

        try {
            hotFeedItem.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.DATE)));
            hotFeedItem.setImages(new ArrayList<String>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.IMAGES)).split(", "))));
            hotFeedItem.setLink(new Link().fromCursor(cursor));
            hotFeedItem.setLocation(new Location().fromCursor(cursor));
            hotFeedItem.setMainImage(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.MAIN_IMAGE)));
            hotFeedItem.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.MESSAGE)));
            hotFeedItem.setReminder(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.REMINDER)));
            hotFeedItem.setSnacks(new Snacks().fromCursor(cursor));
            hotFeedItem.setTag(cursor.getInt(cursor.getColumnIndexOrThrow(Table.HotFeedItems.TAG)));
            hotFeedItem.setTheme(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.THEME)));
            hotFeedItem.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.TITLE)));
            hotFeedItem.setType(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.TYPE)));
        } catch(Exception e) {
            hotFeedItem = null;
        }
        return hotFeedItem;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public class Link {

        @SerializedName(FieldNames.TITLE)
        private String mTitle;

        @SerializedName(FieldNames.URL)
        private String mUrl;

        @SerializedName(FieldNames.IMAGE)
        private String mImage;

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            mImage = image;
        }

        public ContentValues toContentValues() {
            ContentValues values = new ContentValues();

            values.put(Table.HotFeedItems.LINK_TITLE, mTitle);
            values.put(Table.HotFeedItems.LINK_URL, mUrl);
            values.put(Table.HotFeedItems.LINK_IMAGE, mImage);

            return values;
        }

        public Link fromCursor(Cursor cursor) {
            Link link = new Link();

            try {
                link.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LINK_TITLE)));
                link.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LINK_URL)));
                link.setImage(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LINK_IMAGE)));
            }
            catch (Exception e) {
                link = null;
            }

            return link;
        }
    }

    public class Location {

        @SerializedName(FieldNames.TITLE)
        private String mTitle;

        @SerializedName(FieldNames.LATITUDE)
        private long mLatitude;

        @SerializedName(FieldNames.LONGITUDE)
        private long mLongitude;

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public long getLatitude() {
            return mLatitude;
        }

        public void setLatitude(long latitude) {
            mLatitude = latitude;
        }

        public long getLongitude() {
            return mLongitude;
        }

        public void setLongitude(long longitude) {
            mLongitude = longitude;
        }

        public ContentValues toContentValues() {
            ContentValues values = new ContentValues();

            values.put(Table.HotFeedItems.LOCATION_TITLE, mTitle);
            values.put(Table.HotFeedItems.LOCATION_LATITUDE, mLatitude);
            values.put(Table.HotFeedItems.LOCATION_LONGITUDE, mLongitude);

            return values;
        }

        public Location fromCursor(Cursor cursor) {
            Location location = new Location();

            try {
                location.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LOCATION_TITLE)));
                location.setLatitude(cursor.getLong(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LOCATION_LATITUDE)));
                location.setLongitude(cursor.getLong(cursor.getColumnIndexOrThrow(Table.HotFeedItems.LOCATION_LONGITUDE)));
            } catch (Exception e) {
                location = null;
            }

            return location;
        }
    }

    public class Snacks {

        @SerializedName(FieldNames.SALTY)
        private String mSalty;

        @SerializedName(FieldNames.SWEET)
        private String mSweet;

        @SerializedName(FieldNames.DRINKS)
        private String mDrinks;

        public String getSalty() {
            return mSalty;
        }

        public void setSalty(String salty) {
            mSalty = salty;
        }

        public String getSweet() {
            return mSweet;
        }

        public void setSweet(String sweet) {
            mSweet = sweet;
        }

        public String getDrinks() {
            return mDrinks;
        }

        public void setDrinks(String drinks) {
            mDrinks = drinks;
        }

        public ContentValues toContentValues() {
            ContentValues values = new ContentValues();

            values.put(Table.HotFeedItems.SNACKS_SALTY, mSalty);
            values.put(Table.HotFeedItems.SNACKS_SWEET, mSweet);
            values.put(Table.HotFeedItems.SNACKS_DRINKS, mDrinks);

            return values;
        }

        public Snacks fromCursor(Cursor cursor) {
            Snacks location = new Snacks();

            location.setSalty(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_SALTY)));
            location.setSweet(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_SWEET)));
            location.setDrinks(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_DRINKS)));

            return location;
        }
    }

}
