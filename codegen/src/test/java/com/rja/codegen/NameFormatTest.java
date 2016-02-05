package com.rja.codegen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rjaylward on 1/24/16
 */
public class NameFormatTest {

    @Test
    public void camelcase_isCorrect() throws Exception {
        assertEquals("to camelcase", "fieldNameName", NameFormatter.toCamelCase("field_name_name"));
        assertEquals("to camelcase", "fieldNameName", NameFormatter.toCamelCase("fieldNameName"));
    }

    @Test
    public void underscore_isCorrect() throws Exception {
        assertEquals("to underscore", "field_name_name", NameFormatter.toUnderscored("fieldNameName"));
    }

    @Test
    public void varName_isCorrect() throws Exception {
        assertEquals("to var name", "fieldName", NameFormatter.toVariableName("FieldName"));
    }

}