package com.rja.etaThetaTau.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rja.etaThetaTau.objects.Script;
import com.rja.etaThetaTau.objects.ScriptsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rjaylward on 2/23/16
 */
public class ScriptsDeserializer implements JsonDeserializer<ScriptsResponse> {

    @Override
    public ScriptsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ScriptsResponse scriptsResponse = new ScriptsResponse();
        ArrayList<Script> scripts = new ArrayList<>();

        try {
            JsonArray response = json.getAsJsonArray();

            for(JsonElement jsonElement : response) {
                Script script = gson.fromJson(jsonElement, Script.class);
                scripts.add(script);
            }
        }
        catch (Exception e) {
            try {
                scripts.clear();
                JsonObject feedResponse = json.getAsJsonObject();

                for(Map.Entry<String, JsonElement> elementEntry : feedResponse.entrySet()) {
                    Script script = gson.fromJson(elementEntry.getValue(), Script.class);
                    scripts.add(script);
                }
            }
            catch (Exception e1) {

            }
        }

        scriptsResponse.setScripts(scripts);
        return scriptsResponse;
    }
}
