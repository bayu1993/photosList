package io.github.bayu1993.photosapps.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bayu teguh pamuji on 10/4/18.
 * email : bayuteguhpamuji@gmail.com.
 */
public class PhotoModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
