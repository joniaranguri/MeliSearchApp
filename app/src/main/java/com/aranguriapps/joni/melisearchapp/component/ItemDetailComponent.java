package com.aranguriapps.joni.melisearchapp.component;


import com.aranguriapps.joni.melisearchapp.common.ActivityScope;
import com.aranguriapps.joni.melisearchapp.module.ItemDetailModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemDetailPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.activities.DetailActivity;
import com.aranguriapps.joni.melisearchapp.ui.adapters.ImageAdapter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = MeliSearchComponent.class,
        modules = ItemDetailModule.class
)
public interface ItemDetailComponent {

    void inject(DetailActivity activity);

    ItemDetailPresenter getPresenter();

    ImageAdapter getAdapter();
}