package com.aranguriapps.joni.melisearchapp.common;

public class MercadoLibreUtils {
    public static int MAX_ITEMS_OFFSET_WITHOUT_TOKEN = 1000;

    /**
     * This method gets a image with the apropiate size to get a good
     * quality for shows results from search, based on the Mercadolibre images standar
     */
    public static String getImageGoodQuality(String url) {
        StringBuilder newUrl = new StringBuilder(url);
        try {
            newUrl.setCharAt(url.lastIndexOf('I'), 'O');

        } catch (Exception e) {
            Log.e("Error when try to get image of good quality", e.getMessage());
        }
        return newUrl.toString();
    }
}
