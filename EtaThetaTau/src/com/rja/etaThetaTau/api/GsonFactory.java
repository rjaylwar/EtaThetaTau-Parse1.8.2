package com.rja.etaThetaTau.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rja.etaThetaTau.api.deserializers.HotFeedDeserializer;
import com.rja.etaThetaTau.objects.HotFeedItemResponse;

/**
 * Created by rjaylward on 9/22/15
 */
public class GsonFactory {

    public static Gson createGsonObject() {
        return new GsonBuilder()
                .registerTypeAdapter(HotFeedItemResponse.class, new HotFeedDeserializer())
                .create();
    }

}