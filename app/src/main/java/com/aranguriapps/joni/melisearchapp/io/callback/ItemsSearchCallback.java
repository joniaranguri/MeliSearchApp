package com.aranguriapps.joni.melisearchapp.io.callback;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

import java.util.ArrayList;

public interface ItemsSearchCallback {

    void onItemsFound(ArrayList<ItemSearch> items);

    void onFailedSearch();
}