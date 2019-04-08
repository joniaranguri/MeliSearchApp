package com.aranguriapps.joni.melisearchapp.module;

import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    public ItemSearchInteractor provideItemSearchInteractor(MeliSearchApiService apiService) {
        return new ItemSearchInteractor(apiService);
    }
}
