package com.example.wallet_app.model

data class CryptoApiResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double
)
