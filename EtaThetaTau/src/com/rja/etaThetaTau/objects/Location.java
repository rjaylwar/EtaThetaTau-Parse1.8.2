package com.rja.etaThetaTau.objects;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.database.Table;
import com.rja.etaThetaTau.values.FieldNames;

/**
 * Created by rjaylward on 2/20/16
 */
public class Location implements Parcelable {

    @SerializedName(FieldNames.TITLE)
    private String mTitle;

    @SerializedName(FieldNames.LATITUDE)
    private long mLatitude;

    @SerializedName(FieldNames.LONGITUDE)
    private long mLongitude;

    public Location() {}

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

    public static Location fromCursor(Cursor cursor) {
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

    protected Location(Parcel in) {
        mTitle = in.readString();
        mLatitude = in.readLong();
        mLongitude = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeLong(mLatitude);
        dest.writeLong(mLongitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
