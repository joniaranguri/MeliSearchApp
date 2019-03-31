package com.aranguriapps.joni.melisearchapp.interactor;

import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiService;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemsSearchCallback;

public class ItemSearchInteractor {
    MeliSearchApiService apiService;
    public ItemSearchInteractor(MeliSearchApiService apiService) {
        this.apiService = apiService;
    }
    public void performSearch(String site,String item, ItemsSearchCallback callback) {

        //do the call
       /* apiService.searchItems(site,item)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artistSearchResponse -> {callback.onItemsFound(artistSearchResponse);}
                        , throwable -> {callback.onFailedSearch();
                        });*/
    }
}
