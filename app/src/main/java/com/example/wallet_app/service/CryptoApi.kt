package com.example.wallet_app.service

import com.example.wallet_app.model.CryptoApiResponse
import com.example.wallet_app.model.CryptoDetails
import com.example.wallet_app.model.HistoricalMarketDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {
    @GET("coins/markets")
    suspend fun getCryptoList(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false
    ): List<CryptoApiResponse>

    @GET("coins/{id}")
    suspend fun getCryptoById(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = "usd"
    ): CryptoDetails
    @GET("coins/{id}/market_chart")
    suspend fun getHistoricalMarketData(
        @Path("id") id: String,
        @Query("vs_currency") currency: String = "usd",
        @Query("days") days: Int,
//        @Query("interval") interval: String
    ): HistoricalMarketDataResponse
}
