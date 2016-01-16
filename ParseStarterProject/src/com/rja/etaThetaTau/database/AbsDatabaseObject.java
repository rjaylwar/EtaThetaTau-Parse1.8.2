package com.rja.etaThetaTau.database;

import android.content.Context;
import android.net.Uri;

import com.rja.etaThetaTau.api.ApiResponse;

/**
 * Created by rjaylward on 9/22/15
 */
public abstract class AbsDatabaseObject implements ApiResponse {

    public abstract void saveResponse(final Context context);

    public abstract Uri getUri();

}
