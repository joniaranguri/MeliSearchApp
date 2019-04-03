package com.aranguriapps.joni.melisearchapp.interactor;

import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiService;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailCallback;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailDescriptionCallBack;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemsSearchCallback;
import com.aranguriapps.joni.melisearchapp.io.model.ItemDescription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ItemSearchInteractor {
    MeliSearchApiService apiService;
    public ItemSearchInteractor(MeliSearchApiService apiService) {
        this.apiService = apiService;
    }
    public void performSearch(String site,String item, ItemsSearchCallback callback) {

        apiService.searchItems(site,item)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemsSearchResponse -> {callback.onItemsFound(itemsSearchResponse.getItems());}
                        , throwable -> {callback.onFailedSearch();
                        });
    }
    public void getItemDetail(String itemId,ItemDetailCallback callback){


        apiService.getItemById(itemId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemDetailResponse -> {callback.onItemFound(itemDetailResponse);}
                        , throwable -> {callback.onFailedGetDetail();
                        });
    }

    public void getItemDescription(String itemId,ItemDetailDescriptionCallBack callback) {
        apiService.getDescriptionById(itemId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemDescResponse -> {callback.onDescriptionFound(itemDescResponse);}
                        , throwable -> {callback.onFailedGetDescription();
                        });
    }
}
