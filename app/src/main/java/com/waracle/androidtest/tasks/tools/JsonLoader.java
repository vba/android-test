package com.waracle.androidtest.tasks.tools;


import android.support.annotation.Nullable;
import android.util.Log;

import com.waracle.androidtest.tools.StreamUtils;
import com.waracle.androidtest.dto.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class JsonLoader<T extends JSONArray> implements DataLoader<String, T> {

    private final Client client;
    private final static String TAG = JsonLoader.class.getSimpleName();;

    public JsonLoader() {
        client = new HttpClient(); // TODO inject via IoC
    }

    // TODO Improve it with HTTP compression (https://en.wikipedia.org/wiki/HTTP_compression), better way to do it, would be the usage of a library such AsyncHttpClient
    @Override
    @Nullable
    public T load(final String path) {
        InputStream inputStream = null;
        try {
            final Response response = client.get(path);
            if(response == null) {
                return null;
            }
            inputStream = response.getContent();
            final byte[] bytes = StreamUtils.readUnknownFully(inputStream);
            final String charset = CharsetUtil.extractCharset(response.getContentType());
            final String jsonText = new String(bytes, charset);

            return (T) new JSONArray(jsonText);
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage(), e); // TODO It smells bad, use normal error reporting with 3rd libs
            throw new IllegalStateException(e); // TODO It smells bad, stacktrace breaking, do better with 3rd libs
        } finally {
            StreamUtils.close(inputStream);
        }

    }
}
