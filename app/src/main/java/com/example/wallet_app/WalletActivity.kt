package com.example.wallet_app

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginStart
import androidx.viewpager.widget.ViewPager
import com.example.wallet_app.adapters.WalletPagerAdapter
import com.example.wallet_app.fragments.CryptoHoldingsFragment
import com.example.wallet_app.fragments.TransactionHistoryFragment
import com.example.wallet_app.model.Card
import com.example.wallet_app.model.Wallet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class WalletActivity : AppCompatActivity() {

    private lateinit var balanceTextView: TextView
    private lateinit var sendMoneyButton: Button
    private lateinit var receiveMoneyButton: Button
    private lateinit var allTransactionsButton: Button
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var addCardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        balanceTextView = findViewById(R.id.balanceTextView)
        sendMoneyButton = findViewById(R.id.sendMoneyButton)
        receiveMoneyButton = findViewById(R.id.receiveMoneyButton)
        allTransactionsButton = findViewById(R.id.allTransactionsButton)
        addCardButton = findViewById(R.id.addCardButton)

        addCardButton.setOnClickListener { showAddCardDialog() }
        sendMoneyButton.setOnClickListener { onSendMoneyClicked() }
        receiveMoneyButton.setOnClickListener { onReceiveMoneyClicked() }
        allTransactionsButton.setOnClickListener { onAllTransactionsClicked() }

        retrieveUserWallet()

        setupViewPager()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.wallet -> true
                R.id.home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = R.id.wallet
    }

    private fun updateBalance(balance: Double) {
        balanceTextView.text = String.format("$%.2f", balance)
    }

    private fun onSendMoneyClicked() {
        val intent = Intent(this, TransactionActivity::class.java)
        startActivity(intent)
    }

    private fun onReceiveMoneyClicked() {
        val intent = Intent(this, TransactionActivity::class.java)
        startActivity(intent)
    }

    private fun onAllTransactionsClicked() {
        // Handle All Transactions button click
    }

    private fun retrieveUserWallet() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val walletRef = FirebaseFirestore.getInstance().collection("wallets").document(userId)

            walletRef.get()
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

    private fun setupViewPager() {
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabs)

        val adapter = WalletPagerAdapter(supportFragmentManager)
        adapter.addFragment(CryptoHoldingsFragment(), "Crypto Holdings")
        adapter.addFragment(TransactionHistoryFragment(), "Transaction History")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun onAddCardClicked(view: View) {
        showAddCardDialog()
    }

    fun onSubmitCardClicked(dialog: Dialog) {
        val cardNumberEditText: EditText = dialog.findViewById(R.id.cardNumberEditText)
        val cardHolderNameEditText: EditText = dialog.findViewById(R.id.cardHolderNameEditText)
        val expirationDateEditText: EditText = dialog.findViewById(R.id.expirationDateEditText)
        val cvvEditText: EditText = dialog.findViewById(R.id.cvvEditText)

        val cardNumber = cardNumberEditText.text.toString().replace(" ", "") // Remove spaces
        val cardHolderName = cardHolderNameEditText.text.toString()
        val expirationDate = expirationDateEditText.text.toString()

        // Validate input fields before proceeding
        if (!isValidCardNumber(cardNumber) || !isValidExpirationDate(expirationDate)) {
            showToast("Invalid card information. Please check and try again.")
            return
        }

        val cvv = cvvEditText.text.toString()

        // Check the number of existing cards
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val walletRef = FirebaseFirestore.getInstance().collection("wallets").document(userId)

            walletRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        wallet?.cards?.let { cards ->
                            if (cards.size < 3) {
                                // Create a new Card object with the entered information
                                val newCard = Card(cardNumber, cardHolderName, expirationDate, cvv)

                                // Update the user's wallet with the new card
                                walletRef.update("cards", FieldValue.arrayUnion(newCard))
                                    .addOnSuccessListener {
                                        Log.d("Firestore", "Card added successfully")
                                        // Update UI or perform any additional actions
                                        showToast("Card added successfully")
                                        dialog.dismiss()
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e("Firestore", "Error updating wallet document", exception)
                                        showToast("Failed to add card. Please try again.")
                                        // Handle failure scenario
                                    }
                            } else {
                                showToast("Maximum 3 cards allowed.")
                            }
                        }
                    }
                }
        }
    }

    private fun isValidCardNumber(cardNumber: String): Boolean {
        val regex = Regex("^\\d{4} ?\\d{4} ?\\d{4} ?\\d{4}\$")
        return regex.matches(cardNumber)
    }

    private fun isValidExpirationDate(expirationDate: String): Boolean {
        val regex = Regex("^\\d{2}/\\d{2}\$")
        return regex.matches(expirationDate)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // ...

    private fun showAddCardDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_card)

        val submitCardButton: Button = dialog.findViewById(R.id.submitCardButton)
        val cardsContainer: LinearLayout = dialog.findViewById(R.id.cardsContainer)

        submitCardButton.setOnClickListener {
            onSubmitCardClicked(dialog)
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val walletRef = FirebaseFirestore.getInstance().collection("wallets").document(userId)

            walletRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        wallet?.cards?.let { cards ->
                            for (card in cards) {
                                val cardLayout = LinearLayout(this)
                                cardLayout.orientation = LinearLayout.HORIZONTAL

                                val cardTextView = TextView(this)
                                val maskedCardNumber = maskCardNumber(card.cardNumber)
                                cardTextView.text = "Card: $maskedCardNumber"
                                cardTextView.textSize = 20f
                                cardTextView.setTextColor(ContextCompat.getColor(this, R.color.wallet_text_color))
                                cardTextView.setPadding(10, 10, 10, 10)

                                val deleteButton = Button(this)
                                deleteButton.text = "Delete"
                                deleteButton.setTextColor(ContextCompat.getColor(this,R.color.wallet_text_color))
                                deleteButton.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
                                deleteButton.setOnClickListener {
                                    onDeleteCardClicked(card, walletRef, dialog)
                                }

                                cardLayout.addView(cardTextView)
                                cardLayout.addView(deleteButton)

                                cardsContainer.addView(cardLayout)
                            }
                        }
                    }
                }
        }

        dialog.show()
    }

    private fun onDeleteCardClicked(card: Card, walletRef: DocumentReference, dialog: Dialog) {
        walletRef.update("cards", FieldValue.arrayRemove(card))
            .addOnSuccessListener {
                showToast("Card deleted successfully")
                dialog.dismiss()
                showAddCardDialog()
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error deleting card", exception)
                showToast("Failed to delete card. Please try again.")
            }
    }

    private fun maskCardNumber(cardNumber: String): String {
        val visibleDigits = 2
        val maskedDigits = cardNumber.length - visibleDigits
        return "*".repeat(maskedDigits) + cardNumber.substring(maskedDigits)
    }


}
