package com.rja.etaThetaTau.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

/**
 * Created by rjaylward on 9/22/15
 */
public abstract class AbsVolleyRequester {

    protected Context mContext;

    public AbsVolleyRequester(VolleyContext context) {
        mContext = context.getContext().getApplicationContext(); //To avoid leaks
    }

    public void makeGetRequest(VolleyContext volleyContext, String url, final VolleyRequestListener listener) {
        VolleyRequestQueue queue = VolleyRequestQueue.getInstance(mContext);
        Request request = buildRequest(volleyContext, Request.Method.GET, url, null, listener, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, true);
        queue.add(request);
    }

    public void makeLargeGetRequest(VolleyContext volleyContext, String url, final VolleyRequestListener listener) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        Request request = buildRequest(volleyContext, Request.Method.GET, url, null, listener, 10000, true);
        queue.add(request);
    }

    public void makePostRequest(VolleyContext volleyContext, String url, JsonObject requestBody, VolleyRequestListener listener) {
        makePostRequest(volleyContext, url, requestBody, listener, true);
    }

    public void makePostRequest(VolleyContext volleyContext, String url, JsonObject requestBody, VolleyRequestListener listener, boolean handleResponse) {
        VolleyRequestQueue queue = VolleyRequestQueue.getInstance(mContext);
        Request request = buildRequest(volleyContext, Request.Method.POST, url, requestBody, listener, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, handleResponse);

        queue.add(request);
    }

    public void makePutRequest(VolleyContext volleyContext, String url, JsonObject requestBody, VolleyRequestListener listener) {
        makePutRequest(volleyContext, url, requestBody, listener, true);
    }

    public void makePutRequest(VolleyContext volleyContext, String url, JsonObject requestBody, VolleyRequestListener listener, boolean handleResponse) {
        VolleyRequestQueue queue = VolleyRequestQueue.getInstance(mContext);
        Request request = buildRequest(volleyContext, Request.Method.PUT, url, requestBody, listener, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, handleResponse);

        queue.add(request);
    }

    public void makeDeleteRequest(VolleyContext volleyContext, String url, VolleyRequestListener listener) {
        VolleyRequestQueue queue = VolleyRequestQueue.getInstance(mContext);
        Request request = buildRequest(volleyContext, Request.Method.DELETE, url, null, listener, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, false);

        queue.add(request);
    }

    protected abstract Request buildRequest(VolleyContext volleyContext, int requestType, String url, JsonObject requestBody,
                                            VolleyRequestListener listener, int timeout, boolean handleResponse);

    protected void onVolleyError(VolleyError error, String url, VolleyRequestListener listener) {
        try {
            if(error instanceof TimeoutError)
                Log.d("request timed out", url);
            else {
                Log.d("error status code", Integer.toString(error.networkResponse.statusCode));

                Log.d("hopefully message?", new String(error.networkResponse.data, "UTF-8"));
            }
        } catch(Exception e) {
            Log.d(null, e.toString());
        }

        if(listener != null)
            listener.onErrorResponse(error == null ? new VolleyError("error was null") : error);
    }

}

