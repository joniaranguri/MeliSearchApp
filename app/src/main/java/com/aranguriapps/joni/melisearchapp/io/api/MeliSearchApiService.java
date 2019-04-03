package com.aranguriapps.joni.melisearchapp.io.api;

import com.aranguriapps.joni.melisearchapp.domain.ItemSearch;
import com.aranguriapps.joni.melisearchapp.io.model.ItemDescription;
import com.aranguriapps.joni.melisearchapp.io.model.ItemSearchResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeliSearchApiService {

    @GET(MeliSearchApiConstants.ITEMS_SEARCH_URL)
    Observable<ItemSearchResponse> searchItems(@Path(MeliSearchApiConstants.SITE_PATH) String site,
                                               @Query(MeliSearchApiConstants.QUERY_TO_SEARCH) String item);
//https://api.mercadolibre.com/items/MLA615019452
    @GET(MeliSearchApiConstants.GET_ITEM_BY_ID)
    Observable<ItemSearch> getItemById(@Path(MeliSearchApiConstants.ID_ITEM) String id_item);
    //https://api.mercadolibre.com/items/MLU445072353/description
 @GET(MeliSearchApiConstants.GET_DESCRIPTION_BY_ID)
    Observable<ItemDescription> getDescriptionById(@Path(MeliSearchApiConstants.ID_ITEM) String id_item);
}
