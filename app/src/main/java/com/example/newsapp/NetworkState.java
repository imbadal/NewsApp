package com.example.newsapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkState {
    private ConnectivityManager connectivityManager;
    public NetworkState(Application application) {
        connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnected() {
        return connectivityManager.getActiveNetworkInfo() != null;

    }
}
