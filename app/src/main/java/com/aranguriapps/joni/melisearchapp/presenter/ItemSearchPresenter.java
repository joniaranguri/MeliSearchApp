package com.aranguriapps.joni.melisearchapp.presenter;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.common.Utils;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemsSearchCallback;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import static com.aranguriapps.joni.melisearchapp.io.api.Constants.ML_ARG;

public class ItemSearchPresenter extends BasePresenter implements ItemsSearchCallback {
    public static final String LOG_TAG = ItemSearchPresenter.class.getSimpleName();
    ItemSearchView searchView;
    ItemSearchInteractor searchInteractor;

    public ItemSearchPresenter(ItemSearchView view, ItemSearchInteractor interactor) {
        searchView = view;
        searchInteractor = interactor;
    }

    @Override
    public void onStart() {
        searchView.setupAdapter();
        searchView.setupList();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onItemsFound(ItemSearchResponse itemSearchResponse) {
        if (itemSearchResponse.getItems() != null && itemSearchResponse.getItems().size() > 0)
            searchView.displayFoundItems(itemSearchResponse);
        else
            searchView.displayFailedSearch();
    }

    @Override
    public void onFailedSearch() {
        searchView.displayServerError();

    }

    public void searchItems(String query, Context context, String offset) {
        if (Utils.isOnline(context))
            searchInteractor.performSearch(ML_ARG, query, this, offset);
        else searchView.displayNetworkError();

    }


}
