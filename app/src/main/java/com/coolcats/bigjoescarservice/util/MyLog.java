package com.coolcats.bigjoescarservice.util;

import android.util.Log;

public class MyLog {

    private static String TAG = "MY_TAG";

    public static void logger(String string){
        Log.d(TAG, string);
    }
}
