package com.example.wallet_app

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.wallet_app.adapters.ViewPagerAdapter
import com.example.wallet_app.adapters.WalletPagerAdapter
import com.example.wallet_app.fragments.CryptoHoldingsFragment
import com.example.wallet_app.fragments.TransactionHistoryFragment
import com.example.wallet_app.model.Wallet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

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
                R.id.wallet -> {
                    // Handle Wallet action
                    true
                }
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
    private fun setupViewPager() {
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabs)

        val adapter = WalletPagerAdapter(supportFragmentManager)
        adapter.addFragment(CryptoHoldingsFragment(), "Crypto Holdings")
        adapter.addFragment(TransactionHistoryFragment(), "Transaction History")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
    private fun showAddCardDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_card)

        val submitCardButton: Button = dialog.findViewById(R.id.submitCardButton)
        submitCardButton.setOnClickListener { onSubmitCardClicked(dialog) }

        dialog.show()
    }

    private fun onSubmitCardClicked(dialog: Dialog) {

        dialog.dismiss()
    }

    fun onAddCardClicked(view: View) {
        showAddCardDialog()
    }

}
