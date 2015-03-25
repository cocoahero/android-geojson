package com.cocoahero.android.geojson;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionTest {
    @Test
    public void constructorsShouldWork() throws Exception {
        Position position;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 91.01;

        // Test 2D Primitive Constructor
        position = new Position(lat, lon);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertFalse(position.hasAltitude());

        // Test 3D Primitive Constructor
        position = new Position(lat, lon, alt);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertEquals(alt, position.getAltitude(), 0);
        assertTrue(position.hasAltitude());
    }

    @Test
    public void shouldReturnValidGeoJSON() throws Exception {
        JSONArray json;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 91.01;

        // Test 2D Position
        json = new Position(lat, lon).toJSON();
        assertEquals(2, json.length());
        assertEquals(lon, json.getDouble(0), 0);
        assertEquals(lat, json.getDouble(1), 0);

        // Test 3D Position
        json = new Position(lat, lon, alt).toJSON();
        assertEquals(3, json.length());
        assertEquals(lon, json.getDouble(0), 0);
        assertEquals(lat, json.getDouble(1), 0);
        assertEquals(alt, json.getDouble(2), 0);
    }
}
