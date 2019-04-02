package com.aranguriapps.joni.melisearchapp.io.callback;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

public interface ItemDetailCallback {

    void onItemFound(ItemSearch items);

    void onFailedGetDetail();
}
