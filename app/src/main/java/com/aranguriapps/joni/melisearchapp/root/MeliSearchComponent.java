package com.aranguriapps.joni.melisearchapp.root;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.module.InteractorModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                MeliSearchModule.class,
                InteractorModule.class
        }
)
public interface MeliSearchComponent {

    Context getContext();
    ItemSearchInteractor getItemSearchInteractor();
}
