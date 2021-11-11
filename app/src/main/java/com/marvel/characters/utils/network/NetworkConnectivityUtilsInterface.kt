package com.marvel.characters.utils.network
import android.net.NetworkInfo

interface NetworkConnectivityUtilsInterface {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}