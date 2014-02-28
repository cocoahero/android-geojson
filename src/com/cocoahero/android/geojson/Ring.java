package com.cocoahero.android.geojson;

import org.json.JSONArray;

public class Ring extends PositionList {

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public Ring() {
        // Default Constructor
    }

    public Ring(JSONArray positions) {
        super(positions);
    }

    public Ring(double[][] positions) {
        super(positions);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public void close() {
        if (!this.isLinearRing()) {
            this.addPosition(this.getHead());
        }
    }

}
