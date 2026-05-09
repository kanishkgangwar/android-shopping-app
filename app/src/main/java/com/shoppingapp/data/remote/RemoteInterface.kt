package com.shoppingapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface RemoteInterface {
    @GET("products")
    suspend fun getProductData(): Response<List<Product>>
}