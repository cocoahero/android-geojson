package com.cocoahero.android.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Polygon extends Geometry {

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final List<Ring> mRings = new ArrayList<Ring>();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public Polygon() {
        // Default Constructor
    }

    public Polygon(Ring ring) {
        this.addRing(ring);
    }

    public Polygon(JSONObject json) {
        super(json);

        this.setRings(json.optJSONArray(JSON_COORDINATES));
    }

    public Polygon(JSONArray rings) {
        this.setRings(rings);
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<Polygon> CREATOR = new Creator<Polygon>() {
        @Override
        public Polygon createFromParcel(Parcel in) {
            return (Polygon) readParcel(in);
        }

        @Override
        public Polygon[] newArray(int size) {
            return new Polygon[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public void addRing(Ring ring) {
        this.mRings.add(ring);
    }

    public void removeRing(Ring ring) {
        this.mRings.remove(ring);
    }

    public List<Ring> getRings() {
        return this.mRings;
    }

    public void setRings(JSONArray rings) {
        this.mRings.clear();
        if (rings != null) {
            for (int i = 0; i < rings.length(); i++) {
                JSONArray ringJSON = rings.optJSONArray(i);
                if (ringJSON != null) {
                    this.mRings.add(new Ring(ringJSON));
                }
            }
        }
    }

    public void setRings(List<Ring> rings) {
        this.mRings.clear();
        if (rings != null) {
            this.mRings.addAll(rings);
        }
    }

    @Override
    public String getType() {
        return GeoJSON.TYPE_POLYGON;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        JSONArray rings = new JSONArray();
        for (Ring ring : this.mRings) {
            rings.put(ring.toJSON());
        }
        json.put(JSON_COORDINATES, rings);

        return json;
    }

}
