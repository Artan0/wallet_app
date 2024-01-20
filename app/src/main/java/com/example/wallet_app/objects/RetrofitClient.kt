package com.example.wallet_app.objects

import com.example.wallet_app.service.CryptoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.wallet_app.utilities.API_URL
object RetrofitClient {
    private const val BASE_URL = API_URL

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cryptoApi: CryptoApi = retrofit.create(CryptoApi::class.java)
}
