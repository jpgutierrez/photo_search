package com.example.photosearch.remote;

import com.example.photosearch.remote.response.FlickrResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    @GET("services/rest?extras=url_q,owner_name,date_upload&format=json&nojsoncallback=1")
    Call<FlickrResponse> callFlickrApi(@Query("method") String method,
                                       @Query("api_key") String apiKey,
                                       @Query("text") String text,
                                       @Query("page") Integer page,
                                       @Query("per_page") Integer perPage);


}
