package com.cocoahero.android.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

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

    public GeometryCollection() {
        // Default Constructor
    }

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

    public void addGeometry(Geometry geometry) {
        this.mGeometries.add(geometry);
    }

    public void removeGeometry(Geometry geometry) {
        this.mGeometries.remove(geometry);
    }

    public List<Geometry> getGeometries() {
        return this.mGeometries;
    }

    public void setGeometries(List<Geometry> geometries) {
        this.mGeometries.clear();
        if (geometries != null) {
            this.mGeometries.addAll(geometries);
        }
    }

    @Override
    public String getType() {
        return GeoJSON.TYPE_GEOMETRY_COLLECTION;
    }

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
