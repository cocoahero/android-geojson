package com.cocoahero.android.geojson.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    /**
     * Decodes the contents of the provided {@link InputStream} into a
     * {@link String} using the UTF-8 charset.
     * 
     * @param byteStream The {@link InputStream} to decode.
     * @return A {@link String} representation of the stream's contents.
     * @throws IOException
     */
    public static String toString(InputStream byteStream) throws IOException {
        return toString(byteStream, "UTF-8");
    }

    /**
     * Decodes the contents of the provided {@link InputStream} into a
     * {@link String} using the charset denoted by the charsetName parameter.
     * 
     * @param byteStream The {@link InputStream} to decode.
     * @param charsetName The charset used to decode the stream.
     * @return A {@link String} representation of the stream's contents.
     * @throws IOException
     */
    public static String toString(InputStream byteStream, String charsetName) throws IOException {
        char[] buffer = new char[1024];

        StringBuilder builder = new StringBuilder();

        InputStreamReader reader = new InputStreamReader(byteStream, charsetName);

        for (int length = 0; (length = reader.read(buffer)) >= 0;) {
            builder.append(buffer, 0, length);
        }

        reader.close();

        return builder.toString();
    }

}
