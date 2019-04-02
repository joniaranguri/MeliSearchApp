package com.aranguriapps.joni.melisearchapp.io.api;

public class MeliSearchApiConstants {
    public static final String BASE_URL = "https://api.mercadolibre.com";

    public static final String SEARCH_PATH = "/search";
    public static final String SITES_PATH = "/sites";

    public static final String TYPE_QUERY = "type";
    public static final String QUERY_TO_SEARCH = "q";

    public static final String ITEM = "item";
    public static final String SITE_PATH = "/{site}";
    public static final String SITE = "site";


   // public static final String ARTIST_SEARCH_URL = VERSION_PATH + SEARCH_PATH + "?"+ TYPE_QUERY + "=" + ARTIST;
    public static final String ITEMS_SEARCH_URL=   SITES_PATH+ SITE_PATH+ SEARCH_PATH;
    public static final String ID_ITEM = "ID_ITEM" ;
    public static final String GET_ITEM_BY_ID = "/items/{" + ID_ITEM + "}";
}
