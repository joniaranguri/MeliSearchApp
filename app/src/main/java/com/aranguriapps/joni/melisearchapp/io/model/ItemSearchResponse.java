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
    public void getItems(ArrayList<ItemSearch> items) {
         this.itemSearches=items;
    }
    private  Page paging;
    public Page getPaging() {
        return paging;
    }

    public void setPaging(Page paging) {
        this.paging = paging;
    }




}
