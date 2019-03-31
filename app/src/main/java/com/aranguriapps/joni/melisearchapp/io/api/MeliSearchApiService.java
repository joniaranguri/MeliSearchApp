package com.aranguriapps.joni.melisearchapp.io.api;

import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeliSearchApiService {

    @GET(MeliSearchApiConstants.ITEMS_SEARCH_URL)
    Observable<ItemSearchResponse> searchItems(@Query(MeliSearchApiConstants.SITE_TO_SEARCH) String site, @Query(MeliSearchApiConstants.QUERY_TO_SEARCH) String query);

}
