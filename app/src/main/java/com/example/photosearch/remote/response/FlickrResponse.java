package com.example.photosearch.remote.response;

public class FlickrResponse {
    private FlickrPhotoList photos;
    private String stat;

    public FlickrPhotoList getPhotos() {
        return photos;
    }

    public void setPhotos(FlickrPhotoList photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}

