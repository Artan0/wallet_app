package com.example.wallet_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.adapters.CryptoAdapter
import com.example.wallet_app.model.Crypto
import com.example.wallet_app.model.Wallet
import com.example.wallet_app.objects.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity(){
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var logoutButton: Button
    private lateinit var usernameText: TextView
    private lateinit var totalBalance: TextView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var auth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Handle Home action
                    true
                }
                R.id.wallet -> {
                    // Handle Wallet action
                    startActivity(Intent(this, WalletActivity::class.java))
                    finish()
                    true
                }
                // Add more cases for other menu items
                else -> false
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        cryptoAdapter = CryptoAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cryptoAdapter

        logoutButton = findViewById(R.id.logoutButton)
        usernameText = findViewById(R.id.usernameText)
        totalBalance = findViewById(R.id.totalBalance)
        auth = FirebaseAuth.getInstance()

        // Set the username text
        usernameText.text = auth.currentUser?.email?.substringBefore('@') ?: "Unknown User"

        // Logout button click listener
        logoutButton.setOnClickListener {
            // Sign out the user
            auth.signOut()

            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Retrieve user's wallet information and update UI
        retrieveUserWallet()
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cryptoList = RetrofitClient.cryptoApi.getCryptoList()

                val cryptoListWithLogos = cryptoList.map { crypto ->
                    Crypto(
                        name = crypto.name,
                        symbol = crypto.symbol,
                        price = crypto.current_price,
                        logoUrl = "https://example.com/logos/${crypto.symbol.toLowerCase()}.png",
                        changePercentage = crypto.price_change_percentage_24h ?: 0.0

                    )
                }

                withContext(Dispatchers.Main) {
                    cryptoAdapter.updateData(cryptoListWithLogos)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
                        updateBalanceUI(wallet?.balance ?: 0.0)
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error getting wallet document", exception)
                }
        }
    }

    private fun updateBalanceUI(balance: Double) {
        // Update the balance text view
        totalBalance.text = String.format("Total Balance: $%.2f", balance)
    }

}