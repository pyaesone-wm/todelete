package com.ps.networkutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by pyaesone on 2019-06-24
 */
public class NetworkUtilss {

    private static NetworkUtilss objInstance;

    private NetworkUtilss() {
    }

    public static NetworkUtilss getInstance() {
        if (objInstance == null) {
            objInstance = new NetworkUtilss();
        }

        return objInstance;
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context,"Already connected internet.",Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(context,"Open internet.",Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
