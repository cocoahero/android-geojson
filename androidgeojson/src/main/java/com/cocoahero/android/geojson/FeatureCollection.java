package com.cocoahero.android.geojson;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeatureCollection extends GeoJSONObject {

    // ------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------

    public static final String JSON_FEATURES = "features";

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final List<Feature> mFeatures = new ArrayList<Feature>();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    /**
     * Creates an empty feature collection.
     */
    public FeatureCollection() {
        // Default Constructor
    }

    /**
     * Parses the given {@link JSONObject} as a feature collection.
     *
     * @param json
     */
    public FeatureCollection(JSONObject json) {
        super(json);

        JSONArray features = json.optJSONArray(JSON_FEATURES);
        if (features != null) {
            for (int i = 0; i < features.length(); i++) {
                JSONObject featureJSON = features.optJSONObject(i);
                if (featureJSON != null) {
                    this.mFeatures.add(new Feature(featureJSON));
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<FeatureCollection> CREATOR = new Creator<FeatureCollection>() {
        @Override
        public FeatureCollection createFromParcel(Parcel in) {
            return (FeatureCollection) readParcel(in);
        }

        @Override
        public FeatureCollection[] newArray(int size) {
            return new FeatureCollection[size];
        }
    };

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    /**
     * Adds a {@link Feature} to this feature collection.
     *
     * @param feature
     */
    public void addFeature(Feature feature) {
        this.mFeatures.add(feature);
    }

    /**
     * Removes a given {@link Feature} from this feature collection.
     *
     * @param feature
     */
    public void removeFeature(Feature feature) {
        this.mFeatures.remove(feature);
    }

    /**
     * Returns a list of all the {@link Feature}s contained within this
     * collection.
     *
     * @return the list of all {@link Feature}s in this collection
     */
    public List<Feature> getFeatures() {
        return this.mFeatures;
    }

    /**
     * Sets the list of features contained within this feature collection. All
     * previously existing features are removed as a result of setting this
     * property.
     *
     * @param features
     */
    public void setFeatures(List<Feature> features) {
        this.mFeatures.clear();
        if (features != null) {
            this.mFeatures.addAll(features);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return GeoJSON.TYPE_FEATURE_COLLECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();

        JSONArray features = new JSONArray();
        for (Feature feature : this.mFeatures) {
            features.put(feature.toJSON());
        }

        json.put(JSON_FEATURES, features);

        return json;
    }

}
