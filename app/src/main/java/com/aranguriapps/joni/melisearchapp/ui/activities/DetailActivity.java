package com.aranguriapps.joni.melisearchapp.ui.activities;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseActivity;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.domain.MeliSearchImage;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.adapters.ImageAdapter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity implements ItemDetailView {

    private String itemToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemToSearch= getIntent().getStringExtra(getString(R.string.item_id_to_search));
        Toast.makeText(this, itemToSearch, Toast.LENGTH_SHORT).show();
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        ArrayList<MeliSearchImage> dummy =new ArrayList<MeliSearchImage>();
        MeliSearchImage m1= new MeliSearchImage();
        m1.setUrl("http://mla-s1-p.mlstatic.com/869649-MLA27216570076_042018-O.jpg");
        dummy.add(m1);
        MeliSearchImage m2= new MeliSearchImage();
        m2.setUrl("http://mla-s1-p.mlstatic.com/878677-MLA27216547324_042018-O.jpg");
        dummy.add(m2);
        adapter.replace(dummy);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
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
    public void setDetails() {

    }

    @Override
    public void displayFoundItems(ItemSearch itemDetail) {

    }

    @Override
    public void displayFailedGetDetails() {

    }

    @Override
    public void displayNetworkError() {

    }

    @Override
    public void displayServerError() {

    }
}
