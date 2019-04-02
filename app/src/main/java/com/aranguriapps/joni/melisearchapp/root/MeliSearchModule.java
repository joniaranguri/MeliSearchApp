package com.aranguriapps.joni.melisearchapp.root;

import android.app.Application;
import android.content.Context;

import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiAdapter;
import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MeliSearchModule {
    private MeliSearchApp app;

    public MeliSearchModule (MeliSearchApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides @Singleton public Context provideContext() {
        return app;
    }

    @Provides @Singleton public Retrofit provideRetrofitInstance() {
        return MeliSearchApiAdapter.getInstance();
    }

    @Provides @Singleton public MeliSearchApiService provideMeliSearchApiService(Retrofit retrofit){
        return retrofit.create(MeliSearchApiService.class);
    }

}
