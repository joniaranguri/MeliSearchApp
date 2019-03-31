package com.aranguriapps.joni.melisearchapp.common;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BasePresenter {
    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStart()} in case presenter is attached to activity <br>
     * {@link Fragment#onStart()}  in case presenter is attached to fragment
     * */
    public abstract void onStart();

    /**
     * This method will be executed on
     * {@link AppCompatActivity#onStop()} in case presenter is attached to activity <br>
     * {@link Fragment#onStop()}  in case presenter is attached to fragment
     * */
    public abstract void onStop();
}
