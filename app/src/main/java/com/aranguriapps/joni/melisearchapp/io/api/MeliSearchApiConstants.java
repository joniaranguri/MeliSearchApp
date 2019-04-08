package com.aranguriapps.joni.melisearchapp.io.api;

public class MeliSearchApiConstants {
    public static final String BASE_URL = "https://api.mercadolibre.com";

    public static final String SEARCH = "/search";

    public static final String QUERY_TO_SEARCH = "q";

    public static final String SITE_PATH = "site_path";
    public static final String SITES = "/sites";


    public static final String ITEMS_SEARCH_URL = SITES + "/{" + SITE_PATH + "}" + SEARCH + "?";
    public static final String DESCRIPTION = "/description";

    public static final String ID_ITEM = "ID_ITEM";
    public static final String GET_ITEM_BY_ID = "/items/{" + ID_ITEM + "}";
    public static final String GET_DESCRIPTION_BY_ID = "/items/{" + ID_ITEM + "}" + DESCRIPTION;

    public static final String OFFSET = "offset";
}
