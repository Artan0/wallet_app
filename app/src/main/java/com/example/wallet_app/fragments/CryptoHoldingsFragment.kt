package com.example.wallet_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.R
import com.example.wallet_app.adapters.CryptoHoldingsAdapter
import com.example.wallet_app.model.Crypto
import com.example.wallet_app.model.Wallet
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class CryptoHoldingsFragment : Fragment() {

    private lateinit var cryptoHoldingsAdapter: CryptoHoldingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crypto_holdings, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.cryptoHoldingsRecyclerView)
        cryptoHoldingsAdapter = CryptoHoldingsAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cryptoHoldingsAdapter

        // Fetch crypto holdings data
        fetchCryptoHoldingsData()

        return view
    }

    private fun fetchCryptoHoldingsData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            db.collection("wallets")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        val cryptoHoldings = wallet?.cryptoHoldings
                        if (cryptoHoldings != null) {
                            // Update the UI with the user's crypto holdings
                            updateCryptoHoldingsUI(cryptoHoldings)
                        } else {
                            Log.d("Firestore", "Crypto holdings is null")
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

    private fun updateCryptoHoldingsUI(cryptoHoldings: List<Crypto>) {
        cryptoHoldingsAdapter.updateData(cryptoHoldings)
    }

}
