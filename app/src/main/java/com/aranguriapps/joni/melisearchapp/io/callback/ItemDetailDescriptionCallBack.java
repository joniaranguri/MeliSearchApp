package com.aranguriapps.joni.melisearchapp.io.callback;


import com.aranguriapps.joni.melisearchapp.io.model.ItemDescription;

public interface ItemDetailDescriptionCallBack {

    void onDescriptionFound(ItemDescription itemDescription);

    void onFailedGetDescription();
}