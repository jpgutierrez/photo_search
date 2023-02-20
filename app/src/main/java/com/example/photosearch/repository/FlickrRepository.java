package com.example.photosearch.repository;

import com.example.photosearch.remote.FlickrConnection;
import com.example.photosearch.remote.response.FlickrResponse;

import retrofit2.Call;

public class FlickrRepository {

    public static Call<FlickrResponse> getRecent(String apiKey) {
        return FlickrConnection.getFlickrService().callFlickrApi("flickr.photos.getRecent",
                apiKey, "", 1, 50);
    }

    public static Call<FlickrResponse> search(String apiKey, String searchText) {
        return FlickrConnection.getFlickrService().callFlickrApi("flickr.photos.search",
                apiKey, searchText, 1, 50);
    }
}
