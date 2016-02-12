package com.rja.codegen;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rjaylward on 2/10/16
 */
public class TableBuilderTest {

    JsonObject mJsonObject;
    ObjectTemplate mObjectTemplate;
    TableBuilder mTableBuilder;

    private static final String HOT_FEED_JSON = "{" +
            "\"id\":\"abc123\"," +
            "\"date\":\"2-28-15 19:00\"," +
            "\"images\":[\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-15/11078465_791232620973084_349843663_n.jpg\"," +
            "\"https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-15/11032794_395259607302025_2138447158_n.jpg\"]," +
            "\"link_title\":\"tickets\",\"link_url\":\"http://pepperdinearts.ticketforce.com/eventperformances.asp?evt=50\"," +
            "\"link_image\":\"image.jpg\",\"location_latitude\":41.4535052,\"location_longitude\":-120.2463351," +
            "\"location_title\":\"Lovefeast in the cafe Cafe - 7 PM\",\"main_image\":\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-15/11078465_791232620973084_349843663_n.jpg\"," +
            "\"message\":\"\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25You guys brought to house down last night, fantastic job. The audience can tell when we are having fun, so go all out on stage! You guys are wonderful and amazing in every way.\"," +
            "\"reminder\":\"Buy Songfest tickets for your friends and family!\",\"snacks_drinks\":\"Clarissa N.\",\"snacks_salty\":\"Alanna A.\"," +
            "\"snacks_sweet\":\"RJ A.\",\"tag\":\"2016\",\"theme\":\"Rubber Ducky\",\"title\":\"Tonight\",\"type\":\"tonight\"}";

    @Before
    public void setUp() throws Exception {

        JsonParser parser = new JsonParser();

        mJsonObject = parser.parse(HOT_FEED_JSON).getAsJsonObject();

        mObjectTemplate = new ObjectTemplate("HotFeedItem", mJsonObject);
        mTableBuilder = new TableBuilder();
    }

    @Test
    public void checkTableDeclaration() throws Exception {
        assertEquals("", mTableBuilder.getTableDeclaration(mObjectTemplate));
    }

    @Test
    public void checkTableSql() throws Exception {
        assertEquals("", mTableBuilder.getTableSql(mObjectTemplate));
    }

}