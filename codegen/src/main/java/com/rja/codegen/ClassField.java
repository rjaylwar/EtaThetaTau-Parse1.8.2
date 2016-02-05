package com.rja.codegen;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by rjaylward on 1/17/16
 */
public class ClassField {

    public static final String INT = "int";
    public static final String BOOL = "boolean";
    public static final String STRING = "String";
    public static final String LONG = "long";
    public static final String STRING_ARRAY_LIST = "ArrayList<String>";
    public static final String INT_ARRAY_LIST = "ArrayList<Integer>";
    public static final String OBJECT = "Object";

    private static final String SERIALIZED_ANNOTATION = "\t@SerializedName(FieldNames.%1$s)";
    private static final String PRIVATE_OBJECT = "\tprivate %1$s m%2$s;";

    private static final String GETTER_STRING = "\tpublic %1$s get%2$s() {\n\t\treturn m%2$s;\n\t}";
    private static final String SETTER_STRING = "\tpublic void set%1$s(%2$s %3$s) {\n\t\tm%1$s = %3$s;\n\t}";

    private String mName;
    private String mNameFormattedAsVar;
    private String mApiFieldName;
    private String mFieldClass;
    private String mFieldConstantName;
    private JsonElement mJsonElement;
    private boolean mIncludeSerializedName;

    private String mString;
    private int mInt;
    private boolean mBoolean;
    private ArrayList<Integer> mIntegerArrayList;
    private ArrayList<String> mStringArrayList;
    private Object mObject;

    public ClassField(String apiFieldName, JsonElement jsonElement, boolean includeSerializedName) {
        mIncludeSerializedName = includeSerializedName;

        mApiFieldName = apiFieldName;
        mName = NameFormatter.toCamelCase(NameFormatter.capitalizeFirst(apiFieldName));
        mNameFormattedAsVar = NameFormatter.toVariableName(mName);
        mFieldConstantName = NameFormatter.toUnderscored(mName).toUpperCase();

        mJsonElement = jsonElement;
        checkFieldClass();
    }

    public String getFieldGetterString() {
        return String.format(GETTER_STRING, mFieldClass, mName);
    }

    public String getFieldSetterString() {
        return String.format(SETTER_STRING, mName, mFieldClass, mNameFormattedAsVar);
    }

    public String getFieldDeclarationString() {
        StringBuilder sb = new StringBuilder();

        if(mIncludeSerializedName) {
            sb.append(String.format(SERIALIZED_ANNOTATION, mFieldConstantName));
            sb.append("\n");
        }

        sb.append(String.format(PRIVATE_OBJECT, mFieldClass, mName));

        return sb.toString();
    }

    public String getName() {
        return mName;
    }

    public String getNameFormattedAsVar() {
        return mNameFormattedAsVar;
    }

    public String getApiFieldName() {
        return mApiFieldName;
    }

    public String getFieldClass() {
        return mFieldClass;
    }

    public JsonElement getJsonElement() {
        return mJsonElement;
    }

    public String getString() {
        return mString;
    }

    public boolean getBoolean() {
        return mBoolean;
    }

    public int getInt() {
        return mInt;
    }

    public ArrayList<String> getStringArrayList() {
        return mStringArrayList;
    }

    public ArrayList<Integer> getIntegerArrayList() {
        return mIntegerArrayList;
    }

    public Object getObject() {
        return mObject;
    }

    public String getConstantFieldName() {
        return mFieldConstantName;
    }

    public boolean getIncludeSerializedName() {
        return mIncludeSerializedName;
    }

    private void formatVarName() {
        StringBuilder sb = new StringBuilder();

        if (mName.length() > 0)
            sb.append(mName.substring(0, 1).toLowerCase());

        if (mName.length() > 1)
            sb.append(mName.substring(1));

        mNameFormattedAsVar = sb.toString();
    }

    private void checkFieldClass() {
        if(mJsonElement.isJsonPrimitive()) {
            try { mInt = mJsonElement.getAsInt(); mFieldClass = INT; return; } catch (Exception e) { /*not an int*/ }

            try { mString = mJsonElement.getAsString(); mFieldClass = STRING; return; } catch (Exception e) { /*not an int*/ }

            try { mBoolean = mJsonElement.getAsBoolean(); mFieldClass = BOOL; return; } catch (Exception e) { /*not a boolean*/ }

            // defaults to object
            mFieldClass = OBJECT;
        }
        else if(mJsonElement.isJsonArray()) {
            Gson gson = new Gson();
            try {
                mIntegerArrayList = gson.fromJson(mJsonElement, new TypeToken<ArrayList<Integer>>(){}.getType());
                mFieldClass = INT_ARRAY_LIST;
                return;
            }
            catch (Exception e) { /*not an an array list*/ }

            try {
                mStringArrayList = gson.fromJson(mJsonElement, new TypeToken<ArrayList<String>>(){}.getType());
                mFieldClass = STRING_ARRAY_LIST;
                return;
            }
            catch (Exception e) { /*not a String array list*/ }

            // defaults to this
            mFieldClass = OBJECT;
        }
        else {
            Gson gson = new Gson();
            try {
                mObject = gson.fromJson(mJsonElement, Object.class);
            }
            catch (Exception e) {
                mObject = null;
            }
            mFieldClass = OBJECT;
        }
    }
}
