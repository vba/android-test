package com.waracle.androidtest.tasks.tools;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.waracle.androidtest.dto.Response;

public class BitmapLoader implements DataLoader<String, Bitmap> {

    private final static String TAG = BitmapLoader.class.getSimpleName();
    private final Client client;

    public BitmapLoader() {
        client = new HttpClient(); // TODO inject via IoC
    }

    @Override
    @Nullable
    public Bitmap load(String path) {

        final Response response = client.get(path);
        if(response == null) {
            return null;
        }

        Bitmap bitmap = BitmapFactory.decodeStream(response.getContent());
        return bitmap;
    }

}
