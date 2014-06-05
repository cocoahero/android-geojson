package com.cocoahero.android.geojson;

import org.json.JSONArray;

import android.os.Parcel;
import android.os.Parcelable;

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

    protected Ring(Parcel parcel) {
        super(parcel);
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<Ring> CREATOR = new Parcelable.Creator<Ring>() {
        @Override
        public Ring createFromParcel(Parcel in) {
            return new Ring(in);
        }

        @Override
        public Ring[] newArray(int size) {
            return new Ring[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public void close() {
        if (!this.isLinearRing()) {
            this.addPosition(this.getHead());
        }
    }

}
