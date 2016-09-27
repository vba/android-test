package com.waracle.androidtest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waracle.androidtest.tasks.DataLoadAsyncTask;

import org.json.JSONArray;

/**
 * Fragment is responsible for loading in some JSON and
 * then displaying a list of cakes with images.
 * Fix any crashes
 * Improve any performance issues
 * Use good coding practices to make code more secure
 */
public class DataLoadPlaceholderFragment extends ListFragment
        implements LoadingCapable<JSONArray>, FragmentActivityProvider {

    private static final String JSON_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/" +
            "raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final DataLoadAsyncTask<JSONArray> dataLoadAsyncTask = new DataLoadAsyncTask<>(this);
        dataLoadAsyncTask.execute(JSON_URL);
    }

    @Override
    public void onLoad(JSONArray data) {
        final DataLoadListAdapter adapter = new DataLoadListAdapter(data, this);
        getListView().setAdapter(adapter);
    }

    @Override
    public FragmentActivity provide() {
        return getActivity();
    }

}
