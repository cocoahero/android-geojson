package com.cocoahero.android.geojson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * An intermediate, abstract class that acts as a common base for all GeoJSON
 * geometry types.
 * 
 */
public abstract class Geometry extends GeoJSONObject {

    // ------------------------------------------------------------------------
    // Public Constants
    // ------------------------------------------------------------------------

    public static final String JSON_COORDINATES = "coordinates";

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public Geometry() {
        // Default Constructor
    }

    public Geometry(JSONObject json) {
        super(json);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        json.put(JSON_COORDINATES, new JSONArray());

        return json;
    }

}
