package com.aranguriapps.joni.melisearchapp.ui.activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseActivity;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.io.api.MeliSearchApiConstants;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;

public class ResultsActivity extends BaseActivity{
        private QueryCallBackListener queryCallBackListener;
        @BindView(R.id.search_view)
        MaterialSearchView searchView;
    private String queryFromSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar("Búsqueda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

this.queryFromSearch=getIntent().getStringExtra("queryFromSearch");

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() { }

            @Override
            public void onSearchViewClosed() {}
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.queryFromSearch= query;
                if (queryCallBackListener != null) {
                    queryCallBackListener.onCallBack(query);
                }
             searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return true; }

        });

        searchView.setQuery(this.queryFromSearch,true);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void setQueryCallBackListener(QueryCallBackListener listener) {
        this.queryCallBackListener = listener;
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_results ;
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void setUpComponent(MeliSearchComponent appComponent) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
    public interface QueryCallBackListener {
        void onCallBack(String query);// pass any parameter in your onCallBack which you want to return
    }


}
