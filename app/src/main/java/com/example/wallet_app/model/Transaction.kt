package com.example.wallet_app.model

data class Transaction(
    val type: TransactionType,
    val amount: Double,
    val date: String
)
