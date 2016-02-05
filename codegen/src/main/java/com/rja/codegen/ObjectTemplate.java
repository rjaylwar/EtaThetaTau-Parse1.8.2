package com.rja.codegen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rjaylward on 1/17/16
 */
public class ObjectTemplate {

    private String mClassName;
    private String mVarName;
    private String mConstantObjectName;
    private ArrayList<ClassField> mFields = new ArrayList<>();

    private JsonObject mJsonObject;

    private static final String PUBLIC_CLASS_TEMPLATE_STRING =
            "import com.google.gson.Gson;\n" +
            "import com.google.gson.JsonElement;\n" +
            "import com.google.gson.annotations.SerializedName;\n" +
            "import com.google.gson.reflect.TypeToken;\n\n" +
            "import java.util.ArrayList;\n\n" +
            "public class %1$s {\n%2$s\n\n}";

    private static final String FIELD_NAMES_STRING = "\tpublic static final String %1$s = \"%2$s\"";

    public ObjectTemplate(String className, JsonObject jsonObject) {
        mJsonObject = jsonObject;
        mClassName = NameFormatter.capitalizeFirst(className);
        mVarName = NameFormatter.toVariableName(NameFormatter.toCamelCase(mClassName));
        mConstantObjectName = NameFormatter.toUnderscored(mClassName).toUpperCase();

        for (Map.Entry<String, JsonElement> jsonElementEntry : mJsonObject.entrySet()) {
            mFields.add(new ClassField(jsonElementEntry.getKey(), jsonElementEntry.getValue(), true));
        }
    }

    public JsonObject getJsonObject() {
        return mJsonObject;
    }

    public String getClassName() {
        return mClassName;
    }

    public ArrayList<ClassField> getFields() {
        return mFields;
    }

    public void addField(ClassField classField) {
        mFields.add(classField);
    }

    public String getObjectDefinitionCodeDeclaration() {
        StringBuilder sb = new StringBuilder();

        for (ClassField classField : mFields) {
            sb.append("\n");
            sb.append(classField.getFieldDeclarationString());
            sb.append("\n");
        }

        for (ClassField cf : mFields) {
            sb.append("\n\n");
            sb.append(cf.getFieldSetterString());
            sb.append("\n\n");
            sb.append(cf.getFieldGetterString());
        }

        sb.append("\n\n");
        sb.append(toContentValuesDeclaration());
        sb.append("\n\n");
        sb.append(fromCursorDeclaration());

        return String.format(PUBLIC_CLASS_TEMPLATE_STRING, mClassName, sb.toString());
    }

    public String getFieldNamesDeclaration() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("// %1$s.class Api Field Names\n\n", mClassName));

        for (ClassField cf : mFields) {
            sb.append(String.format(FIELD_NAMES_STRING, cf.getConstantFieldName(), cf.getApiFieldName()));
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getVarName() {
        return mVarName;
    }

    public String getConstantObjectName() {
        return mConstantObjectName;
    }

    public static final String CONTENT_VALUES = "\t\tvalues.put(Table.%1$ss.%2$s, %3$s.get%4$s());\n";
    public static final String CONTENT_VALUES_FOR_BOOLEAN = "\t\tvalues.put(Table.%1$ss.%2$s, %3$s.get%4$s() ? 1 : 0);\n";
    public static final String CONTENT_VALUES_FOR_ARRAY = "\t\tvalues.put(Table.%1$ss.%2$s, Util.printArrayListAsString(%3$s.get%4$s()));\n";

    public static final String GET_OBJECT =
            "\tpublic ContentValues toContentValues() {\n" +
                    "\t\tContentValues values = new ContentValues();\n\n" +
                    "%1$s\n" +
                    "\t\treturn values;\n\t}";

    public String toContentValuesDeclaration() {

        StringBuilder s = new StringBuilder();
        for(ClassField cf : this.getFields()) {
            switch (cf.getFieldClass()) {
                case ClassField.INT_ARRAY_LIST:
                case ClassField.STRING_ARRAY_LIST:
                    s.append(String.format(CONTENT_VALUES_FOR_ARRAY, this.getClassName(), cf.getConstantFieldName(), "this", cf.getName()));
                    break;
                case ClassField.BOOL:
                    s.append(String.format(CONTENT_VALUES_FOR_BOOLEAN, this.getClassName(), cf.getConstantFieldName(), "this", cf.getName()));
                    break;
                default:
                    s.append(String.format(CONTENT_VALUES, this.getClassName(), cf.getConstantFieldName(), "this", cf.getName()));
                    break;
            }
        }

        return String.format(GET_OBJECT, s.toString());
    }

    public static final String SET_FIELD_VALUES = "\t\t\t%1$s.set%2$s(cursor.get%3$s(cursor.getColumnIndexOrThrow(Table.%4$ss.%5$s)));\n";
    public static final String SET_BOOLEAN_FIELD_VALUES = "\t\t\t%1$s.set%2$s(1 == cursor.getInt(cursor.getColumnIndexOrThrow(Table.%3$ss.%4$s)));\n";
    public static final String SET_STRING_ARRAY_FIELD_VALUES = "\t\t\t%1$s.set%2$s(new ArrayList<String>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(Table.%3$ss.%4$s)).split(\", \"))));\n";
    public static final String SET_INT_ARRAY_FIELD_VALUES =
            "\t\t\tArrayList<String> stringList%2$s = new ArrayList<String>(Arrays.asList(cursor.getString(cursor.getColumnIndexOrThrow(Table.%3$ss.%4$s)).split(\", \")));\n" +
            "\t\t\tArrayList<Integer> intList%2$s = new ArrayList<>();\n" +
            "\t\t\tfor (String st : stringList%2$s) {\n" +
            "\t\t\t\ttry { intList%2$s.add(Integer.parseInt(st)); } catch (Exception e) {} \n" +
            "\t\t\t}\n" +
            "\t\t\t%1$s.set%2$s(intList%2$s);\n";

    public static final String READ_CURSOR =
            "\tpublic %1$s fromCursor(Cursor cursor) {\n" +
            "\t\t%1$s %2$s = new %1$s();\n\n" +
            "\t\ttry {\n" +
            "%3$s\n" +
            "\t\t} catch(Exception e) {\n" +
            "\t\t\t%2$s = null;\n\t\t}\n" +
            "\t\treturn %2$s;\n\t}";

    public String fromCursorDeclaration() {
        StringBuilder sb = new StringBuilder();

        for(ClassField cf : this.getFields()) {
            switch (cf.getFieldClass()) {
                case ClassField.STRING_ARRAY_LIST:
                    sb.append(String.format(SET_STRING_ARRAY_FIELD_VALUES, this.getVarName(), cf.getName(), this.getClassName(), cf.getConstantFieldName()));
                    break;
                case ClassField.INT_ARRAY_LIST:
                    sb.append(String.format(SET_INT_ARRAY_FIELD_VALUES, this.getVarName(), cf.getName(), this.getClassName(), cf.getConstantFieldName()));
                    break;
                case ClassField.BOOL:
                    sb.append(String.format(SET_BOOLEAN_FIELD_VALUES, this.getVarName(), cf.getName(), this.getClassName(), cf.getConstantFieldName()));
                    break;
                default:
                    sb.append(String.format(SET_FIELD_VALUES, this.getVarName(), cf.getName(), NameFormatter.capitalizeFirst(cf.getFieldClass()), this.getClassName(), cf.getConstantFieldName()));
                    break;
            }
        }

        return String.format(READ_CURSOR, this.getClassName(), this.getVarName(), sb.toString());
    }

}
