package com.shoppingapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REMOTEURL = "https://api.escuelajs.co/api/v1/"

class RemoteService {
    val api: RemoteInterface = Retrofit.Builder()
        .baseUrl(REMOTEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RemoteInterface::class.java)
}