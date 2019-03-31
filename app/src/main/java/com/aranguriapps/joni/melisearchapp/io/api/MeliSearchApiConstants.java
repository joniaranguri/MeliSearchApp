package com.aranguriapps.joni.melisearchapp.io.api;

public class MeliSearchApiConstants {
    public static final String BASE_URL = "https://api.mercadolibre.com";

    public static final String SEARCH_PATH = "/search";
    public static final String SITES_PATH = "/sites";

    public static final String TYPE_QUERY = "type";
    public static final String QUERY_TO_SEARCH = "query_to_search";

    public static final String ITEM = "item";
    public static final String SITE_TO_SEARCH = "site_to_search";

   // public static final String ARTIST_SEARCH_URL = VERSION_PATH + SEARCH_PATH + "?"+ TYPE_QUERY + "=" + ARTIST;
    public static final String ITEMS_SEARCH_URL=   SITES_PATH+ SITE_TO_SEARCH+ SEARCH_PATH+ "?q" +"="+ QUERY_TO_SEARCH;
}
