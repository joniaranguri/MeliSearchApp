package com.aranguriapps.joni.melisearchapp.io.model;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemSearchResponse {


    @SerializedName("items")
    ArrayList<ItemSearch> itemSearches;

    public ArrayList<ItemSearch> getArtists() {
        return itemSearches;
    }




}
