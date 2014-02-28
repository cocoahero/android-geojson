package com.cocoahero.android.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiLineString extends Geometry {

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final List<LineString> mLineStrings = new ArrayList<LineString>();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public MultiLineString() {
        // Default Constructor
    }

    public MultiLineString(JSONObject json) {
        super(json);

        this.setLineStrings(json.optJSONArray(JSON_COORDINATES));
    }

    public MultiLineString(JSONArray lineStrings) {
        this.setLineStrings(lineStrings);
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<MultiLineString> CREATOR = new Creator<MultiLineString>() {
        @Override
        public MultiLineString createFromParcel(Parcel in) {
            return (MultiLineString) readParcel(in);
        }

        @Override
        public MultiLineString[] newArray(int size) {
            return new MultiLineString[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public void addLineString(LineString lineString) {
        this.mLineStrings.add(lineString);
    }

    public void removeLineString(LineString lineString) {
        this.mLineStrings.remove(lineString);
    }

    public List<LineString> getLineStrings() {
        return this.mLineStrings;
    }

    public void setLineStrings(JSONArray lineStrings) {
        this.mLineStrings.clear();
        if (lineStrings != null) {
            for (int i = 0; i < lineStrings.length(); i++) {
                JSONArray lineJSON = lineStrings.optJSONArray(i);
                if (lineJSON != null) {
                    this.mLineStrings.add(new LineString(lineJSON));
                }
            }
        }
    }

    public void setLineStrings(List<LineString> lineStrings) {
        this.mLineStrings.clear();
        if (lineStrings != null) {
            this.mLineStrings.addAll(lineStrings);
        }
    }

    @Override
    public String getType() {
        return GeoJSON.TYPE_MULTI_LINE_STRING;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        JSONArray strings = new JSONArray();
        for (LineString line : this.mLineStrings) {
            JSONArray lineJSON = new JSONArray();
            for (Position position : line.getPositions()) {
                lineJSON.put(position.toJSON());
            }
            strings.put(lineJSON);
        }
        json.put(JSON_COORDINATES, strings);

        return json;
    }

}
