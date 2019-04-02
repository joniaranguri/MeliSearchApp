package com.aranguriapps.joni.melisearchapp.io.api;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeliSearchApiService {

    @GET("/sites/{Site}/search")
    Observable<ItemSearchResponse> searchItems(@Path("Site") String site,
                                               @Query("q") String item);
//https://api.mercadolibre.com/items/MLA615019452
    @GET(MeliSearchApiConstants.GET_ITEM_BY_ID)
    Observable<ItemSearch> getItemById(@Path(MeliSearchApiConstants.ID_ITEM) String id_item);

}
