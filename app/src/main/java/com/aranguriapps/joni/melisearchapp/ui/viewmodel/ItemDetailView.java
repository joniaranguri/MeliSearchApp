package com.aranguriapps.joni.melisearchapp.ui.viewmodel;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;

public interface ItemDetailView {
    void setImageAdapter();

    void displayFoundItem(ItemSearch itemDetail);

    void displayFailedGetDetails();

    void displayFailedGetDescription();

    void displayNetworkError();

    void displayServerError();

    void displayFoundDescription(String description);
}
