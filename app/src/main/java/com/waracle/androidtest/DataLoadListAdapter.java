package com.waracle.androidtest;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waracle.androidtest.dto.BitmapLoadingDto;
import com.waracle.androidtest.tasks.BitmapLoadAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

class DataLoadListAdapter extends BaseAdapter implements LoadingCapable<BitmapLoadingDto> {
    private final String TAG = DataLoadListAdapter.class.getSimpleName();
    private final String TITLE_KEY = "title";
    private final String DESCRIPTION_KEY = "desc";
    private final String IMAGE_KEY = "image";

    private final JSONArray loadedDataItems; // TODO use a real POJO/DTO object to represent data
    private final WeakReference<FragmentActivityProvider> fragmentActivityProviderWeakReference;

    DataLoadListAdapter(JSONArray items, FragmentActivityProvider activityProvider) {
        loadedDataItems = items;
        fragmentActivityProviderWeakReference = new WeakReference<>(activityProvider);
    }

    @Override
    public int getCount() {
        return loadedDataItems.length();
    }

    @Override
    @Nullable
    public Object getItem(int position) {
        try {
            return loadedDataItems.getJSONObject(position);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    @Nullable
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = getRoot(parent);
        if (root == null) {
            return null;
        }
        TextView title = (TextView) root.findViewById(R.id.title);
        TextView desc = (TextView) root.findViewById(R.id.desc);
        ImageView imageView = (ImageView) root.findViewById(R.id.image);

        bindData(position, title, desc, imageView);

        return root;
    }

    private void bindData(int position, TextView title, TextView desc, ImageView imageView) {
        try {
            JSONObject jsonObject = (JSONObject) getItem(position); // TODO see comment about pojo
            if (jsonObject == null) {
                return;
            }
            title.setText(jsonObject.getString(TITLE_KEY));
            desc.setText(jsonObject.getString(DESCRIPTION_KEY));
            new BitmapLoadAsyncTask(this, imageView).execute(jsonObject.getString(IMAGE_KEY));
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Nullable
    private View getRoot(ViewGroup parent) {
        final FragmentActivityProvider activityProvider = fragmentActivityProviderWeakReference.get();
        if (activityProvider == null) {
            return null;
        }
        LayoutInflater inflater = LayoutInflater.from(activityProvider.provide());
        return inflater.inflate(R.layout.list_item_layout, parent, false);
    }

    @Override
    public void onLoad(BitmapLoadingDto bitmapLoadingDto) {
        if (bitmapLoadingDto.getBitmap() == null) {
            return;
        } // TODO add placeholder when bitmap is null
        bitmapLoadingDto.getImageView().setImageBitmap(bitmapLoadingDto.getBitmap());
    }
}
