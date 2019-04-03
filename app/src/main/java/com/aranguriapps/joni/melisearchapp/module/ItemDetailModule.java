package com.aranguriapps.joni.melisearchapp.module;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.presenter.ItemDetailPresenter;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.ui.activities.DetailActivity;
import com.aranguriapps.joni.melisearchapp.ui.adapters.ImageAdapter;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import dagger.Module;
import dagger.Provides;
@Module
public class ItemDetailModule {


    private ItemDetailView view;

    public ItemDetailModule(ItemDetailView view) {
        this.view = view;
    }

    @Provides
    public ItemDetailView provideView() {
        return view;
    }


    @Provides
    public ItemDetailPresenter providePresenter(ItemDetailView view, ItemSearchInteractor interactor) {
        return new ItemDetailPresenter(view, interactor);
    }
    @Provides
    public ImageAdapter provideAdapter(Context context) {
        return new ImageAdapter(context);
    }

}
