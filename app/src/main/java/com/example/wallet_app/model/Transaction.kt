package com.example.wallet_app.model

import com.google.firebase.firestore.PropertyName

data class Transaction(
    @PropertyName("type") val type: TransactionType,
    @PropertyName("amount") val amount: Double,
    @PropertyName("date") val date: String,
    @PropertyName("senderPayId") val senderPayId: String,
    @PropertyName("receiverPayId") val receiverPayId: String
) {
    constructor() : this(TransactionType.SEND, 0.0, "", "", "")
}
