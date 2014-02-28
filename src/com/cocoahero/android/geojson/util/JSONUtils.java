package com.cocoahero.android.geojson.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {

    public static boolean isEmpty(JSONObject json) {
        return (json == null) || (json.length() <= 0);
    }

    public static boolean isEmpty(JSONArray json) {
        return (json == null) || (json.length() <= 0);
    }

    public static String optString(JSONObject json, String name) {
        return optString(json, name, null);
    }

    public static String optString(JSONObject json, String name, String fallback) {
        if (json != null) {
            if (!json.isNull(name)) {
                return json.optString(name, fallback);
            }
            return fallback;
        }
        return null;
    }

    public static String optString(JSONArray json, int index) {
        return optString(json, index, null);
    }

    public static String optString(JSONArray json, int index, String fallback) {
        if (json != null) {
            if (!json.isNull(index)) {
                return json.optString(index, fallback);
            }
            return fallback;
        }
        return null;
    }

}
