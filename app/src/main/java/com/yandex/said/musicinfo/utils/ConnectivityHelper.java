package com.yandex.said.musicinfo.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by said on 22.04.16.
 */
public final class ConnectivityHelper {

    private static Context context;

    public static void setContext(Context context) {
        ConnectivityHelper.context = context;
    }

    private static ConnectivityManager getConnectivityManager() {
        if (context == null) throw new NullPointerException("context");
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    }

    public static boolean isConnected() {
        ConnectivityManager cm = getConnectivityManager();
        return cm.getActiveNetworkInfo() == null ? false : cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean isWifi() {
        ConnectivityManager cm = getConnectivityManager();
        return cm.getActiveNetworkInfo() == null ? false : cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static String getType() {
        ConnectivityManager cm = getConnectivityManager();
        return cm.getActiveNetworkInfo() == null ? null : cm.getActiveNetworkInfo().getTypeName();
    }

    private ConnectivityHelper() {}
}
