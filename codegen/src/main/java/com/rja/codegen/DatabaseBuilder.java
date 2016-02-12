package com.rja.codegen;

/**
 * Created by rjaylward on 1/17/16
 */
public class DatabaseBuilder {

    public static final String ADD_OBJECT =
            "public synchronized void add%1$s(%1$s %2$s) {\n\n" +
            "\tmContentResolver.insert(Table.%1$ss.CONTENT_URI, %2$s.toContentValues());\n\n}";

//    public synchronized void addNotification(Notification notification) {
//
//        ContentValues values = new ContentValues();
//        values.put(Table.Notifications.OBJECT_ID, notification.getObjectId());
//        values.put(Table.Notifications.CREATED_AT, notification.getCreatedAt().getTime());
//
//        // Inserting Row
//        mContentResolver.insert(Table.Notifications.CONTENT_URI, values);
//
//    }

    public String generateAddMethod(ObjectTemplate ot) {
        return String.format(ADD_OBJECT, ot.getClassName(), ot.getVarName());
    }

    public static final String ADD_ALL_OBJECTS =
            "\tpublic synchronized void add%1$ss(ArrayList<%1$s> %2$ss) {\n\n" +
            "\t\tArrayList<ContentValues> valuesArray = new ArrayList<>();\n\n" +
            "\t\tfor(%1$s %2$s : %2$ss) {\n" +
            "\t\t\tvaluesArray.add(%2$s.toContentValues);\n\t\t}\n\n" +
            "\t\tmContentResolver.bulkInsert(Table.%1$ss.CONTENT_URI, valuesArray.toArray(new ContentValues[%2$ss.size()]));\n}";

//    public synchronized void addNotifications(ArrayList<Notification> notifications) {
//
//        ArrayList<ContentValues> valuesArray = new ArrayList<>();
//
//        for(Notification notification : notifications) {
//            ContentValues values = new ContentValues();
//            values.put(Table.Notifications.ALERT, notification.getAlert());
//            values.put(Table.Notifications.OBJECT_ID, notification.getObjectId());
//            values.put(Table.Notifications.CREATED_AT, notification.getCreatedAt().getTime());
//
//            valuesArray.add(values);
//        }
//
//        mContentResolver.bulkInsert(Table.Notifications.CONTENT_URI, valuesArray.toArray(new ContentValues[notifications.size()]));
//    }

    public String generateAddAllMethod(ObjectTemplate ot) {
        return String.format(ADD_ALL_OBJECTS, ot.getClassName(), ot.getVarName());
    }

    public static final String GET_ALL = "\tpublic synchronized ArrayList<%1$s> getAll%1$ss() {\n" +
            "\t\tArrayList<%1$s> %2$ss = new ArrayList<>();\n" +
            "\t\tString selectQuery = \"SELECT * FROM \" + Table.%3$ss.TABLE_NAME + \" ORDER BY \" + Table.%3$ss.CREATED_AT + \" DESC\";\n" +
            "\t\tSQLiteDatabase db = mOpenDatabaseHelper.getWritableDatabase();\n" +
            "\t\tCursor cursor = db.rawQuery(selectQuery, null);\n" +
            "\t\ttry {\n\t\tif(cursor.moveToFirst()) {\n\t\t\tdo {\n" +
            "\t\t\t\t\t%1$s %2$s = %1$s.fromCursor(cursor);\n\n" +
            "\t\t\t\t\tif(%2$s != null)\n\n" +
            "\t\t\t\t\t\t%2$ss.add(%2$s);\n" +
            "\t\t\t\t} while (cursor.moveToNext());\n\t\t\t}\n\t\t} finally {\n\t\t\tif(cursor != null)\n\t\t\t\tcursor.close();\t\t\n}\n\n" +
            "\t\treturn %2$ss;\n\t}";

//    public synchronized ArrayList<Talk> getAllTalks() {
//
//        ArrayList<Talk> talks = new ArrayList<>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + FieldNames.TALKS + " ORDER BY " + Table.Talks.CREATED_AT + " DESC";
//
//        SQLiteDatabase db = mOpenDatabaseHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        try {
//            if(cursor.moveToFirst()) {
//                do {
//                    Talk talk = new Talk();
//                    talk.setCreatedAt(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(Table.Talks.CREATED_AT))));
//                    talk.setId(cursor.getString(cursor.getColumnIndexOrThrow(Table.Talks.OBJECT_ID)));
//
//                    talks.add(talk);
//                } while (cursor.moveToNext());
//            }
//        } finally {
//            if(cursor != null)
//                cursor.close();
//        }
//
//        return talks;
//    }

    public String generateGetAllMethod(ObjectTemplate ot) {
        return String.format(GET_ALL, ot.getClassName(), ot.getVarName(), ot.getClassName());
    }

//    public synchronized void updateRequest(UmberRequest request) {
//        mContentResolver.update(Table.Requests.CONTENT_URI, request.toContentValues(), Table.Requests.OBJECT_ID + " = '" + request.getObjectId() + "'", null);
//    }

    public static final String UPDATE_OBJECT =
            "\tpublic synchronized void update%1$s(%1$s %2$s) {\n" +
            "\t\tmContentResolver.update(Table.%1$ss.CONTENT_URI, %2$s.toContentValues(), Table.%1$ss.ID + \" = '\" + %2$s.getId() + \"'\", null);\n\t}";

    public String generateUpdateMethod(ObjectTemplate ot) {
        return String.format(UPDATE_OBJECT, ot.getClassName(), ot.getVarName());
    }

//    public synchronized void deleteRequest(String id) {
//        mContentResolver.delete(Table.Requests.CONTENT_URI, Table.Requests.OBJECT_ID + " == '" + id + "'", null);
//    }

    public static final String DELETE_OBJECT =
            "\tpublic synchronized void delete%1$s(String id) {\n" +
            "\t\tmContentResolver.delete(Table.%1$ss.CONTENT_URI, %2$s.toContentValues(), Table.%1$ss.ID + \" == '\" + %2$s.getId() + \"'\", null);\n\t}";

    public String generateDeleteMethod(ObjectTemplate ot) {
        return String.format(DELETE_OBJECT, ot.getClassName(), ot.getVarName());
    }

    public String getAllDatabaseMethods(ObjectTemplate ot) {
        StringBuilder sb = new StringBuilder();

        sb.append(generateAddMethod(ot)).append("\n\n");
        sb.append(generateAddAllMethod(ot)).append("\n\n");
        sb.append(generateGetAllMethod(ot)).append("\n\n");
        sb.append(generateUpdateMethod(ot)).append("\n\n");
        sb.append(generateDeleteMethod(ot)).append("\n\n");

        return sb.toString();
    }
}
