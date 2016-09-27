package com.waracle.androidtest.tasks;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.waracle.androidtest.LoadingCapable;
import com.waracle.androidtest.dto.BitmapLoadingDto;
import com.waracle.androidtest.tasks.tools.BitmapLoader;
import com.waracle.androidtest.tasks.tools.DataLoader;

import java.lang.ref.WeakReference;

public class BitmapLoadAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<LoadingCapable<BitmapLoadingDto>> loadingCapableWeakReference;
    private final DataLoader<String, Bitmap> dataLoader;
    private final WeakReference<ImageView> imageViewWeakReference;

    public BitmapLoadAsyncTask(LoadingCapable<BitmapLoadingDto> loadingCapable, ImageView imageView) {
        this.loadingCapableWeakReference = new WeakReference<>(loadingCapable);
        this.imageViewWeakReference = new WeakReference<>(imageView);
        this.dataLoader = new BitmapLoader(); // TODO use IoC instead
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        if (urls == null) {
            throw new IllegalStateException("Url cannot be null");
        }
        if (urls.length != 1) {
            throw new IllegalStateException("Only one url is expected");
        }
        return dataLoader.load(urls[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        // TODO process cancellation

        final LoadingCapable<BitmapLoadingDto> loadingCapable = loadingCapableWeakReference.get();
        final ImageView imageView = imageViewWeakReference.get();
        if (loadingCapable == null || imageView == null) {
            return;
        }
        loadingCapable.onLoad(new BitmapLoadingDto(imageView, bitmap));
    }

}
