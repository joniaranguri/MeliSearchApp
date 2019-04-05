package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

import java.util.ArrayList;

public interface ItemSearchView {
    void setupList();

    void setupAdapter();

    void displayFoundItems(ItemSearchResponse itemSearchResponse);

    void displayFailedSearch();

    void displayNetworkError();

    void displayServerError();
}
