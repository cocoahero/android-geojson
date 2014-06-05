package com.cocoahero.android.geojson;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.cocoahero.android.geojson.util.JSONUtils;
import com.cocoahero.android.geojson.util.StreamUtils;

public class GeoJSON {

    // ------------------------------------------------------------------------
    // Public Constants
    // ------------------------------------------------------------------------

    public static final String TYPE_POINT = "Point";

    public static final String TYPE_MULTI_POINT = "MultiPoint";

    public static final String TYPE_LINE_STRING = "LineString";

    public static final String TYPE_MULTI_LINE_STRING = "MultiLineString";

    public static final String TYPE_POLYGON = "Polygon";

    public static final String TYPE_MULTI_POLYGON = "MultiPolygon";

    public static final String TYPE_GEOMETRY_COLLECTION = "GeometryCollection";

    public static final String TYPE_FEATURE = "Feature";

    public static final String TYPE_FEATURE_COLLECTION = "FeatureCollection";

    // ------------------------------------------------------------------------
    // Class Methods
    // ------------------------------------------------------------------------

    public static GeoJSONObject parse(JSONObject json) {
        String type = JSONUtils.optString(json, GeoJSONObject.JSON_TYPE);

        if (TYPE_POINT.equalsIgnoreCase(type)) {
            return new Point(json);
        }

        if (TYPE_MULTI_POINT.equalsIgnoreCase(type)) {
            return new MultiPoint(json);
        }

        if (TYPE_LINE_STRING.equalsIgnoreCase(type)) {
            return new LineString(json);
        }

        if (TYPE_MULTI_LINE_STRING.equalsIgnoreCase(type)) {
            return new MultiLineString(json);
        }

        if (TYPE_POLYGON.equalsIgnoreCase(type)) {
            return new Polygon(json);
        }

        if (TYPE_MULTI_POLYGON.equalsIgnoreCase(type)) {
            return new MultiPolygon(json);
        }

        if (TYPE_GEOMETRY_COLLECTION.equalsIgnoreCase(type)) {
            return new GeometryCollection(json);
        }

        if (TYPE_FEATURE.equalsIgnoreCase(type)) {
            return new Feature(json);
        }

        if (TYPE_FEATURE_COLLECTION.equalsIgnoreCase(type)) {
            return new FeatureCollection(json);
        }

        throw new IllegalArgumentException("The type '" + type + "' is not a valid GeoJSON type.");
    }

    public static GeoJSONObject parse(String jsonString) throws JSONException {
        return parse(new JSONObject(jsonString));
    }

    public static GeoJSONObject parse(InputStream stream) throws IOException, JSONException {
        return parse(StreamUtils.toString(stream));
    }

}
