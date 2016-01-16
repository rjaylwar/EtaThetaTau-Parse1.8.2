package com.rja.etaThetaTau.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by rjaylward on 9/22/15
 */
public class GsonFactory {

    public static Gson createGsonObject() {
        return new GsonBuilder()
//                .registerTypeAdapter(Notification.class, new NotificationDeserializer())
                .create();
    }

}
