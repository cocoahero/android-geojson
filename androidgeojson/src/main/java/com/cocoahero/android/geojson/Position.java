package com.cocoahero.android.geojson;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

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
        this.mStorage[LON_IDX] = array.optDouble(LON_IDX, 0);
        this.mStorage[LAT_IDX] = array.optDouble(LAT_IDX, 0);
        this.mStorage[ALT_IDX] = array.optDouble(ALT_IDX, 0);
    }

    public Position(double[] array) {
        if (array.length == 2) {
            this.mStorage[LON_IDX] = array[LON_IDX];
            this.mStorage[LAT_IDX] = array[LAT_IDX];
        }
        else if (array.length == 3) {
            this.mStorage[LON_IDX] = array[LON_IDX];
            this.mStorage[LAT_IDX] = array[LAT_IDX];
            this.mStorage[ALT_IDX] = array[ALT_IDX];
        }
    }

    public Position(double latitude, double longitude) {
        this.mStorage[LAT_IDX] = latitude;
        this.mStorage[LON_IDX] = longitude;
    }

    public Position(double latitude, double longitude, double altitude) {
        this.mStorage[LAT_IDX] = latitude;
        this.mStorage[LON_IDX] = longitude;
        this.mStorage[ALT_IDX] = altitude;
    }

    public Position(Location location) {
        this.mStorage[LAT_IDX] = location.getLatitude();
        this.mStorage[LON_IDX] = location.getLongitude();
        this.mStorage[ALT_IDX] = location.getAltitude();
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
        dest.writeDoubleArray(this.mStorage);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public double getLatitude() {
        return this.mStorage[LAT_IDX];
    }

    public void setLatitude(double latitude) {
        this.mStorage[LAT_IDX] = latitude;
    }

    public double getLongitude() {
        return this.mStorage[LON_IDX];
    }

    public void setLongitude(double longitude) {
        this.mStorage[LON_IDX] = longitude;
    }

    public double getAltitude() {
        return this.mStorage[ALT_IDX];
    }

    public void setAltitude(double altitude) {
        this.mStorage[ALT_IDX] = altitude;
    }

    public JSONArray toJSON() throws JSONException {
        JSONArray coordinates = new JSONArray();

        coordinates.put(LAT_IDX, this.getLatitude());
        coordinates.put(LON_IDX, this.getLongitude());
        coordinates.put(ALT_IDX, this.getAltitude());

        return coordinates;
    }

    public Location toLocation() {
        Location location = new Location("GeoJSON");

        location.setLatitude(this.getLatitude());
        location.setLongitude(this.getLongitude());
        location.setAltitude(this.getAltitude());

        return location;
    }

    public double[] toArray() {
        return this.mStorage;
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

        return Arrays.equals(this.mStorage, aPosition.mStorage);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.mStorage);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.mStorage);
    }

}
