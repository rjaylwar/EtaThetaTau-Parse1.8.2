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
public class Snacks implements Parcelable {

    @SerializedName(FieldNames.SALTY)
    private String mSalty;

    @SerializedName(FieldNames.SWEET)
    private String mSweet;

    @SerializedName(FieldNames.DRINKS)
    private String mDrinks;

    public Snacks() {}

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

    public static Snacks fromCursor(Cursor cursor) {
        Snacks location = new Snacks();

        location.setSalty(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_SALTY)));
        location.setSweet(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_SWEET)));
        location.setDrinks(cursor.getString(cursor.getColumnIndexOrThrow(Table.HotFeedItems.SNACKS_DRINKS)));

        return location;
    }

    protected Snacks(Parcel in) {
        mSalty = in.readString();
        mSweet = in.readString();
        mDrinks = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSalty);
        dest.writeString(mSweet);
        dest.writeString(mDrinks);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Snacks> CREATOR = new Parcelable.Creator<Snacks>() {
        @Override
        public Snacks createFromParcel(Parcel in) {
            return new Snacks(in);
        }

        @Override
        public Snacks[] newArray(int size) {
            return new Snacks[size];
        }
    };
}
