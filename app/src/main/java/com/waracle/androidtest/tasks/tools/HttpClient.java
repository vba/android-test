package com.waracle.androidtest.tasks.tools;

import android.support.annotation.Nullable;
import android.util.Log;

import com.waracle.androidtest.tools.StreamUtils;
import com.waracle.androidtest.dto.Response;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements Client {

    private final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String TAG = HttpClient.class.getSimpleName();
    private final int HTTP_STATUS_OK = 200;

    // TODO Improve it with HTTP compression (https://en.wikipedia.org/wiki/HTTP_compression), better way to do it, would be the usage of a library such AsyncHttpClient
    @Override
    @Nullable
    public Response get(String resource) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(resource);

            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HTTP_STATUS_OK) { // TODO work with not success cases in more gentile manner
                return null; // TODO null is bad, use optional types
            }

            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            return new Response(StreamUtils.clone(inputStream), urlConnection.getRequestProperty(CONTENT_TYPE_KEY));

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new IllegalStateException(e); // TODO It smells bad, stacktrace breaking, do better with 3rd libs
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            StreamUtils.close(inputStream);
        }
    }

}
