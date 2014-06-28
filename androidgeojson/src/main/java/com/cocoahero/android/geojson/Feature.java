package com.cocoahero.android.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import com.cocoahero.android.geojson.util.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * Creates an empty Feature without a geometry.
     */
    public Feature() {
        // Default Constructor
    }

    /**
     * Parses the given {@link JSONObject} as a GeoJSON Feature.
     *
     * @param json
     */
    public Feature(JSONObject json) {
        super(json);

        this.mIdentifier = JSONUtils.optString(json, JSON_ID);

        JSONObject geometry = json.optJSONObject(JSON_GEOMETRY);
        if (geometry != null) {
            this.mGeometry = (Geometry) GeoJSON.parse(geometry);
        }

        this.mProperties = json.optJSONObject(JSON_PROPERTIES);
    }

    /**
     * Creates a new GeoJSON Feature object with the given geometry.
     *
     * @param geometry
     */
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

    /**
     * The optional, common identifier of this feature.
     *
     * @return The common identifier of this feature, if set.
     */
    public String getIdentifier() {
        return this.mIdentifier;
    }

    /**
     * Sets the optional, common identifier of this feature.
     *
     * @param identifier
     */
    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    /**
     * Returns the geometry of this feature. It will be a concrete instance of
     * {@link Geometry}.
     *
     * @return the geometry of this feature, or null if not set.
     */
    public Geometry getGeometry() {
        return this.mGeometry;
    }

    /**
     * Sets the {@link Geometry} of this feature.
     *
     * @param geometry
     */
    public void setGeometry(Geometry geometry) {
        this.mGeometry = geometry;
    }

    /**
     * Returns the optional properties of this feature as JSON.
     *
     * @return the properties of this feature
     */
    public JSONObject getProperties() {
        return this.mProperties;
    }

    /**
     * Sets the properties of this feature.
     *
     * @param properties
     */
    public void setProperties(JSONObject properties) {
        this.mProperties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return GeoJSON.TYPE_FEATURE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        json.put(JSON_ID, this.mIdentifier);

        if (this.mGeometry != null) {
            json.put(JSON_GEOMETRY, this.mGeometry.toJSON());
        } else {
            json.put(JSON_GEOMETRY, JSONObject.NULL);
        }

        if (this.mProperties != null) {
            json.put(JSON_PROPERTIES, this.mProperties);
        } else {
            json.put(JSON_PROPERTIES, JSONObject.NULL);
        }

        return json;
    }

}
