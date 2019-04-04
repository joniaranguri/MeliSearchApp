package com.aranguriapps.joni.melisearchapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.common.BaseActivity;
import com.aranguriapps.joni.melisearchapp.common.BasePresenter;
import com.aranguriapps.joni.melisearchapp.component.DaggerItemDetailComponent;
import com.aranguriapps.joni.melisearchapp.component.DaggerItemSearchComponent;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.domain.MeliSearchImage;
import com.aranguriapps.joni.melisearchapp.module.ItemDetailModule;
import com.aranguriapps.joni.melisearchapp.module.ItemSearchModule;
import com.aranguriapps.joni.melisearchapp.presenter.ItemDetailPresenter;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;
import com.aranguriapps.joni.melisearchapp.ui.adapters.ImageAdapter;
import com.aranguriapps.joni.melisearchapp.ui.fragments.ErrorFragment;
import com.aranguriapps.joni.melisearchapp.ui.fragments.NoInternetFragment;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailActivity extends BaseActivity implements ItemDetailView ,ErrorFragment.OnFragmentInteractionListener,NoInternetFragment.OnFragmentInteractionListener{

    private static final String TAG = DetailActivity.class.getName();
    @Inject
    ItemDetailPresenter itemDetailPresenter;
    @Inject
    ImageAdapter imageAdapter;
    @BindView(R.id.txt_item_cat_fotos)
    TextView textViewCantImages;
    @BindView(R.id.txt_item_title_det)
    TextView textViewTitle;
    @BindView(R.id.txt_item_price_det)
    TextView textViewPrice;
    @BindView(R.id.txt_item_desc_det)
    TextView textViewDescription;
    @BindView(R.id.txt_desc)
    TextView textTitleDesc;
    @BindView(R.id.btn_go_to_meli)
    Button btnLinkMeli;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private boolean isDescriptionLoaded;
    private boolean isDetailsLoaded;
    private boolean isShowingError;
    private Fragment nestedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar("Producto");
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (Exception e){
            Log.i(TAG,"Error al setear la toolbar");
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter() {
        return this.itemDetailPresenter;
    }

    @Override
    public void setUpComponent(MeliSearchComponent appComponent) {
        DaggerItemDetailComponent.builder()
                .meliSearchComponent(appComponent)
                .itemDetailModule(new ItemDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setImageAdapter() {
        viewPager.setAdapter(imageAdapter);
    }

    @Override
    public void displayFoundItem(ItemSearch itemDetail) {
        imageAdapter.replace(itemDetail.getPictures());
        this.textViewCantImages.setText(String.valueOf(itemDetail.getPictures().size()).concat(" "+getString(R.string._FOTOS)));
        this.textViewTitle.setText(itemDetail.getTitle());
        this.textViewPrice.setText(getString(R.string.pesos_sig).concat(itemDetail.getPrice()));
        this.textViewDescription.setText(itemDetail.getDescription());
        if(isDescriptionLoaded)
        {
            pbLoading.setVisibility(View.GONE);
            btnLinkMeli.setVisibility(View.VISIBLE);
            textTitleDesc.setText(getString(R.string.Description));
        }
        isDetailsLoaded= true;

        this.btnLinkMeli.setOnClickListener(view -> {
            Uri webpage = Uri.parse(itemDetail.getLinkMercadolibre());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }

    @Override
    public void displayFailedGetDetails() {
        if(!isShowingError)
            manageError(R.string.not_details);

    }

    private void manageError(int typeError) {
        switch (typeError){
            case R.string.network_error:
                nestedFragment= new NoInternetFragment();
                break;
            default:
                    nestedFragment= new ErrorFragment();

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.containerDetail,nestedFragment).commit();
        this.isShowingError= true;
    }

    @Override
    public void displayFailedGetDescription() {
        if(!isShowingError)
            manageError(R.string.not_details);
    }

    @Override
    public void displayNetworkError() {
        if(!isShowingError)
            manageError(R.string.network_error);
    }

    @Override
    public void displayServerError() {
        if(!isShowingError)
            manageError(R.string.server_error);

    }

    @Override
    public void displayFoundDescription(String description) {
        this.textViewDescription.setText(description);
        if(isDetailsLoaded) {
            pbLoading.setVisibility(View.GONE);
            btnLinkMeli.setVisibility(View.VISIBLE);
            textTitleDesc.setText(getString(R.string.Description));
        }
        isDescriptionLoaded= true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        String itemToSearch = getIntent().getStringExtra(getString(R.string.item_id_to_search));
        itemDetailPresenter.getDetailsItem(itemToSearch,this);
        itemDetailPresenter.getItemDescription(itemToSearch,this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
