package com.example.wallet_app.model

import com.google.firebase.firestore.PropertyName

data class Wallet(
    @PropertyName("userId") var userId: String? = null,
    @PropertyName("balance") var balance: Double = 0.0,
    @PropertyName("cryptoHoldings") var cryptoHoldings: MutableList<Crypto>? = null,
    @PropertyName("transactions") var transactions: List<Transaction>? = null,
    @PropertyName("cards") var cards: List<Card>? = null
)