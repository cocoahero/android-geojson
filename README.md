# Android GeoJSON

A complete GeoJSON implementation for Android.

### Table of Contents

* [Requirements](#requirements)
* [Installation](#installation)
* [Sample Usage](#sample-usage)
  * [Parsing GeoJSON](#parsing-geojson)
  * [Creating GeoJSON](#creating-geojson)

### Requirements
* Android SDK 8 or Higher

### Installation

#### Android Studio / Gradle

````groovy
dependencies {
  compile 'com.cocoahero.android:geojson:1.0.1@jar'
}
````

#### Maven

````xml
<dependency>
  <groupId>com.cocoahero.android</groupId>
  <artifactId>geojson</artifactId>
  <version>1.0.1</version>
  <type>jar</type>
</dependency>
````

### Sample Usage

Whether you need to parse existing GeoJSON from a file or web server, or create new GeoJSON from user input, this library has got you covered.

#### Parsing GeoJSON

If you have existing GeoJSON that you need to parse, you have three source options with this library:

1. String
2. JSONObject
3. InputStream

Once you have your GeoJSON in one of the above formats, simply pass it to `GeoJSON#parse`.

##### String
````java
String string; // A string containing GeoJSON

try {
    GeoJSONObject geoJSON = GeoJSON.parse(string);
}
catch (JSONException e) {
    e.printStackTrace();
}
````

##### JSONObject
````java
JSONObject json; // A JSONObject formatted as GeoJSON

GeoJSONObject geoJSON = GeoJSON.parse(json);
````

##### InputStream
````java
InputStream stream; // An InputStream pointing to GeoJSON

try {
    GeoJSONObject geoJSON = GeoJSON.parse(stream);
}
catch (IOException e) {
    e.printStackTrace();
}
catch (JSONException e) {
    e.printStackTrace();
}
````

The returned object instance will be a subclass of [GeoJSONObject](./androidgeojson/src/main/java/com/cocoahero/android/geojson/GeoJSONObject.java), depending on the `type` property of the GeoJSON.

* `"type": "Feature"` => [Feature](./androidgeojson/src/main/java/com/cocoahero/android/geojson/Feature.java)
* `"type": "FeatureCollection"` => [FeatureCollection](./androidgeojson/src/main/java/com/cocoahero/android/geojson/FeatureCollection.java)
* `"type": "Point"` => [Point](./androidgeojson/src/main/java/com/cocoahero/android/geojson/Point.java)
* `"type": "MultiPoint"` => [MultiPoint](./androidgeojson/src/main/java/com/cocoahero/android/geojson/MultiPoint.java)
* `"type": "LineString"` => [LineString](./androidgeojson/src/main/java/com/cocoahero/android/geojson/LineString.java)
* `"type": "MultiLineString"` => [MultiLineString](./androidgeojson/src/main/java/com/cocoahero/android/geojson/MultiLineString.java)
* `"type": "Polygon"` => [Polygon](./androidgeojson/src/main/java/com/cocoahero/android/geojson/Polygon.java)
* `"type": "MultiPolygon"` => [MultiPolygon](./androidgeojson/src/main/java/com/cocoahero/android/geojson/MultiPolygon.java)
* `"type": "GeometryCollection"` => [GeometryCollection](./androidgeojson/src/main/java/com/cocoahero/android/geojson/GeometryCollection.java)

#### Creating GeoJSON

Parsing existing GeoJSON is only half the fun! Why not create new GeoJSON?! Simply create a new instance of which ever GeoJSONObject sub-type you would like, then call `#toJSON` on it to get a properly formatted `JSONObject` instance.

For example, the following sample code creates a GeoJSON Feature with a Point geometry.

````java
// Create geometry
Point point = new Point(38.889462878011365, -77.03525304794312);

// Create feature with geometry
Feature feature = new Feature(point);

// Set optional feature identifier
feature.setIdentifier("MyIdentifier");

// Set optional feature properties
feature.setProperties(new JSONObject());

// Convert to formatted JSONObject
JSONObject geoJSON = feature.toJSON();
````

The resulting GeoJSON can be seen [here](https://gist.github.com/cocoahero/7ce6bc203d47d7a64438#file-sample-feature-geojson).
