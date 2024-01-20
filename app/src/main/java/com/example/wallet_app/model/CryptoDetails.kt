package com.example.wallet_app.model


data class CryptoDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val priceData: List<Double>,
    val historicalMarketData: List<Pair<Long, Double>>
)
