package com.rja.etaThetaTau.objects;

import android.content.Context;

import com.rja.etaThetaTau.api.ApiResponse;
import com.rja.etaThetaTau.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/23/16
 */
public class ScriptsResponse implements ApiResponse {

    private ArrayList<Script> mScripts;

    @Override
    public void saveResponse(Context context) {
        DatabaseHelper.getInstance(context).addScripts(mScripts);
    }

    public void setScripts(ArrayList<Script> scripts) {
        mScripts = scripts;
    }

    public ArrayList<Script> getScripts() {
        return mScripts;
    }

}
