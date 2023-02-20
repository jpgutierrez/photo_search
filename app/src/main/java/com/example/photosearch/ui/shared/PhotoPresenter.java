package com.example.photosearch.ui.shared;

import android.graphics.Color;
import android.view.ViewGroup;

import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.example.photosearch.entity.Photo;

public class PhotoPresenter extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageCardView cardView = new ImageCardView(parent.getContext());
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setInfoAreaBackgroundColor(Color.TRANSPARENT);
        cardView.setMainImageDimensions(550, 300);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Photo photo = (Photo) item;
        ImageCardView cardView = (ImageCardView) viewHolder.view;

        cardView.setTitleText(photo.getTitle());
        cardView.setContentText(photo.getOwner() + " / " + photo.getDate());

        Glide.with(cardView.getMainImageView())
                .load(photo.getUrl())
                .centerCrop()
                .into(cardView.getMainImageView());

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        cardView.setMainImage(null);
    }

}