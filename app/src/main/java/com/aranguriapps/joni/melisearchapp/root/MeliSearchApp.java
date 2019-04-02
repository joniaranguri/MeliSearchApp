package com.aranguriapps.joni.melisearchapp.root;

import android.app.Application;
import android.content.Context;

public class MeliSearchApp extends Application {
    private MeliSearchComponent component;
    public MeliSearchComponent getComponent() {
        return component;
    }

    public static MeliSearchApp getApp(Context context) {
        return (MeliSearchApp) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    /**
     * The object graph contains all the instances of the objects
     * that resolve a dependency
     * */
    private void setupGraph() {
        component = DaggerMeliSearchComponent.builder()
                .meliSearchModule(new MeliSearchModule(this))
                .build();
    }




}
