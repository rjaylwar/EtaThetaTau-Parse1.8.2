package com.rja.etaThetaTau.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.rja.etaThetaTau.api.ApiResponse;
import com.rja.etaThetaTau.api.GsonRequest;
import com.rja.etaThetaTau.values.Constants;
import com.rja.etaThetaTau.values.FieldNames;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rjaylward on 9/22/15
 */
public class GsonVolleyRequester<T extends ApiResponse> extends AbsVolleyRequester {

    protected Class<T> mClass;

    public GsonVolleyRequester(VolleyContext context, Class<T> theClass) {
        super(context);
        mClass = theClass;
    }

    @Override
    protected Request buildRequest(VolleyContext volleyContext, int requestType, String url, JsonObject requestBody,
                                   final VolleyRequestListener listener, int timeoutLength, boolean handleResponse) {

        final boolean authorizationHeaderRequired = true;

        GsonRequest<T> request = new GsonRequest<T>(requestType, url, requestBody, mClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GSON Request error --> ", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(authorizationHeaderRequired)
                    return buildAuthenticationHeader();
                else
                    return new HashMap<>();
            }

        };

        if(timeoutLength == 0)
            timeoutLength = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;

        request.setRetryPolicy(new DefaultRetryPolicy(timeoutLength, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag("Class Name");

        return request;
    }

    private Map<String, String> buildAuthenticationHeader() {

        Map<String, String> header = new HashMap<>();

        header.put(FieldNames.PARSE_REST_API_KEY, Constants.PARSE_API_KEY);
        header.put(FieldNames.PARSE_APPLICATION_ID, Constants.PARSE_APP_ID);

        return header;
    }
}

