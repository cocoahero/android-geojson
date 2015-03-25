package com.cocoahero.android.geojson;

import android.location.Location;
import android.test.AndroidTestCase;

import org.json.JSONArray;

public class PositionTest extends AndroidTestCase {
    public void testPrimitiveConstructor() throws Exception {
        Position position;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 10.00;

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

    public void testLocationConstructor() throws Exception {
        Position position;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 10.00;

        // Test 2D Location Constructor
        Location location2D = new Location("Test");
        location2D.setLatitude(lat);
        location2D.setLongitude(lon);

        position = new Position(location2D);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertFalse(position.hasAltitude());

        // Test 3D Location Constructor
        Location location3D = new Location("Test");
        location3D.setLatitude(lat);
        location3D.setLongitude(lon);
        location3D.setAltitude(alt);

        position = new Position(location3D);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertEquals(alt, position.getAltitude(), 0);
        assertTrue(position.hasAltitude());
    }

    public void testJSONArrayConstructor() throws Exception {
        Position position;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 10.00;

        // Test 2D JSONArray Constructor
        JSONArray array2D = new JSONArray();
        array2D.put(0, lon);
        array2D.put(1, lat);

        position = new Position(array2D);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertFalse(position.hasAltitude());

        // Test 3D JSONArray Constructor
        JSONArray array3D = new JSONArray();
        array3D.put(0, lon);
        array3D.put(1, lat);
        array3D.put(2, alt);

        position = new Position(array3D);
        assertEquals(lat, position.getLatitude(), 0);
        assertEquals(lon, position.getLongitude(), 0);
        assertEquals(alt, position.getAltitude(), 0);
        assertTrue(position.hasAltitude());
    }

    public void testGeoJSONFormatting() throws Exception {
        JSONArray json;

        double lat = 12.34;
        double lon = 56.78;
        double alt = 10.00;

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