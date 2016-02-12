package com.rja.etaThetaTau.volley;

import android.content.Context;

import com.rja.etaThetaTau.ParseApplication;

/**
 * Created by rjaylward on 2/11/16
 */
public interface VolleyContext {

    /**
     * A request filter for request cancellation
     *
     * @return the filter
     */
    String getRequestFilter();

    /**
     * Get an app context
     *
     * @return app context
     */
    Context getContext();

    /**
     * Get the application class
     *
     * @return application
     */
    ParseApplication getHotApplication();

}
