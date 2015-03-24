package com.cocoahero.android.geojson;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

public class Position implements Parcelable {

    // ------------------------------------------------------------------------
    // Private Constants
    // ------------------------------------------------------------------------

    private static final int LON_IDX = 0;

    private static final int LAT_IDX = 1;

    private static final int ALT_IDX = 2;

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final double[] mStorage = new double[3];

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public Position(JSONArray array) {
        mStorage[LON_IDX] = array.optDouble(LON_IDX, 0.0);
        mStorage[LAT_IDX] = array.optDouble(LAT_IDX, 0.0);
        mStorage[ALT_IDX] = array.optDouble(ALT_IDX, Double.NaN);
    }

    public Position(double[] array) {
        mStorage[LON_IDX] = array[LON_IDX];
        mStorage[LAT_IDX] = array[LAT_IDX];
        if (array.length == 3) {
            mStorage[ALT_IDX] = array[ALT_IDX];
        } else {
            mStorage[ALT_IDX] = Double.NaN;
        }
    }

    public Position(double latitude, double longitude) {
        this(latitude, longitude, Double.NaN);
    }

    public Position(double latitude, double longitude, double altitude) {
        mStorage[LAT_IDX] = latitude;
        mStorage[LON_IDX] = longitude;
        mStorage[ALT_IDX] = altitude;
    }

    public Position(Location location) {
        mStorage[LAT_IDX] = location.getLatitude();
        mStorage[LON_IDX] = location.getLongitude();
        if (location.hasAltitude()) {
            mStorage[ALT_IDX] = location.getAltitude();
        } else {
            mStorage[ALT_IDX] = Double.NaN;
        }
    }

    private Position(Parcel parcel) {
        this(parcel.createDoubleArray());
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<Position> CREATOR = new Parcelable.Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDoubleArray(mStorage);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public double getLatitude() {
        return mStorage[LAT_IDX];
    }

    public void setLatitude(double latitude) {
        mStorage[LAT_IDX] = latitude;
    }

    public double getLongitude() {
        return mStorage[LON_IDX];
    }

    public void setLongitude(double longitude) {
        mStorage[LON_IDX] = longitude;
    }

    public double getAltitude() {
        return mStorage[ALT_IDX];
    }

    public void setAltitude(double altitude) {
        mStorage[ALT_IDX] = altitude;
    }

    public boolean hasAltitude() {
        return mStorage[ALT_IDX] != Double.NaN;
    }

    public JSONArray toJSON() throws JSONException {
        JSONArray coordinates = new JSONArray();

        coordinates.put(LAT_IDX, getLatitude());
        coordinates.put(LON_IDX, getLongitude());
        if (hasAltitude()) {
            coordinates.put(ALT_IDX, getAltitude());
        }

        return coordinates;
    }

    public Location toLocation() {
        Location location = new Location("GeoJSON");

        location.setLatitude(getLatitude());
        location.setLongitude(getLongitude());
        if (hasAltitude()) {
            location.setAltitude(getAltitude());
        }

        return location;
    }

    public double[] toArray() {
        if (hasAltitude()) {
            return mStorage.clone();
        }
        return new double[]{mStorage[LON_IDX], mStorage[LAT_IDX]};
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Position)) {
            return false;
        }

        Position aPosition = (Position) object;

        return Arrays.equals(mStorage, aPosition.mStorage);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(mStorage);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}
