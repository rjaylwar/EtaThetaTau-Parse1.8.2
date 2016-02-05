package com.rja.etaThetaTau.objects;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.values.FieldNames;

import java.util.ArrayList;

/**
 * Created by rjaylward on 1/16/16
 */
public class Tonight {

    @SerializedName(FieldNames.DESCRIPTION)
    private String mDescription;

    @SerializedName(FieldNames.DESCRIPTION_ARRAY)
    private ArrayList<String> mDescriptionArray;

    @SerializedName(FieldNames.DRINKS)
    private String mDrinks;

    @SerializedName(FieldNames.GUEST_DESCRIPTION_ARRAY)
    private ArrayList<String> mGuestDescArray;

    @SerializedName(FieldNames.GUEST_DESCRIPTION)
    private ArrayList<String> mGuestDesc;

    @SerializedName(FieldNames.HEADING)
    private String mHeading;

    @SerializedName(FieldNames.HOTD_NAME)
    private String mHotdName;

    @SerializedName(FieldNames.HOTD_IMAGE_URL)
    private String mHotdImageUrl;

    @SerializedName(FieldNames.IMAGE_URL)
    private String mImageUrl;

    @SerializedName(FieldNames.IMAGE_WAS_CLICKED_URL)
    private String mImageClickUrl;

    @SerializedName(FieldNames.IS_HTML)
    private boolean mIsHtml;

    @SerializedName(FieldNames.SALTY_SNACK)
    private String mSaltySnack;

    @SerializedName(FieldNames.SWEET_SNACK)
    private String mSweetSnack;

    @SerializedName(FieldNames.HOTD_DESCRIPTION)
    private String mHotdDescription;

    @SerializedName(FieldNames.HOTD_DESCRIPTION_ARRAY)
    private ArrayList<String> mHotdDescriptionArray;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public ArrayList<String> getGuestDesc() {
        return mGuestDesc;
    }

    public void setGuestDesc(ArrayList<String> guestDesc) {
        mGuestDesc = guestDesc;
    }

    public String getHotdDescription() {
        return mHotdDescription;
    }

    public void setHotdDescription(String hotdDescription) {
        mHotdDescription = hotdDescription;
    }

    public ArrayList<String> getHotdDescriptionArray() {
        return mHotdDescriptionArray;
    }

    public void setHotdDescriptionArray(ArrayList<String> hotdDescriptionArray) {
        mHotdDescriptionArray = hotdDescriptionArray;
    }

    public ArrayList<String> getDescriptionArray() {
        return mDescriptionArray;
    }

    public void setDescriptionArray(ArrayList<String> descriptionArray) {
        mDescriptionArray = descriptionArray;
    }

    public String getDrinks() {
        return mDrinks;
    }

    public void setDrinks(String drinks) {
        mDrinks = drinks;
    }

    public ArrayList<String> getGuestDescArray() {
        return mGuestDescArray;
    }

    public void setGuestDescArray(ArrayList<String> guestDescArray) {
        mGuestDescArray = guestDescArray;
    }

    public String getHeading() {
        return mHeading;
    }

    public void setHeading(String heading) {
        mHeading = heading;
    }

    public String getHotdName() {
        return mHotdName;
    }

    public void setHotdName(String hotdName) {
        mHotdName = hotdName;
    }

    public String getHotdImageUrl() {
        return mHotdImageUrl;
    }

    public void setHotdImageUrl(String hotdImageUrl) {
        mHotdImageUrl = hotdImageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageClickUrl() {
        return mImageClickUrl;
    }

    public void setImageClickUrl(String imageClickUrl) {
        mImageClickUrl = imageClickUrl;
    }

    public boolean isHtml() {
        return mIsHtml;
    }

    public void setIsHtml(boolean isHtml) {
        mIsHtml = isHtml;
    }

    public String getSaltySnack() {
        return mSaltySnack;
    }

    public void setSaltySnack(String saltySnack) {
        mSaltySnack = saltySnack;
    }

    public String getSweetSnack() {
        return mSweetSnack;
    }

    public void setSweetSnack(String sweetSnack) {
        mSweetSnack = sweetSnack;
    }
}
