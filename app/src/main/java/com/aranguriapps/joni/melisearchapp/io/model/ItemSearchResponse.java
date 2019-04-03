package com.aranguriapps.joni.melisearchapp.io.model;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemSearchResponse {


    @SerializedName("results")
    ArrayList<ItemSearch> itemSearches;

    public ArrayList<ItemSearch> getItems() {
        return itemSearches;
    }




}
