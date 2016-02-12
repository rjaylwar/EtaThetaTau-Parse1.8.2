package com.rja.codegen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rjaylward on 1/24/16
 */
public class ObjectTemplateTest {

    JsonObject mJsonObject;
    ObjectTemplate mObjectTemplate;

    private static final String HOT_FEED_JSON = "{\"date\":\"2-28-15 19:00\",\"images\":[\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-15/11078465_791232620973084_349843663_n.jpg\"," +
            "\"https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-15/11032794_395259607302025_2138447158_n.jpg\"],\"link\":{\"title\":\"tickets\"," +
            "\"image\":\"https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-15/11032794_395259607302025_2138447158_n.jpg\"," +
            "\"url\":\"http://pepperdinearts.ticketforce.com/eventperformances.asp?evt=50\"},\"location\":{\"latitude\":41.4535052,\"longitude\":-120.2463351," +
            "\"title\":\"Lovefeast in the cafe Cafe - 7 PM\"},\"main_image\":\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-15/11078465_791232620973084_349843663_n.jpg\"," +
            "\"message\":\"\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25You guys brought to house down last night, fantastic job. The audience can tell when we are having fun, so go all out on stage! You guys are wonderful and amazing in every way.\"," +
            "\"reminder\":\"Buy Songfest tickets for your friends and family!\",\"snacks\":{\"drinks\":\"Clarissa N.\",\"salty\":\"Alanna A.\",\"sweet\":\"RJ A.\"}," +
            "\"tag\":\"2016\",\"theme\":\"Rubber Ducky\",\"title\":\"Tonight\",\"type\":\"tonight\"}";

    @Before
    public void setUp() throws Exception {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("field_string", "A new string");
//        jsonObject.addProperty("someString", "A test string");
//        jsonObject.addProperty("field_bool", true);
//        jsonObject.addProperty("field_int", 12);
//
//        JsonArray jsonStringArray = new JsonArray();
//        jsonStringArray.add("one");
//        jsonStringArray.add("two");
//        jsonStringArray.add("three");
//
//        JsonArray jsonIntArray = new JsonArray();
//        jsonIntArray.add(1);
//        jsonIntArray.add(2);
//        jsonIntArray.add(3);
//
//        JsonObject innerObject = new JsonObject();
//
//        jsonObject.add("field_string_array", jsonStringArray);
//        jsonObject.add("field_int_array", jsonIntArray);
//        jsonObject.add("SomeInnerObject", innerObject);

        JsonParser parser = new JsonParser();

        mJsonObject = parser.parse(HOT_FEED_JSON).getAsJsonObject();

        mObjectTemplate = new ObjectTemplate("HotFeedItem", mJsonObject);
    }

    @Test
    public void checkClassGenerator_objectDefinition() throws Exception {
        assertEquals("", mObjectTemplate.getObjectDefinitionCodeDeclaration());
    }

    @Test
    public void checkClassGenerator_fieldNames() throws Exception {
        assertEquals("", mObjectTemplate.getFieldNamesDeclaration());
    }
}