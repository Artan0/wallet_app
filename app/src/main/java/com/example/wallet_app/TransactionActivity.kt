package com.example.wallet_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wallet_app.model.Transaction
import com.example.wallet_app.model.TransactionType
import com.example.wallet_app.model.Wallet
import com.example.wallet_app.objects.EmailSender
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class TransactionActivity : AppCompatActivity() {
    private lateinit var amountEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var successTextView: TextView
    private lateinit var balanceText: TextView
    private lateinit var backButton: ImageButton

    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val firestore = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        amountEditText = findViewById(R.id.amountEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        sendButton = findViewById(R.id.sendButton)
        successTextView = findViewById(R.id.successTextView)
        balanceText = findViewById(R.id.balanceTextView)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }
        retrieveUserWallet()

    }

    // Inside the onSendButtonClick function
    // Inside the onSendButtonClick function
    fun onSendButtonClick(view: View) {
        val amount = amountEditText.text.toString().toDoubleOrNull()
        val payId = usernameEditText.text.toString()

        if (userId != null && amount != null && amount > 0 && payId.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Check if the user with the provided payId exists in Firestore
                    val userQuery = firestore.collection("users").whereEqualTo("payId", payId.trim().toLowerCase(Locale.ROOT))
                    val userDocs = userQuery.get().await()

                    if (!userDocs.isEmpty) {
                        // Retrieve the recipient's UID and payId
                        val recipientUid = userDocs.documents.first().id
                        val recipientPayId = userDocs.documents.first().getString("payId") ?: ""

                        // Perform the transaction
                        performTransaction(amount, recipientUid, recipientPayId)

                        withContext(Dispatchers.Main) {
                            successTextView.text = "Money sent successfully!"
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            successTextView.text = "User with the provided payId does not exist."
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        successTextView.text = "An error occurred. Please try again later."
                    }
                }
            }
        } else {
            successTextView.text = "Please enter a valid amount and payId."
        }
    }



// ... (existing code)

    // Inside the performTransaction function
    private suspend fun performTransaction(amount: Double, recipientUid: String?, recipientPayId: String) {
        if (recipientUid != null) {
            // Create a transaction object
            val senderPayId = getCurrentUserPayId()
            val senderEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

            val transaction = Transaction(
                type = TransactionType.SEND,
                amount = amount,
                date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
                senderPayId = senderPayId,
                receiverPayId = recipientPayId
            )

            // Update sender's wallet
            firestore.collection("wallets").document(userId!!)
                .update(
                    "balance", FieldValue.increment(-amount),
                    "transactions", FieldValue.arrayUnion(transaction)
                ).await()

            // Update recipient's wallet
            firestore.collection("wallets").document(recipientUid)
                .update(
                    "balance", FieldValue.increment(amount),
                    "transactions", FieldValue.arrayUnion(transaction.copy(type = TransactionType.RECEIVE))
                ).await()

            // Get recipient's email from the users collection using payId
            val recipientEmail = getUserEmailByPayId(recipientPayId)

            if (senderEmail.isNotEmpty() && recipientEmail.isNotEmpty()) {
                EmailSender.sendMoneySentEmail(senderEmail)
                EmailSender.sendMoneyReceivedEmail(recipientEmail, amount, senderPayId)
            } else {
                Log.e("Transaction", "Sender or recipient email not found.")
            }
        } else {
            // Handle the case when the recipient's UID is not found
            // You may want to show an error message or take appropriate action
            Log.e("Transaction", "Recipient UID not found.")
        }
    }

    // Add this function to get the current user's payId
    private suspend fun getCurrentUserPayId(): String {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val userDocument = firestore.collection("users").document(userId).get().await()
            return userDocument.getString("payId") ?: ""
        }
        return ""
    }

    private fun retrieveUserWallet() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            db.collection("wallets")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        // Update the UI with the user's balance
                        updateBalance(wallet?.balance ?: 0.0)
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error getting wallet document", exception)
                }
        }
    }
    private fun updateBalance(balance: Double) {
        balanceText.text = String.format("Available Balance $%.2f", balance)
    }

    private suspend fun getUserEmailByPayId(payId: String): String {
        val userQuery = firestore.collection("users").whereEqualTo("payId", payId.trim().toLowerCase(Locale.ROOT))
        val userDocs = userQuery.get().await()

        return if (!userDocs.isEmpty) {
            userDocs.documents.first().getString("email") ?: ""
        } else {
            ""
        }
    }
}
