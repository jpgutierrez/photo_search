package com.example.photosearch.remote.response;

import com.example.photosearch.entity.Photo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlickrPhoto {
    private String title;
    private String ownername;

    private Long dateupload;

    private String url_q;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Long getDateupload() {
        return dateupload;
    }

    public void setDateupload(Long dateupload) {
        this.dateupload = dateupload;
    }

    public String getUrl_q() {
        return url_q;
    }

    public void setUrl_q(String url_q) {
        this.url_q = url_q;
    }

    public Photo toPhotoEntity() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        Photo p = new Photo();
        p.setTitle(this.getTitle());
        p.setOwner(this.getOwnername());
        p.setDate(sdf.format(new Date(this.getDateupload() * 1000)));
        p.setUrl(this.getUrl_q());
        return p;
    }

}
