package com.waracle.androidtest.tasks;


import android.os.AsyncTask;

import com.waracle.androidtest.LoadingCapable;
import com.waracle.androidtest.tasks.tools.DataLoader;
import com.waracle.androidtest.tasks.tools.JsonLoader;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public class DataLoadAsyncTask<T extends JSONArray> extends AsyncTask<String, Void, T> {

    private final DataLoader<String, T> dataLoader;
    private final WeakReference<LoadingCapable<T>> dataLoadingFragmentWeakReference;

    public DataLoadAsyncTask(LoadingCapable<T> loadingCapable) {
        dataLoader = new JsonLoader<>(); //TODO Must go away with IoC usage
        dataLoadingFragmentWeakReference = new WeakReference<>(loadingCapable);
    }

    @Override
    protected T doInBackground(String... urls) {
        // TODO process cancellation
        if (urls == null) {
            throw new IllegalStateException("Url cannot be null");
        }
        if (urls.length != 1) {
            throw new IllegalStateException("Only one url is expected");
        }
        final T data = dataLoader.load(urls[0]);
        return data;
    }

    @Override
    protected void onPostExecute(T data) {
        super.onPostExecute(data);

        final LoadingCapable<T> loadingFragment = dataLoadingFragmentWeakReference.get();

        if (loadingFragment == null) {
            return;
        }
        loadingFragment.onLoad(data);
    }
}
