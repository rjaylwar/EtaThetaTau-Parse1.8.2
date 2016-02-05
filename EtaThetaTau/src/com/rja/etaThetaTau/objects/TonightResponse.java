package com.rja.etaThetaTau.objects;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.values.FieldNames;

import java.util.ArrayList;

/**
 * Created by rjaylward on 1/16/16
 */
public class TonightResponse extends ParseBaseObject {

    @SerializedName(FieldNames.RESULTS)
    private ArrayList<Tonight> mTonightArrayList;

    @Override
    public void saveResponse(Context context) {

    }
}