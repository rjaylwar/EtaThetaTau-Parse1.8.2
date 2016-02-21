package com.rja.etaThetaTau.objects;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.database.Table;
import com.rja.etaThetaTau.util.Util;
import com.rja.etaThetaTau.values.FieldNames;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rjaylward on 2/7/16
 */
public class HotFeedItem implements Parcelable {

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

    public HotFeedItem() {}

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

    public static HotFeedItem fromCursor(Cursor cursor) {
        HotFeedItem hotFeedItem = new HotFeedItem();

        try {
            hotFeedItem.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.DATE)));
            hotFeedItem.setImages(new ArrayList<String>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.IMAGES)).split(", "))));
            hotFeedItem.setLink(Link.fromCursor(cursor));
            hotFeedItem.setLocation(Location.fromCursor(cursor));
            hotFeedItem.setMainImage(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.MAIN_IMAGE)));
            hotFeedItem.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.MESSAGE)));
            hotFeedItem.setReminder(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.REMINDER)));
            hotFeedItem.setSnacks(Snacks.fromCursor(cursor));
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

    protected HotFeedItem(Parcel in) {
        mId = in.readString();
        mDate = in.readString();
        if (in.readByte() == 0x01) {
            mImages = new ArrayList<String>();
            in.readList(mImages, String.class.getClassLoader());
        } else {
            mImages = null;
        }
        mLink = (Link) in.readValue(Link.class.getClassLoader());
        mLocation = (Location) in.readValue(Location.class.getClassLoader());
        mMainImage = in.readString();
        mMessage = in.readString();
        mReminder = in.readString();
        mSnacks = (Snacks) in.readValue(Snacks.class.getClassLoader());
        mTag = in.readInt();
        mTheme = in.readString();
        mTitle = in.readString();
        mType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mDate);
        if (mImages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mImages);
        }
        dest.writeValue(mLink);
        dest.writeValue(mLocation);
        dest.writeString(mMainImage);
        dest.writeString(mMessage);
        dest.writeString(mReminder);
        dest.writeValue(mSnacks);
        dest.writeInt(mTag);
        dest.writeString(mTheme);
        dest.writeString(mTitle);
        dest.writeString(mType);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HotFeedItem> CREATOR = new Parcelable.Creator<HotFeedItem>() {
        @Override
        public HotFeedItem createFromParcel(Parcel in) {
            return new HotFeedItem(in);
        }

        @Override
        public HotFeedItem[] newArray(int size) {
            return new HotFeedItem[size];
        }
    };
}
