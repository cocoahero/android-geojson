package com.cocoahero.android.geojson;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.cocoahero.android.geojson.util.JSONUtils;

public class Feature extends GeoJSONObject {

    // ------------------------------------------------------------------------
    // Private Constants
    // ------------------------------------------------------------------------

    private static final String JSON_ID = "id";

    private static final String JSON_GEOMETRY = "geometry";

    private static final String JSON_PROPERTIES = "properties";

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private String mIdentifier;

    private Geometry mGeometry;

    private JSONObject mProperties;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public Feature() {
        // Default Constructor
    }

    public Feature(JSONObject json) {
        super(json);

        this.mIdentifier = JSONUtils.optString(json, JSON_ID);

        JSONObject geometry = json.optJSONObject(JSON_GEOMETRY);
        if (geometry != null) {
            this.mGeometry = (Geometry) GeoJSON.parse(geometry);
        }

        this.mProperties = json.optJSONObject(JSON_PROPERTIES);
    }

    public Feature(Geometry geometry) {
        this.mGeometry = geometry;
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<Feature> CREATOR = new Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel in) {
            return (Feature) readParcel(in);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public Geometry getGeometry() {
        return this.mGeometry;
    }

    public void setGeometry(Geometry geometry) {
        this.mGeometry = geometry;
    }

    public JSONObject getProperties() {
        return this.mProperties;
    }

    public void setProperties(JSONObject properties) {
        this.mProperties = properties;
    }

    @Override
    public String getType() {
        return GeoJSON.TYPE_FEATURE;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        json.put(JSON_ID, this.mIdentifier);

        if (this.mGeometry != null) {
            json.put(JSON_GEOMETRY, this.mGeometry.toJSON());
        }
        else {
            json.put(JSON_GEOMETRY, JSONObject.NULL);
        }

        if (this.mProperties != null) {
            json.put(JSON_PROPERTIES, this.mProperties);
        }
        else {
            json.put(JSON_PROPERTIES, JSONObject.NULL);
        }

        return json;
    }

}
