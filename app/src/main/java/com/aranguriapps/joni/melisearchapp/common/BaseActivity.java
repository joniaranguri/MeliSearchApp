package com.aranguriapps.joni.melisearchapp.common;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.aranguriapps.joni.melisearchapp.R;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchApp;
import com.aranguriapps.joni.melisearchapp.root.MeliSearchComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectDependencies();
        injectViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getPresenter() != null)
            getPresenter().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (getPresenter() != null)
            getPresenter().onStop();
    }

    /**
     * Its common use a toolbar within activity, if it exists in the
     * layout this will be configured
     */
    public void setupToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * @return The layout that's gonna be the activity view.
     */
    protected abstract int getLayout();

    /**
     * @return The presenter attached to the activity. This must extends from {@link BasePresenter}
     */
    @Nullable
    protected abstract BasePresenter getPresenter();

    /**
     * Setup the object graph and inject the dependencies needed on this activity.
     */
    private void injectDependencies() {
        setUpComponent(MeliSearchApp.getApp(this).getComponent());
    }

    /**
     * Every object annotated with bind its gonna injected trough butterknife
     */
    private void injectViews() {
        ButterKnife.bind(this);
    }


    public abstract void setUpComponent(MeliSearchComponent appComponent);
}
