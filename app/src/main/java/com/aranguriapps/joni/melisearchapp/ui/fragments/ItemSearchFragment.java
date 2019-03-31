package com.aranguriapps.joni.melisearchapp.ui.fragments;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseFragment;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import java.util.ArrayList;

public class ItemSearchFragment extends BaseFragment implements ItemSearchView, SearchResultsAdapter.ItemClickListener{
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_item_search;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void displayFoundItems(ArrayList<ItemSearch> artists) {

    }

    @Override
    public void displayFailedSearch() {

    }

    @Override
    public void displayNetworkError() {

    }

    @Override
    public void displayServerError() {

    }
}
