package com.cocoahero.android.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GeometryCollection extends Geometry {

    // ------------------------------------------------------------------------
    // Public Constants
    // ------------------------------------------------------------------------

    public static final String JSON_GEOMETRIES = "geometries";

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final List<Geometry> mGeometries = new ArrayList<Geometry>();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Creates an empty geometry collection.
     */
    public GeometryCollection() {
        // Default Constructor
    }

    /**
     * Parses the given {@link JSONObject} as a geometry collection.
     *
     * @param json
     */
    public GeometryCollection(JSONObject json) {
        super(json);

        JSONArray geometries = json.optJSONArray(JSON_GEOMETRIES);
        if (geometries != null) {
            for (int i = 0; i < geometries.length(); i++) {
                JSONObject geometry = geometries.optJSONObject(i);
                if (geometry != null) {
                    this.mGeometries.add((Geometry) GeoJSON.parse(geometry));
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<GeometryCollection> CREATOR = new Creator<GeometryCollection>() {
        @Override
        public GeometryCollection createFromParcel(Parcel in) {
            return (GeometryCollection) readParcel(in);
        }

        @Override
        public GeometryCollection[] newArray(int size) {
            return new GeometryCollection[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    /**
     * Adds a {@link Geometry} to this geometry collection.
     *
     * @param geometry
     */
    public void addGeometry(Geometry geometry) {
        this.mGeometries.add(geometry);
    }

    /**
     * Removes the given {@link Geometry} from this geometry collection.
     *
     * @param geometry
     */
    public void removeGeometry(Geometry geometry) {
        this.mGeometries.remove(geometry);
    }

    /**
     * Returns a list of all the {@link Geometry} contained within this geometry
     * collection.
     *
     * @return a list of all the {@link Geometry} in this geometry collection.
     */
    public List<Geometry> getGeometries() {
        return this.mGeometries;
    }

    /**
     * Sets the list of geometries contained within this geometry collection.
     * All previously existing geometries are removed as a result of setting
     * this property.
     *
     * @param geometries
     */
    public void setGeometries(List<Geometry> geometries) {
        this.mGeometries.clear();
        if (geometries != null) {
            this.mGeometries.addAll(geometries);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return GeoJSON.TYPE_GEOMETRY_COLLECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        JSONArray geometries = new JSONArray();
        for (Geometry geometry : this.mGeometries) {
            geometries.put(geometry.toJSON());
        }

        json.put(JSON_GEOMETRIES, geometries);

        return json;
    }
}
