package com.aranguriapps.joni.melisearchapp.common;

public class MercadoLibreUtils {

    /**
     * This method gets a image with the apropiate size to get a good
     * quality for shows results from search, based on the Mercadolibre images standar*/
    public static String getImageGoodQuality(String url){
        StringBuilder newUrl= new StringBuilder(url);
        newUrl.setCharAt(url.lastIndexOf('I'),'O');
        return newUrl.toString();
    }
}
