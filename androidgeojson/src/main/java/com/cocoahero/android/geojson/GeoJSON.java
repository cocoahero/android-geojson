package com.cocoahero.android.geojson;

import com.cocoahero.android.geojson.util.JSONUtils;
import com.cocoahero.android.geojson.util.StreamUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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

    /**
     * Parses the given JSONObject as GeoJSON and returns a concrete subclass of
     * {@link GeoJSONObject} corresponding to the type of the root object.
     * <p/>
     * Example:
     * <p/>
     * <pre>
     * {
     *     "type": "Feature",
     *     "geometry": {
     *         "type": "Point",
     *         "coordinates": [125.6, 10.1]
     *     },
     * }
     * </pre>
     * <p/>
     * The above GeoJSON would return an instance of {@link Feature}.
     *
     * @param json
     *         A {@link JSONObject} to parse as GeoJSON
     *
     * @return A concrete subclass instance of {@link GeoJSONObject}.
     *
     * @throws IllegalArgumentException
     *         If the given object is not a valid GeoJSON type.
     */
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

    /**
     * Parses the given {@link String} into a {@link JSONObject}, and then
     * passes it to {@link GeoJSON#parse(JSONObject)}.
     *
     * @param jsonString
     *         A {@link String} to parse as GeoJSON
     *
     * @return A concrete subclass instance of {@link GeoJSONObject}.
     *
     * @throws JSONException
     */
    public static GeoJSONObject parse(String jsonString) throws JSONException {
        return parse(new JSONObject(jsonString));
    }

    /**
     * Parses the given {@link InputStream} into a {@link JSONObject}, and then
     * passes it to {@link GeoJSON#parse(JSONObject)}.
     *
     * @param stream
     *         An {@link InputStream} to parse as GeoJSON
     *
     * @return A concrete subclass instance of {@link GeoJSONObject}.
     *
     * @throws IOException
     * @throws JSONException
     */
    public static GeoJSONObject parse(InputStream stream) throws IOException, JSONException {
        return parse(StreamUtils.toString(stream));
    }

}
