package com.aranguriapps.joni.melisearchapp.ui.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseFragment;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.component.DaggerItemSearchComponent;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.module.ItemSearchModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.activities.DetailActivity;
import com.aranguriapps.joni.melisearchapp.ui.activities.ResultsActivity;
import com.aranguriapps.joni.melisearchapp.ui.activities.SearchActivity;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

import static android.view.View.GONE;


public class ItemSearchFragment extends BaseFragment implements ItemSearchView, SearchResultsAdapter.ItemClickListener, ResultsActivity.QueryCallBackListener {
    private final String TAG = ItemSearchFragment.class.getName();
    @Inject
    ItemSearchPresenter mSearchPresenter;

    @Inject
    SearchResultsAdapter mResultsAdapter;

    @BindView(R.id.list_items)
    RecyclerView mItemResultsList;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    private ArrayList<ItemSearch> mItems;
    private boolean notToSearch;
    private int currentOrientation;

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


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ((ResultsActivity) getActivity()).setQueryCallBackListener(this);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
     try {
         ((ResultsActivity) getActivity()).setQueryCallBackListener(null);
     }catch (Exception e){
         Log.e(TAG,e.getMessage());
     }

    }

    @Override
    public void setupList() {

        mItemResultsList.setLayoutManager(new StaggeredGridLayoutManager(this.currentOrientation== Configuration.ORIENTATION_LANDSCAPE?3:2, StaggeredGridLayoutManager.VERTICAL));
        mItemResultsList.setAdapter(mResultsAdapter);
    }







    @Override
    public void setupAdapter() {
        mResultsAdapter.setOnItemClickListener(this);
    }

    @Override
    public void displayFoundItems(ArrayList<ItemSearch> items) {

            mResultsAdapter.replace(this.mItems=items);

            pbLoading.setVisibility(GONE);
    }


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
    public void onItemClicked(String idItem) {
        Intent intent= new Intent(getContext(), DetailActivity.class);
        intent.putExtra(getString(R.string.item_id_to_search),idItem);
        startActivity(intent);




    }

    @Override
    public void onCallBack(String query) {
        if(notToSearch)
        {   displayFoundItems(this.mItems);
            notToSearch=false;
        }
        else
        mSearchPresenter.searchItems(query);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items",mItems);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){

            mItems = savedInstanceState.getParcelableArrayList("items");
            this.notToSearch= true;
        }
         currentOrientation = getResources().getConfiguration().orientation;

    }
}
