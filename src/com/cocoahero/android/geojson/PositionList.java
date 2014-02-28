package com.cocoahero.android.geojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Parcel;
import android.os.Parcelable;

import com.cocoahero.android.geojson.util.ListUtils;

public class PositionList implements Parcelable {

    // ------------------------------------------------------------------------
    // Instance Variables
    // ------------------------------------------------------------------------

    private final List<Position> mPositions = new ArrayList<Position>();

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public PositionList() {
        // Default Constructor
    }

    public PositionList(JSONArray positions) {
        this.setPositions(positions);
    }

    public PositionList(double[][] positions) {
        for (int i = 0; i < positions.length; i++) {
            this.addPosition(new Position(positions[i]));
        }
    }

    protected PositionList(Parcel parcel) {
        this.setPositions(parcel.createTypedArrayList(Position.CREATOR));
    }

    // ------------------------------------------------------------------------
    // Parcelable Interface
    // ------------------------------------------------------------------------

    public static final Parcelable.Creator<PositionList> CREATOR = new Parcelable.Creator<PositionList>() {
        @Override
        public PositionList createFromParcel(Parcel in) {
            return new PositionList(in);
        }

        @Override
        public PositionList[] newArray(int size) {
            return new PositionList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mPositions);
    }

    // ------------------------------------------------------------------------
    // Public Methods
    // ------------------------------------------------------------------------

    public List<Position> getPositions() {
        return this.mPositions;
    }

    public void addPosition(Position position) {
        this.mPositions.add(position);
    }

    public void addPositions(PositionList positions) {
        this.mPositions.addAll(positions.mPositions);
    }

    public void addPositions(List<Position> positions) {
        this.mPositions.addAll(positions);
    }

    public void removePosition(Position position) {
        this.mPositions.remove(position);
    }

    public void removePositions(PositionList positions) {
        this.mPositions.removeAll(positions.mPositions);
    }

    public void removePositions(List<Position> positions) {
        this.mPositions.removeAll(positions);
    }

    public void clearPositions() {
        this.mPositions.clear();
    }

    public void setPositions(JSONArray positions) {
        this.mPositions.clear();
        if (positions != null) {
            for (int i = 0; i < positions.length(); i++) {
                JSONArray position = positions.optJSONArray(i);
                if (position != null) {
                    this.mPositions.add(new Position(position));
                }
            }
        }
    }

    public void setPositions(PositionList positions) {
        this.mPositions.clear();
        if (positions != null) {
            this.mPositions.addAll(positions.mPositions);
        }
    }

    public void setPositions(List<Position> positions) {
        this.mPositions.clear();
        if (positions != null) {
            this.mPositions.addAll(positions);
        }
    }

    public Position getHead() {
        return ListUtils.getHead(this.mPositions);
    }

    public Position getTail() {
        return ListUtils.getTail(this.mPositions);
    }

    public boolean isLinearRing() {
        if (this.mPositions.size() < 4) {
            return false;
        }

        Position head = this.getHead();
        Position tail = this.getTail();

        return head.equals(tail);
    }

    public JSONArray toJSON() throws JSONException {
        JSONArray positions = new JSONArray();

        for (Position position : this.mPositions) {
            positions.put(position.toJSON());
        }

        return positions;
    }

}
