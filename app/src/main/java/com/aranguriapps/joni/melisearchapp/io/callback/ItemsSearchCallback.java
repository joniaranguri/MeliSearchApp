package com.aranguriapps.joni.melisearchapp.io.callback;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

import java.util.ArrayList;

public interface ItemsSearchCallback {

    void onItemsFound(ItemSearchResponse itemSearchResponse);

    void onFailedSearch();
}