package com.rja.codegen;

/**
 * Created by rjaylward on 1/24/16
 */
public class NameFormatter {

    public static String toCamelCase(String string) {
        if(string.contains("_")) {
            String[] splitName = string.split("_");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < splitName.length; i++) {
                String s = splitName[i];
                int length = s.length();

                if (i > 0) {
                    if (length > 0)
                        sb.append(s.substring(0, 1).toUpperCase());

                    if (length > 1)
                        sb.append(s.substring(1).toLowerCase());
                } else
                    sb.append(s);
            }

            return sb.toString();
        }
        else
           return string;
    }

    public static String toVariableName(String string) {
        String varName = toCamelCase(string);

        StringBuilder sb = new StringBuilder();

        if (varName.length() > 0)
            sb.append(varName.substring(0, 1).toLowerCase());

        if (varName.length() > 1)
            sb.append(varName.substring(1));

        return sb.toString();
    }

    public static String toUnderscored(String string) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        return string.replaceAll(regex, replacement).toLowerCase();
    }

    public static String capitalizeFirst(String string) {
        StringBuilder sb = new StringBuilder();

        if (string.length() > 0)
            sb.append(string.substring(0, 1).toUpperCase());

        if (string.length() > 1)
            sb.append(string.substring(1));

        return sb.toString();
    }

}
