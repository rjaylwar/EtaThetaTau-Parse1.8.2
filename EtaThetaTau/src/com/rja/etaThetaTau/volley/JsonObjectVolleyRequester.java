package com.rja.etaThetaTau.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rja.etaThetaTau.values.Constants;
import com.rja.etaThetaTau.values.FieldNames;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rjaylward on 9/22/15
 */
public class JsonObjectVolleyRequester extends AbsVolleyRequester {

    public JsonObjectVolleyRequester(VolleyContext context) {
        super(context);
    }

    @Override
    protected Request buildRequest(VolleyContext volleyContext, int requestType, final String url, final JsonObject
            requestBody, final VolleyRequestListener listener, int timeout, boolean handleResponse) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestType, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(response.toString()).getAsJsonObject();

                if(listener != null)
                    listener.onResponse(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onVolleyError(error, url, listener);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return buildAuthenticationHeader();
            }
        };

        if(timeout == 0)
            timeout = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setTag("JSON Request");

        return jsonObjectRequest;
    }

    private Map<String, String> buildAuthenticationHeader() {
        Map<String, String> header = new HashMap<>();

        header.put(FieldNames.PARSE_REST_API_KEY, Constants.PARSE_API_KEY);
        header.put(FieldNames.PARSE_APPLICATION_ID, Constants.PARSE_APP_ID);

        return header;
    }

}

