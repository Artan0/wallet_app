package com.example.wallet_app.model

data class Crypto(
    var id: String = "",
    var name: String = "",
    var symbol: String = "",
    var price: Double = 0.0,
    var changePercentage: Double = 0.0,
    var amount: Double = 0.0
)

