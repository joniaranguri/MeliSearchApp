package com.aranguriapps.joni.melisearchapp.presenter;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.common.Utils;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailCallback;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailDescriptionCallBack;
import com.aranguriapps.joni.melisearchapp.io.model.ItemDescription;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;

public class ItemDetailPresenter extends BasePresenter implements ItemDetailCallback,ItemDetailDescriptionCallBack {

    public static final String LOG_TAG = ItemSearchPresenter.class.getSimpleName();
    ItemDetailView detailView;
    ItemSearchInteractor searchInteractor;
    public ItemDetailPresenter(ItemDetailView view, ItemSearchInteractor interactor) {
        detailView = view;
        searchInteractor = interactor;
    }
    @Override
    public void onStart() {
detailView.setImageAdapter();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onItemFound(ItemSearch item) {
detailView.displayFoundItem(item);
    }

    @Override
    public void onFailedGetDetail() {
    detailView.displayFailedGetDetails();
    }
    public void getDetailsItem(String itemId, Context context){

        if(Utils.isOnline(context))
            searchInteractor.getItemDetail(itemId,this);
        else detailView.displayNetworkError();
    }

    public void getItemDescription(String itemId,Context context){
        if(Utils.isOnline(context))
        searchInteractor.getItemDescription(itemId,this);
        else detailView.displayNetworkError();
    }
    @Override
    public void onDescriptionFound(ItemDescription itemDescription) {
        detailView.displayFoundDescription(itemDescription.getDescripcion());
    }

    @Override
    public void onFailedGetDescription() {
        detailView.displayFailedGetDescription();
    }
}
