package com.example.wallet_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.adapters.CryptoAdapter
import com.example.wallet_app.model.Crypto
import com.example.wallet_app.objects.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var logoutButton: Button
    private lateinit var usernameText: TextView
    private lateinit var auth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        cryptoAdapter = CryptoAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cryptoAdapter

        logoutButton = findViewById(R.id.logoutButton)
        usernameText = findViewById(R.id.usernameText)
        auth = FirebaseAuth.getInstance()

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
            finish() // Close the current activity
        }

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
                        logoUrl = "https://example.com/logos/${crypto.symbol.toLowerCase()}.png"
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

}
