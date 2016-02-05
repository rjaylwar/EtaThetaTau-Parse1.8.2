package com.rja.codegen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rjaylward on 1/24/16
 */
public class ObjectTemplateTest {

    JsonObject mJsonObject;
    ObjectTemplate mObjectTemplate;

    @Before
    public void setUp() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("field_string", "A new string");
        jsonObject.addProperty("someString", "A test string");
        jsonObject.addProperty("field_bool", true);
        jsonObject.addProperty("field_int", 12);

        JsonArray jsonStringArray = new JsonArray();
        jsonStringArray.add("one");
        jsonStringArray.add("two");
        jsonStringArray.add("three");

        JsonArray jsonIntArray = new JsonArray();
        jsonIntArray.add(1);
        jsonIntArray.add(2);
        jsonIntArray.add(3);

        JsonObject innerObject = new JsonObject();

        jsonObject.add("field_string_array", jsonStringArray);
        jsonObject.add("field_int_array", jsonIntArray);
        jsonObject.add("SomeInnerObject", innerObject);

        mJsonObject = jsonObject;

        mObjectTemplate = new ObjectTemplate("TestObject", mJsonObject);
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