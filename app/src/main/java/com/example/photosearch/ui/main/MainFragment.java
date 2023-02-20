package com.example.photosearch.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.leanback.app.VerticalGridSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.VerticalGridPresenter;

import com.example.photosearch.R;
import com.example.photosearch.remote.response.FlickrPhoto;
import com.example.photosearch.remote.response.FlickrResponse;
import com.example.photosearch.repository.FlickrRepository;
import com.example.photosearch.ui.search.SearchActivity;
import com.example.photosearch.ui.shared.PhotoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends VerticalGridSupportFragment {

    private int NUM_COLUMNS = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        gridPresenter.setShadowEnabled(false);
        setGridPresenter(gridPresenter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitle(getString(R.string.app_title));

        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });

        setSearchAffordanceColor(ContextCompat.getColor(getActivity(), R.color.searchIconColor));

        Call<FlickrResponse> flickrResponse = FlickrRepository.getRecent(getString(R.string.flickr_api_key));

        flickrResponse.enqueue(new FlickCallback());

    }

    public void displayData(FlickrResponse response) {

        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new PhotoPresenter());

        for (FlickrPhoto flickrPhoto : response.getPhotos().getPhoto()) {
            listRowAdapter.add(flickrPhoto.toPhotoEntity());
        }

        setAdapter(listRowAdapter);
    }

    class FlickCallback implements Callback<FlickrResponse> {

        @Override
        public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
            if (response.isSuccessful()) {
                displayData(response.body());
            }
        }

        @Override
        public void onFailure(Call<FlickrResponse> call, Throwable t) {
        }
    }


}
