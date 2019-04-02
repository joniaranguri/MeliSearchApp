package com.aranguriapps.joni.melisearchapp.presenter;

import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemsSearchCallback;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import java.util.ArrayList;

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
    public void onItemsFound(ArrayList<ItemSearch> items) {
    searchView.displayFoundItems(items);
    }

    @Override
    public void onFailedSearch() {
        searchView.displayFailedSearch();

    }
    public void searchItems(String query) {
       searchInteractor.performSearch("MLA",  query, this);

    }


}
