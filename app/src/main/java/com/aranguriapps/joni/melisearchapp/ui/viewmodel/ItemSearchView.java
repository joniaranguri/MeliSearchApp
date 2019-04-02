package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

import java.util.ArrayList;

public interface ItemSearchView {
    void setupList();

    void setupAdapter();

    void displayFoundItems(ArrayList<ItemSearch> itemSearches);

    void displayFailedSearch();

    void displayNetworkError();

    void displayServerError();
}
