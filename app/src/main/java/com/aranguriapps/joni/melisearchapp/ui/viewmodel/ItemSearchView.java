package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

public interface ItemSearchView {
    void setupList();

    void setupAdapter();

    void displayFoundItems(ItemSearchResponse itemSearchResponse);

    void displayFailedSearch();

    void displayNetworkError();

    void displayServerError();
}
