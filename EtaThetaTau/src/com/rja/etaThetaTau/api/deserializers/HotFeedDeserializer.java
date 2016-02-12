package com.rja.etaThetaTau.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.objects.HotFeedItemResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rjaylward on 2/11/16
 */
public class HotFeedDeserializer implements JsonDeserializer<HotFeedItemResponse> {

    @Override
    public HotFeedItemResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        HotFeedItemResponse hotFeedItemResponse = new HotFeedItemResponse();
        ArrayList<HotFeedItem> feedItemArrayList = new ArrayList<>();

        try {
            JsonObject feedResponse = json.getAsJsonObject();

            for(Map.Entry<String, JsonElement> elementEntry : feedResponse.entrySet()) {
                HotFeedItem hotFeedItem = gson.fromJson(elementEntry.getValue(), HotFeedItem.class);
                hotFeedItem.setId(elementEntry.getKey());

                if(hotFeedItem.getMessage() != null)
                    hotFeedItem.setMessage(hotFeedItem.getMessage().replace("\\n", "\n"));
                if(hotFeedItem.getReminder() != null)
                    hotFeedItem.setMessage(hotFeedItem.getReminder().replace("\\n", "\n"));
                if(hotFeedItem.getTheme() != null)
                    hotFeedItem.setMessage(hotFeedItem.getTheme().replace("\\n", "\n"));

                feedItemArrayList.add(hotFeedItem);
            }
        }
        catch (Exception e) {

        }

        hotFeedItemResponse.setHotFeedItems(feedItemArrayList);

        return hotFeedItemResponse;
    }
}
