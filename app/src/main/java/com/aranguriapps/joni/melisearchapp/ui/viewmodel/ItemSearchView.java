package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

import java.util.ArrayList;

public interface ItemSearchView {
    void displayFoundItems(ArrayList<ItemSearch> artists);

    void displayFailedSearch();

    void displayNetworkError();

    void displayServerError();
}
