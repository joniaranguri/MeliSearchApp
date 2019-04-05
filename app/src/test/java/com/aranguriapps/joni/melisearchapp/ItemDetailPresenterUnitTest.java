package com.aranguriapps.joni.melisearchapp;

import android.content.Context;

import com.aranguriapps.joni.melisearchapp.common.Utils;
import com.aranguriapps.joni.melisearchapp.interactor.ItemSearchInteractor;
import com.aranguriapps.joni.melisearchapp.io.callback.ItemDetailCallback;
import com.aranguriapps.joni.melisearchapp.presenter.ItemDetailPresenter;
import com.aranguriapps.joni.melisearchapp.ui.viewmodel.ItemDetailView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemDetailPresenterUnitTest {

    private ItemDetailPresenter itemDetailPresenter;
    private ItemDetailView mockedview;
    private ItemSearchInteractor mockedInteractor;
    private Context mockedContext;
    ItemDetailCallback mockedCallback;

    @Before
    public void conifigurationMethod() {
        mockedview = mock(ItemDetailView.class);
        mockedInteractor = mock(ItemSearchInteractor.class);
        itemDetailPresenter = new ItemDetailPresenter(mockedview, mockedInteractor);
        mockedContext = mock(Context.class);
        mockedCallback= mock(ItemDetailCallback.class);
    }

    @Test
    public void showErrorMessageWhenNoConnectionDescription() {
        when(Utils.isOnline(mockedContext)).thenReturn(false);
        itemDetailPresenter.getItemDescription("id", mockedContext);
        verify(mockedview, times(1)).displayNetworkError();
    }

    @Test
    public void showErrorMessageWhenNoConnectionDetails() {
        when(Utils.isOnline(mockedContext)).thenReturn(false);
        itemDetailPresenter.getDetailsItem("id", mockedContext);
        verify(mockedview, times(1)).displayNetworkError();
    }

}