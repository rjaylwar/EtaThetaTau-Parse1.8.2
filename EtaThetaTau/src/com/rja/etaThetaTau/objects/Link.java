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
public class Link implements Parcelable {

    @SerializedName(FieldNames.TITLE)
    private String mTitle;

    @SerializedName(FieldNames.URL)
    private String mUrl;

    @SerializedName(FieldNames.IMAGE)
    private String mImage;

    public Link() {}

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

    public static Link fromCursor(Cursor cursor) {
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

    protected Link(Parcel in) {
        mTitle = in.readString();
        mUrl = in.readString();
        mImage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mUrl);
        dest.writeString(mImage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Link> CREATOR = new Parcelable.Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
}