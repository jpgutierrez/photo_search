package com.example.photosearch.remote.response;

import java.util.List;

public class FlickrPhotoList {
    private List<FlickrPhoto> photo;

    public List<FlickrPhoto> getPhoto() {
        return photo;
    }

    public void setPhoto(List<FlickrPhoto> photo) {
        this.photo = photo;
    }
}
