package com.aranguriapps.joni.melisearchapp;


import android.content.Context;

import com.aranguriapps.joni.melisearchapp.common.Log;
import com.aranguriapps.joni.melisearchapp.common.Utils;
import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemsSearchCallback;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.ui.activities.ResultsActivity;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.aranguriapps.joni.melisearchapp.io.api.Constants.ML_ARG;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemSearchPresenterUnitTest {
    private ItemSearchPresenter itemSearchPresenter;
    private ItemSearchView mockedview;
    private ItemSearchInteractor mockedInteractor;
    private Context mockedContext;

    @Before
    public void conifigurationMethod() {
        mockedview = mock(ItemSearchView.class);
        mockedInteractor = mock(ItemSearchInteractor.class);
        itemSearchPresenter = new ItemSearchPresenter(mockedview, mockedInteractor);
        mockedContext = mock(Context.class);
    }

    @Test
    public void showsErrorMessageWhenNotResults() {
        itemSearchPresenter.onItemsFound(new ArrayList<>());
        verify(mockedview, times(1)).displayFailedSearch();
    }

    @Test
    public void showErrorMessageWhenNoConnection() {
        when(Utils.isOnline(mockedContext)).thenReturn(false);
        itemSearchPresenter.searchItems("algo", mockedContext);
        verify(mockedview, times(1)).displayNetworkError();
 }



}