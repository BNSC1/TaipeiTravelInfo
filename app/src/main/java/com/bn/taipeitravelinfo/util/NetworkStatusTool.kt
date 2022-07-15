package com.bn.taipeitravelinfo.util

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusTool @Inject constructor(@ApplicationContext private val context: Context) {
    suspend fun isInternetAvailable(): Boolean =
        try {
            withContext(Dispatchers.IO) {
                runCatching {
                    val inetAddress = InetAddress.getByName("www.google.com")
                    inetAddress.toString() != ""
                }.getOrDefault(false)
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            false
        }

    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities != null
    }
}