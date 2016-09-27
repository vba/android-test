package com.waracle.androidtest.tools;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    private static final String TAG = StreamUtils.class.getSimpleName();
    private static final int BUFFER_SIZE = 16384;

    public static InputStream clone(InputStream source) throws IOException {
        ByteArrayOutputStream buffer = getByteArrayOutputStream(source);
        return new ByteArrayInputStream(buffer.toByteArray());
    }

    @NonNull
    private static ByteArrayOutputStream getByteArrayOutputStream(InputStream source) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int read;
        byte[] data = new byte[BUFFER_SIZE];

        while ((read = source.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, read);
        }

        buffer.flush();
        return buffer;
    }

    public static byte[] readUnknownFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = getByteArrayOutputStream(inputStream);
        return buffer.toByteArray();
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
