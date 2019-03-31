package com.aranguriapps.joni.melisearchapp.component;

import com.aranguriapps.joni.melisearchapp.common.ActivityScope;
import com.aranguriapps.joni.melisearchapp.module.ItemSearchModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.fragments.ItemSearchFragment;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = MeliSearchComponent.class,
        modules =ItemSearchModule.class
)
public interface ItemSearchComponent {

    void inject(ItemSearchFragment searchFragment);

    ItemSearchPresenter getPresenter();
    SearchResultsAdapter getAdapter();
}
