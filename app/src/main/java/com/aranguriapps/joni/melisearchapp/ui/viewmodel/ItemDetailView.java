package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

public interface ItemDetailView {
    void setDetails();

    //void setupAdapter();

    void displayFoundItems(ItemSearch itemDetail);

    void displayFailedGetDetails();

    void displayNetworkError();

    void displayServerError();
}
