package com.aranguriapps.joni.melisearchapp.domain;

import com.aranguriapps.joni.melisearchapp.io.api.Constants;
import com.google.gson.annotations.SerializedName;

public class MeliSearchImage {
    @SerializedName(Constants.WIDTH)
    int width;

    @SerializedName(Constants.HEIGHT)
    int height;

    @SerializedName(Constants.URL)
    String url;
}
