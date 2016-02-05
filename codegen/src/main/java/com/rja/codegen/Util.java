package com.rja.codegen;

import java.util.ArrayList;

/**
 * Created by rjaylward on 1/24/16
 */
public class Util {

    public static String printArrayListAsString(ArrayList arrayList) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < arrayList.size(); i++) {
            if(i > 0)
                builder.append(", ");

            builder.append(arrayList.get(i));
        }

        return builder.toString();
    }

}
