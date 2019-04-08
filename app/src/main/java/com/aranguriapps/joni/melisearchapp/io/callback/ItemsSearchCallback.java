package com.aranguriapps.joni.melisearchapp.io.callback;

import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;


public interface ItemsSearchCallback {

    void onItemsFound(ItemSearchResponse itemSearchResponse);

    void onFailedSearch();
}