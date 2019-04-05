package com.aranguriapps.joni.melisearchapp.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public  class Utils {

     public static boolean isOnline(Context context) {
               ConnectivityManager connectivityManager;
         boolean connected = false;
         try {
             connectivityManager = (ConnectivityManager) context
                     .getSystemService(Context.CONNECTIVITY_SERVICE);

             NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
             connected = (networkInfo != null) && networkInfo.isAvailable() &&
                     networkInfo.isConnected();
             return connected;


         } catch (Exception e) {
             System.out.println("CheckConnectivity Exception: " + e.getMessage());
             Log.e("connectivity", e.toString());
         }
         return connected;
     }
}
