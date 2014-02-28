package com.cocoahero.android.geojson.util;

import java.util.List;

public class ListUtils {

    public static <T> T getHead(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static <T> T getTail(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

}
