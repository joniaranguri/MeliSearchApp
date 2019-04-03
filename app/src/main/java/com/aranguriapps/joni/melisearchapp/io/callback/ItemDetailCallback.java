package com.aranguriapps.joni.melisearchapp.io.callback;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemDescription;

public interface ItemDetailCallback {

    void onItemFound(ItemSearch itemDetail);

    void onFailedGetDetail();
}
