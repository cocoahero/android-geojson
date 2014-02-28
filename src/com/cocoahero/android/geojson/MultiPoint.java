package com.cocoahero.android.geojson;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MultiPoint extends Geometry {

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final PositionList mPositionList = new PositionList();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public MultiPoint() {
        // Default Constructor
    }

    public MultiPoint(JSONObject json) {
        super(json);

        this.setPositions(json.optJSONArray(JSON_COORDINATES));
    }

    public MultiPoint(JSONArray positions) {
        this.setPositions(positions);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public void addPosition(Position position) {
        this.mPositionList.addPosition(position);
    }

    public void removePosition(Position position) {
        this.mPositionList.removePosition(position);
    }

    public List<Position> getPositions() {
        return this.mPositionList.getPositions();
    }

    public void setPositions(JSONArray positions) {
        this.mPositionList.setPositions(positions);
    }

    public void setPositions(List<Position> positions) {
        this.mPositionList.setPositions(positions);
    }

    @Override
    public String getType() {
        return GeoJSON.TYPE_MULTI_POINT;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        json.put(JSON_COORDINATES, this.mPositionList.toJSON());

        return json;
    }

}
