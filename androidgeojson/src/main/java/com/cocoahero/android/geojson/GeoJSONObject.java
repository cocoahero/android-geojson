package com.cocoahero.android.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The abstract interface of all GeoJSON objects.
 */
public abstract class GeoJSONObject implements Parcelable {

    // ------------------------------------------------------------------------
    // Public Constants
    // ------------------------------------------------------------------------

    public static final String JSON_TYPE = "type";

    // ------------------------------------------------------------------------
    // Class Methods
    // ------------------------------------------------------------------------

    protected static GeoJSONObject readParcel(Parcel parcel) {
        String json = parcel.readString();
        try {
            return GeoJSON.parse(json);
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // ------------------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------------------

    public GeoJSONObject() {
        // Default Constructor
    }

    public GeoJSONObject(JSONObject json) {

    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<GeoJSONObject> CREATOR = new Creator<GeoJSONObject>() {
        @Override
        public GeoJSONObject createFromParcel(Parcel in) {
            return readParcel(in);
        }

        @Override
        public GeoJSONObject[] newArray(int size) {
            return new GeoJSONObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        try {
            dest.writeString(this.toJSON().toString());
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    /**
     * The type of GeoJSON object. This will be one of the constants defined in
     * {@link GeoJSON}, such as {@link GeoJSON#TYPE_FEATURE}.
     *
     * @return The type of GeoJSON object.
     */
    public abstract String getType();

    /**
     * Formats the object's attributes as GeoJSON.
     *
     * @return A GeoJSON formatted {@link JSONObject}
     *
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_TYPE, this.getType());

        return json;
    }

}
