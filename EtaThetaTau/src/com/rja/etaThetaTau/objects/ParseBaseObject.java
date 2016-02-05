package com.rja.etaThetaTau.objects;

import com.google.gson.annotations.SerializedName;
import com.rja.etaThetaTau.api.ApiResponse;
import com.rja.etaThetaTau.values.FieldNames;

/**
 * Created by rjaylward on 1/15/16
 */
public abstract class ParseBaseObject implements ApiResponse {

    @SerializedName(FieldNames.OBJECT_ID)
    private String mObjectId;

    public String getObjectId() {
        return mObjectId;
    }

    public void setObjectId(String objectId) {
        mObjectId = objectId;
    }
}
