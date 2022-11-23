package com.kotlin.pokedex

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
  const val BASE_URL = "https://pokeapi.co/api/v2/"

  fun isNetworkAvailable(context: Context): Boolean{
    val connectivityManager = context.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    // Checking android Version
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      // Newer Versions.
      val network = connectivityManager.activeNetwork?: return false
      val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false
      return when{
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
      }
    }else{
      // Older versions.
      val networkInfo = connectivityManager.activeNetworkInfo
      return networkInfo !=null && networkInfo.isConnectedOrConnecting
    }
  }
}