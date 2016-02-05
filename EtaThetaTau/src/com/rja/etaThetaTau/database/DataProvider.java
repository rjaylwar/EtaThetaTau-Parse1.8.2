package com.rja.etaThetaTau.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rja.etaThetaTau.TestJson;
import com.rja.etaThetaTau.objects.Tonight;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/5/16
 */
public class DataProvider {

    private Gson mGson;

    public DataProvider() {
        mGson = new Gson();
    }

    public ArrayList<Tonight> getTonightObjects() {
        return mGson.fromJson(TestJson.TONIGHT_ARRAY, new TypeToken<ArrayList<Tonight>>(){}.getType());
    }

}
