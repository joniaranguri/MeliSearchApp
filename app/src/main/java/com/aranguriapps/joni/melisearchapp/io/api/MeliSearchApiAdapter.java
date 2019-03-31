package com.aranguriapps.joni.melisearchapp.io.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class MeliSearchApiAdapter {
    private static Retrofit RETROFIT;

    public static Retrofit getInstance(){
        //The adapter will be a singleton
        if(RETROFIT == null)
            RETROFIT =  new Retrofit.Builder()
                    .baseUrl(MeliSearchApiConstants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        return RETROFIT;
    }
}
