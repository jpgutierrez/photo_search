package com.example.photosearch.ui.search;

import android.os.Bundle;

import androidx.leanback.app.SearchSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;

import com.example.photosearch.R;
import com.example.photosearch.remote.response.FlickrPhoto;
import com.example.photosearch.remote.response.FlickrResponse;
import com.example.photosearch.repository.FlickrRepository;
import com.example.photosearch.ui.shared.PhotoPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends SearchSupportFragment implements SearchSupportFragment.SearchResultProvider {

    private ArrayObjectAdapter resultsAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setSearchResultProvider(this);
        setTitle(getString(R.string.app_title));
    }

    @Override
    public ObjectAdapter getResultsAdapter() {
        return resultsAdapter;
    }

    @Override
    public boolean onQueryTextChange(String newQuery) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        Call<FlickrResponse> flickrResponse = FlickrRepository.search(getString(R.string.flickr_api_key), query);

        flickrResponse.enqueue(new FlickCallback(query));

        return true;
    }

    public void displayData(FlickrResponse response, String query) {

        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new PhotoPresenter());

        for (FlickrPhoto flickrPhoto : response.getPhotos().getPhoto()) {

            listRowAdapter.add(flickrPhoto.toPhotoEntity());
        }

        String headerText = "";

        if (response.getPhotos().getPhoto().size() > 0) {
            // has results
            headerText = getString(R.string.foundResults, query);
        } else {
            // no results
            headerText = getString(R.string.notFoundResults, query);
        }

        HeaderItem header = new HeaderItem(headerText);

        resultsAdapter.clear();

        resultsAdapter.add(new ListRow(header, listRowAdapter));

    }

    class FlickCallback implements Callback<FlickrResponse> {

        private String query;

        public FlickCallback(String query) {
            this.query = query;
        }

        @Override
        public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
            if (response.isSuccessful()) {
                displayData(response.body(), query);
            }
        }

        @Override
        public void onFailure(Call<FlickrResponse> call, Throwable t) {
        }
    }
}
