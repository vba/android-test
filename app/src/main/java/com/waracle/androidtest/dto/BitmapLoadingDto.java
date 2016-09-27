package com.waracle.androidtest.dto;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class BitmapLoadingDto {
    private final ImageView imageView;
    private final Bitmap bitmap;

    public BitmapLoadingDto(ImageView imageView, Bitmap bitmap) {
        this.imageView = imageView;
        this.bitmap = bitmap;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
