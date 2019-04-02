package com.aranguriapps.joni.melisearchapp.presenter;

import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailCallback;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;

public class ItemDetailPresenter extends BasePresenter implements ItemDetailCallback {

    public static final String LOG_TAG = ItemSearchPresenter.class.getSimpleName();
    ItemDetailView searchView;
    ItemSearchInteractor searchInteractor;

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onItemFound(ItemSearch items) {

    }

    @Override
    public void onFailedGetDetail() {

    }
    public void getDetailsItem(String itemId){

        searchInteractor.getItemDetail(itemId,this);
    }
}
