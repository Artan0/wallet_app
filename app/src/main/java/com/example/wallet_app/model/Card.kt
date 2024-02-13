package com.example.wallet_app.model

data class Card(
    var cardNumber: String = "",
    var cardHolderName: String = "",
    var expirationDate: String = "",
    var cvv: String = ""
) {
    constructor() : this("", "", "", "")
}
