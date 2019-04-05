package com.aranguriapps.joni.melisearchapp;


import android.content.Context;

import com.aranguriapps.joni.melisearchapp.common.Utils;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;
import com.aranguriapps.joni.melisearchapp.presenter.ItemSearchPresenter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemSearchView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        itemSearchPresenter.onItemsFound(new ItemSearchResponse() );
        verify(mockedview, times(1)).displayFailedSearch();
    }

    @Test
    public void showErrorMessageWhenNoConnection() {
        when(Utils.isOnline(mockedContext)).thenReturn(false);
        itemSearchPresenter.searchItems("algo", mockedContext,"0");
        verify(mockedview, times(1)).displayNetworkError();
 }



}