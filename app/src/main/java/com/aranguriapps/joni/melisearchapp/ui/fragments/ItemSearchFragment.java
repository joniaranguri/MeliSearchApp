package com.aranguriapps.joni.melisearchapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseFragment;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.component.DaggerItemSearchComponent;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.module.ItemSearchModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.activities.ResultsActivity;
import com.aranguriapps.joni.melisearchapp.ui.activities.SearchActivity;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;


public class ItemSearchFragment extends BaseFragment implements ItemSearchView, SearchResultsAdapter.ItemClickListener{


    @Inject
    ItemSearchPresenter mSearchPresenter;

    @Inject
    SearchResultsAdapter mResultsAdapter;

    @BindView(R.id.list_items)
    RecyclerView mItemResultsList;
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_item_search;
    }

    @Override
    protected BasePresenter getPresenter() {
        return this.mSearchPresenter;
    }

    @Override
    protected void setUpComponent(MeliSearchComponent component) {
        DaggerItemSearchComponent.builder()
                .meliSearchComponent(component)
                .itemSearchModule(new ItemSearchModule(this))
                .build()
                .inject(this);
        if(SearchActivity.queryFromSearch!=null)
            mSearchPresenter.searchItems(SearchActivity.queryFromSearch);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setupList() {
        mItemResultsList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        mItemResultsList.setAdapter(mResultsAdapter);
    }







    @Override
    public void setupAdapter() {
        mResultsAdapter.setOnItemClickListener(this);
    }

    @Override
    public void displayFoundItems(ArrayList<ItemSearch> artists) {

            mResultsAdapter.replace(artists);
    }

    /*  @OnTextChanged(R.id.etxt_search)
      public void onQueryChanged(CharSequence query){
          if (query.length() >= 3)
              mSearchPresenter.searchArtists(query.toString());
          else if (query.length() <= 2)
              mResultsAdapter.clear();
      }
  */


    @Override
    public void displayFailedSearch() {
        Toast.makeText(CONTEXT, R.string.failed_search, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayNetworkError() {
        Toast.makeText(CONTEXT, R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayServerError() {
        Toast.makeText(CONTEXT, R.string.server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position) {


    }
}
