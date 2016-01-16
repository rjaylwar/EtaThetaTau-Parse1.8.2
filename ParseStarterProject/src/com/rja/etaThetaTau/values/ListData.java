package com.rja.etaThetaTau.values;

import android.content.Context;

/**
 * Created by rjaylward on 9/22/15
 */
public class ListData extends AbsPreferences {

    private static final String NOTIFICATIONS_EXPIRATION = "notifications_exp";

    protected ListData(Context context) {
        super(context);
    }

    public long getNotificationsExpiration() {
        return getLongPref(NOTIFICATIONS_EXPIRATION);
    }

    public void setNotificationsExpiration(long expiration) {
        setPref(NOTIFICATIONS_EXPIRATION, expiration);
    }
}
