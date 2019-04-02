package com.aranguriapps.joni.melisearchapp.domain;

import com.aranguriapps.joni.melisearchapp.io.api.Constants;
import com.google.gson.annotations.SerializedName;

public class MeliSearchImage {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("id")
    String id;



    @SerializedName(Constants.URL)
    String url;


}
