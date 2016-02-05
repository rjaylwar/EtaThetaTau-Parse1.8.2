package com.rja.codegen;

/**
 * Created by rjaylward on 1/24/16
 */
public class TableBuilder {

//    public static final class Notifications {
//        public static final String TABLE_NAME = "notifications";
//        public static final Uri CONTENT_URI = Uri.parse("content://" + DatabaseProvider.AUTHORITY + "/" + TABLE_NAME);
//        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.UM." + TABLE_NAME;
//
//        public static final String OBJECT_ID = FieldNames.OBJECT_ID;
//        public static final String ALERT = FieldNames.ALERT;
//        public static final String CREATED_AT = FieldNames.CREATED_AT;
//    }

    private static final String TABLE_DECLARATION =
            "\tpublic static final class %1$ss {\n" +
            "\t\tpublic static final String TABLE_NAME = \"%2$ss\";\n" +
            "\t\tpublic static final Uri CONTENT_URI = Uri.parse(\"content://\" + DatabaseProvider.AUTHORITY + \"/\" + TABLE_NAME);" +
            "\t\tpublic static final String CONTENT_ITEM_TYPE = \"vnd.android.cursor.item/vnd.UM.\" + TABLE_NAME;" +
            "%3$s\n\t}";

    private static final String TABLE_COLUMN_DECLARATION = "public static final String %1$s = FieldNames.%1$s;";

    public String getTableDeclaration(ObjectTemplate ot) {
        StringBuilder sb = new StringBuilder();

        for (ClassField cf : ot.getFields()) {
            sb.append(String.format(TABLE_COLUMN_DECLARATION, cf.getConstantFieldName()));
        }

        return String.format(TABLE_DECLARATION, ot.getClassName(), NameFormatter.toUnderscored(ot.getClassName()).toLowerCase(), sb.toString());
    }

//    public static final String CREATE_UMBER_REQUESTS_TABLE = "CREATE TABLE " + Table.Requests.TABLE_NAME + " ( " +
//            Table.Requests.OBJECT_ID + " TEXT PRIMARY KEY," +
//            Table.Requests.ETA + " INTEGER, " +
//            Table.Requests.DRIVER_EMAIL + " TEXT, " +
//            Table.Requests.CANCELED + " TINYINT DEFAULT 0 " + ");";

    private static final String TABLE_SQL_DECLARATION =
            "public static final String CREATE_%1$sS_TABLE = \"CREATE TABLE \" + Table.Requests.TABLE_NAME + \" ( \" +\n" +
            "%2$s \" + \");\";";

    private static final String ROW_SQL_DECLARATION = "Table.%1$ss.%2$s + \" %3$s";
    private static final String ROW_SQL_DIVIDER = ",\" +\n";

    public String getTableSql(ObjectTemplate ot) {
        StringBuilder sb = new StringBuilder();
        int size = ot.getFields().size();

        for (int i = 0; i < size; i++) {
            sb.append(String.format(ROW_SQL_DECLARATION, ot.getConstantObjectName(), ot.getClassName(), getSqlTypeFromClassField(ot.getFields().get(i))));
            if(i < size - 1)
                sb.append(ROW_SQL_DIVIDER);
        }

        return String.format(TABLE_SQL_DECLARATION, ot.getConstantObjectName(), sb.toString());
    }

    private String getSqlTypeFromClassField(ClassField cf) {
        switch (cf.getFieldClass()) {
            case ClassField.INT :
            case ClassField.LONG :
                return "INTEGER";
            case ClassField.BOOL :
                return "TINYINT DEFAULT 0";
            case ClassField.STRING :
            case ClassField.INT_ARRAY_LIST :
            case ClassField.STRING_ARRAY_LIST :
            default :
                return "TEXT";
        }
    }
}
