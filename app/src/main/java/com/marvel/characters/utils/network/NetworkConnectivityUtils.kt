package com.marvel.characters.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkConnectivityUtils constructor(val context: Context): NetworkConnectivityUtilsInterface {

    override fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    override fun isConnected(): Boolean {
        val info = getNetworkInfo()
        return info != null && info.isConnected
    }

}