# Android GeoJSON

A complete GeoJSON implementation for Android.

## Requirements
* Android SDK 8 or Higher

## Installation
1. Download or `git clone` this library into your application project.
2. Import the library into your Eclipse workspace.
3. Add the newly imported Android Library Project as a dependency to your application project.

## Sample Usage

### Parsing GeoJSON

If you have existing GeoJSON that you need to parse, you have three source options with this library:

1. String
2. JSONObject
3. InputStream

Once you have your GeoJSON in one of the above formats, simply pass it to `GeoJSON#parse`.

````java
InputStream stream; // An InputStream pointing to GeoJSON

try {
    GeoJSONObject geoJSON; = GeoJSON.parse(stream);
}
catch (IOException e) {
    e.printStackTrace();
}
catch (JSONException e) {
    e.printStackTrace();
}
````

````java
String string; // A string containing GeoJSON

try {
    GeoJSONObject geoJSON; = GeoJSON.parse(string);
}
catch (JSONException e) {
    e.printStackTrace();
}
````

````java
JSONObject json; // A JSONObject formatted as GeoJSON

GeoJSONObject geoJSON; = GeoJSON.parse(json);
````

The returned object instance will be a subclass of GeoJSONObject, depending on the `type` property of the GeoJSON.

* `"type": "Feature"` => [Feature](./blob/master/src/com/cocoahero/android/geojson/Feature.java)
* `"type": "FeatureCollection"` => [FeatureCollection](./blob/master/src/com/cocoahero/android/geojson/FeatureCollection.java)
* `"type": "Point"` => `Point.java`
* `"type": "MultiPoint"` => `MultiPoint.java`
* `"type": "LineString"` => `LineString.java`
* `"type": "MultiLineString"` => `MultiLineString.java`
* `"type": "Polygon"` => `Polygon.java`
* `"type": "MultiPolygon"` => `MultiPolygon.java`
* `"type": "GeometryCollection"` => `GeometryCollection.java`

