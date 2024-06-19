package com.example.wallet_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.EditText
import android.widget.Button
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.wallet_app.model.Card
import com.example.wallet_app.model.Wallet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue

class DepositActivity : AppCompatActivity() {

    private lateinit var cardSpinner: Spinner
    private lateinit var amountEditText: EditText
    private lateinit var submitDepositButton: Button
    private lateinit var sendMoneyButton: ImageButton
    private lateinit var notificationButton : ImageButton
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)

        cardSpinner = findViewById(R.id.cardSpinner)
        amountEditText = findViewById(R.id.amountEditText)
        submitDepositButton = findViewById(R.id.submitDepositButton)
        sendMoneyButton = findViewById(R.id.sendMoneyButton)
        notificationButton = findViewById(R.id.notificationButton)
        backButton = findViewById(R.id.backButton)

        retrieveUserCards()

        submitDepositButton.setOnClickListener { onSubmitDepositClicked() }

        sendMoneyButton.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
        notificationButton.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun retrieveUserCards() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val walletRef = FirebaseFirestore.getInstance().collection("wallets").document(userId)

            walletRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        wallet?.cards?.let { cards ->
                            val cardAdapter = object : ArrayAdapter<Card>(this, android.R.layout.simple_spinner_item, cards) {
                                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                    val view = super.getView(position, convertView, parent)
                                    val card = getItem(position)
                                    val maskedCardNumber = maskCardNumber(card?.cardNumber ?: "")
                                    (view as TextView).text = "Card: $maskedCardNumber"
                                    view.setTextColor(ContextCompat.getColor(context, R.color.wallet_text_color))
                                    return view
                                }

                                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                                    val view = super.getDropDownView(position, convertView, parent)
                                    val card = getItem(position)
                                    val maskedCardNumber = maskCardNumber(card?.cardNumber ?: "")
                                    (view as TextView).text = "Card: $maskedCardNumber"
                                    view.setTextColor(ContextCompat.getColor(context, R.color.wallet_text_color))
                                    return view
                                }
                            }

                            cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            cardSpinner.adapter = cardAdapter
                        }
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error getting wallet document", exception)
                }
        }
    }


    private fun onSubmitDepositClicked() {
        val selectedCard: Card = cardSpinner.selectedItem as Card
        val enteredAmount: Double = amountEditText.text.toString().toDoubleOrNull() ?: 0.0

        try {
            validateDepositInput(enteredAmount)
            updateBalance(selectedCard, enteredAmount)
        } catch (e: IllegalArgumentException) {
            showToast(e.message ?: "Invalid input")
        }
    }

    private fun validateDepositInput(amount: Double) {
        if (amount <= 0) {
            throw IllegalArgumentException("Please enter a valid amount greater than 0.")
        }
        if (amount < 15) {
            throw IllegalArgumentException("The minimum deposit amount is $15.")
        }
    }

    private fun updateBalance(selectedCard: Card, amount: Double) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val walletRef = FirebaseFirestore.getInstance().collection("wallets").document(userId)

            // Mask the card number before displaying
            val maskedCardNumber = maskCardNumber(selectedCard.cardNumber)

            walletRef.update(
                "balance", FieldValue.increment(amount),
                "lastTransaction", "Deposit $amount to Card ending in $maskedCardNumber"
            )
                .addOnSuccessListener {
                    showToast("Deposit successful. Added balance: $amount")
                    clearInputFields()
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error updating wallet document", exception)
                    showToast("Failed to update balance. Please try again.")
                }
        }
    }
    private fun maskCardNumber(cardNumber: String): String {
        val visibleDigits = 4
        val maskedDigits = cardNumber.length - visibleDigits
        return "*".repeat(maskedDigits) + cardNumber.substring(maskedDigits)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun clearInputFields() {
        amountEditText.text.clear()
    }
}
