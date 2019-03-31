package com.aranguriapps.joni.melisearchapp.module;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import dagger.Module;
import dagger.Provides;


@Module
public class ItemSearchModule {


    private ItemSearchView view;

    public ItemSearchModule(ItemSearchView view) {
        this.view = view;
    }

    @Provides
    public ItemSearchView provideView() {
        return view;
    }


    @Provides
    public ItemSearchPresenter providePresenter(ItemSearchView view, ItemSearchInteractor interactor) {
        return new ItemSearchPresenter(view, interactor);
    }

    @Provides
    public SearchResultsAdapter provideAdapter(Context context) {
        return new SearchResultsAdapter(context);
    }
}
