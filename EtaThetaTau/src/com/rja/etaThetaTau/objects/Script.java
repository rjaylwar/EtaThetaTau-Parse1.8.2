package com.rja.etaThetaTau.objects;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.database.Table;
import com.rja.etaThetaTau.values.FieldNames;

/**
 * Created by rjaylward on 2/5/16
 */
public class Script {

    @SerializedName(FieldNames.Script.TITLE)
    private String mTitle;

    @SerializedName(FieldNames.Script.TAG)
    private String mTag;

    @SerializedName(FieldNames.Script.HTML_SCRIPT)
    private String mHtmlScript;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getHtmlScript() {
        return mHtmlScript;
    }

    public void setHtmlScript(String htmlScript) {
        mHtmlScript = htmlScript;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(Table.Scripts.TITLE, mTitle);
        cv.put(Table.Scripts.TITLE, mTag);
        cv.put(Table.Scripts.TITLE, mHtmlScript);

        return cv;
    }
}
