package com.aranguriapps.joni.melisearchapp.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseFragment;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.common.MercadoLibreUtils;
import com.aranguriapps.joni.melisearchapp.component.DaggerItemSearchComponent;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;
import com.aranguriapps.joni.melisearchapp.module.ItemSearchModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.activities.DetailActivity;
import com.aranguriapps.joni.melisearchapp.ui.activities.ResultsActivity;
import com.aranguriapps.joni.melisearchapp.ui.adapters.PaginationScrollListener;
import com.aranguriapps.joni.melisearchapp.ui.adapters.SearchResultsAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;



public class ItemSearchFragment extends BaseFragment implements ItemSearchView, SearchResultsAdapter.ItemClickListener, ResultsActivity.QueryCallBackListener {
    private final String TAG = ItemSearchFragment.class.getName();
    private final int OFFSET_START = 0;

    private boolean isLoading = false;
    private int offset ;



    @Inject
    ItemSearchPresenter mSearchPresenter;

    @Inject
    SearchResultsAdapter mResultsAdapter;
    @BindView(R.id.recicler_container)
    RelativeLayout recicler_container;
    @BindView(R.id.list_items)
    RecyclerView mItemResultsList;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    private ArrayList<ItemSearch> mItems;
    private boolean notToSearch;
    private int currentOrientation;
    private boolean isErrorShowed;
    private Fragment nestedFragment;
    private String queryToSearch;
    private int limit;

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
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(this.currentOrientation== Configuration.ORIENTATION_LANDSCAPE?3:2, StaggeredGridLayoutManager.VERTICAL);

        mItemResultsList.setLayoutManager(layoutManager);
        mItemResultsList.setAdapter(mResultsAdapter);
        mItemResultsList.addOnScrollListener(new PaginationScrollListener( layoutManager){
            @Override
            protected void loadMoreItems() {

                loadNextPage();
            }

            @Override
            public boolean isLastPage() {

                return offset> MercadoLibreUtils.MAX_ITEMS_OFFSET_WITHOUT_TOKEN;
            }

            @Override
            public boolean isLoading() {
               return isLoading;
            }
        });
    }

    private void loadNextPage() {
        isLoading= true;
        mSearchPresenter.searchItems(this.queryToSearch,CONTEXT,String.valueOf(this.offset));
    }


    @Override
    public void setupAdapter() {
        mResultsAdapter.setOnItemClickListener(this);
    }

    @Override
    public void displayFoundItems(ItemSearchResponse itemSearchResponse) {
        this.limit=itemSearchResponse.getPaging().getLimit();
        this.offset= itemSearchResponse.getPaging().getOffset();
        if(isLoading){

            mResultsAdapter.addAll(itemSearchResponse.getItems());
            offset += limit;
            isLoading= false;
        }
            else{
            mResultsAdapter.replace(itemSearchResponse.getItems());
        }

            pbLoading.setVisibility(View.INVISIBLE);
    }


    @Override
    public void displayFailedSearch() {
       manageErrot(R.string.failed_search);
    }

    private void manageErrot(int typeError) {

        switch (typeError){
            case R.string.failed_search:
                nestedFragment = new NotFoundFragment();
                break;
            case R.string.network_error:
                nestedFragment = new NoInternetFragment();
                break;
            default :
                nestedFragment = new ErrorFragment();

            }
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(recicler_container.getId(),nestedFragment).commit();
        mResultsAdapter.replace(null);
        this.isErrorShowed= true;
    }

    @Override
    public void displayNetworkError() {
    manageErrot(R.string.network_error);
    }

    @Override
    public void displayServerError() {
    manageErrot(R.string.server_error);
    }

    @Override
    public void onItemClicked(String idItem) {
        Intent intent= new Intent(getContext(), DetailActivity.class);
        intent.putExtra(getString(R.string.item_id_to_search),idItem);
        startActivity(intent);




    }

    @Override
    public void onCallBack(String query) {
        this.queryToSearch= query;
        if(notToSearch)
        {   mResultsAdapter.replace(this.mItems); //this is called when return from saved instance
            notToSearch=false;
        }
        else{
            if(this.isErrorShowed)
            {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.remove(this.nestedFragment).commit();
                isErrorShowed= false;
            }
            mSearchPresenter.searchItems(query,CONTEXT,String.valueOf(this.OFFSET_START));
            pbLoading.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mItems= mResultsAdapter.getItemSearches();
        outState.putParcelableArrayList("items",mItems);
        outState.putInt("offset",offset);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            this.offset= savedInstanceState.getInt("offset",0);
            mItems = savedInstanceState.getParcelableArrayList("items");
            this.notToSearch= true;
            pbLoading.setVisibility(View.INVISIBLE);
        }
         currentOrientation = getResources().getConfiguration().orientation;

    }
}
