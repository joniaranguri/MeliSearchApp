package com.aranguriapps.joni.melisearchapp.io.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class MeliSearchApiAdapter {
    private static Retrofit RETROFIT;
private static OkHttpClient buildClient(){
    return  new OkHttpClient
            .Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();
}
    public static Retrofit getInstance(){
        //The adapter will be a singleton
        if(RETROFIT == null)
            RETROFIT =  new Retrofit.Builder()
                    .client(buildClient())
                    .baseUrl(MeliSearchApiConstants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        return RETROFIT;
    }
}
