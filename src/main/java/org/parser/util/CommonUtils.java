package org.parser.util;


import com.google.gson.JsonElement;

public class CommonUtils {
    public static String isNullNull(String address) {
        return null;
    }

    public static String isNullNull(JsonElement elem) {
        if ( elem.isJsonNull() ){
            return null;
        } else {
            return elem.getAsString().replaceAll("\"", "");
        }
    }
}
