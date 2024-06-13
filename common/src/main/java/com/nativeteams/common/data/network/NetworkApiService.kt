package com.nativeteams.common.data.network

import com.nativeteams.common.data.network.responseModels.StocksResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import javax.inject.Singleton

@Singleton
interface NetworkApiService {

    @GET("market/v2/get-summary")
    suspend fun getStocks(@QueryMap parameters: Map<String, String>): StocksResponse
}