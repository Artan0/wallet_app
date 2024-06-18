package com.example.wallet_app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
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
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
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
    private lateinit var qrCodeImageView: ImageView
    private lateinit var sendMoneyButton: ImageButton
    private lateinit var notificationButton : ImageButton
    private lateinit var payIdTextView : TextView
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
        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        sendMoneyButton = findViewById(R.id.sendMoneyButton)
        notificationButton = findViewById(R.id.notificationButton)
        payIdTextView = findViewById(R.id.payId)
        val generateQrCodeButton: Button = findViewById(R.id.generateQrCodeButton)
        val scanQrCodeButton: Button = findViewById(R.id.scanQrCodeButton)


        backButton.setOnClickListener {
            finish()
        }


        generateQrCodeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val amount = amountEditText.text.toString()
                val payId = getCurrentUserPayId()

                if (payId.isNotEmpty() && amount.isNotEmpty()) {
                    val data = "$payId|$amount"
                    generateQrCode(data)
                }
            }
        }

        scanQrCodeButton.setOnClickListener {
            initiateScan()
        }

        sendMoneyButton.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
        notificationButton.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        retrieveUserWallet()

    }
    fun onSendButtonClick(view: View) {
        val amount = amountEditText.text.toString().toDoubleOrNull()
        val payId = usernameEditText.text.toString()

        if (userId != null && amount != null && amount > 0 && payId.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userQuery = firestore.collection("users").whereEqualTo("payId", payId.trim().toLowerCase(Locale.ROOT))
                    val userDocs = userQuery.get().await()

                    if (!userDocs.isEmpty) {
                        val recipientUid = userDocs.documents.first().id
                        val recipientPayId = userDocs.documents.first().getString("payId") ?: ""

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




    private suspend fun performTransaction(amount: Double, recipientUid: String?, recipientPayId: String) {
        if (recipientUid != null) {
            val senderPayId = getCurrentUserPayId()
            val senderEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

            val transaction = Transaction(
                type = TransactionType.SEND,
                amount = amount,
                date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
                senderPayId = senderPayId,
                receiverPayId = recipientPayId
            )

            firestore.collection("wallets").document(userId!!)
                .update(
                    "balance", FieldValue.increment(-amount),
                    "transactions", FieldValue.arrayUnion(transaction)
                ).await()

            firestore.collection("wallets").document(recipientUid)
                .update(
                    "balance", FieldValue.increment(amount),
                    "transactions", FieldValue.arrayUnion(transaction.copy(type = TransactionType.RECEIVE))
                ).await()

            val recipientEmail = getUserEmailByPayId(recipientPayId)

            if (senderEmail.isNotEmpty() && recipientEmail.isNotEmpty()) {
                EmailSender.sendMoneySentEmail(senderEmail)
                EmailSender.sendMoneyReceivedEmail(recipientEmail, amount, senderPayId)
            } else {
                Log.e("Transaction", "Sender or recipient email not found.")
            }
        } else {
            Log.e("Transaction", "Recipient UID not found.")
        }
    }

    private suspend fun getCurrentUserPayId(): String {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val userDocument = firestore.collection("users").document(userId).get().await()
            return userDocument.getString("payId") ?: ""
        }
        return ""
    }

//    private fun retrieveUserWallet() {
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            val db = Firebase.firestore
//            db.collection("wallets")
//                .document(userId)
//                .get()
//                .addOnSuccessListener { document ->
//                    if (document != null && document.exists()) {
//                        val wallet = document.toObject(Wallet::class.java)
//                        updateBalance(wallet?.balance ?: 0.0)
//                        val payId = document.getString("payId") ?: ""
//                        payIdTextView.text = payId
//                    } else {
//                        Log.d("Firestore", "No such document")
//                    }
//                }
//                .addOnFailureListener { exception ->
//                    Log.e("Firestore", "Error getting wallet document", exception)
//                }
//        }
//    }
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
                    updateBalance(wallet?.balance ?: 0.0)
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting wallet document", exception)
            }

        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val payId = document.getString("payId") ?: ""
                    payIdTextView.text = "Pay ID: $payId"
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting user document", exception)
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

    private fun generateQrCode(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bitMatrix = MultiFormatWriter().encode(
                    data,
                    BarcodeFormat.QR_CODE,
                    400,
                    400
                )

                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                withContext(Dispatchers.Main) {
                    qrCodeImageView.setImageBitmap(bmp)
                }
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }

    private fun initiateScan() {
        IntentIntegrator(this)
            .setOrientationLocked(false)
            .setPrompt("Scan QR Code")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents != null) {
            val scannedData = result.contents.split("|")
            if (scannedData.size == 2) {
                val scannedPayId = scannedData[0]
                val scannedAmount = scannedData[1]

                usernameEditText.setText(scannedPayId)
                amountEditText.setText(scannedAmount)

                onSendButtonClick(sendButton)
            } else {
                //  invalid scanned data
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
