package com.aranguriapps.joni.melisearchapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    StaggeredGridLayoutManager layoutManager;

    public PaginationScrollListener(StaggeredGridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int[] firstVisibleItems = null;
        int pastVisibleItems=0;
        firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems);
        if(firstVisibleItems != null && firstVisibleItems.length > 0) {
             pastVisibleItems = firstVisibleItems[0];
        }
         if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + pastVisibleItems) >=
                    totalItemCount && pastVisibleItems >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();
    public abstract boolean isLastPage();
    public abstract boolean isLoading();
}