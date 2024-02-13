package com.example.wallet_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.R
import com.example.wallet_app.adapters.TransactionHistoryAdapter
import com.example.wallet_app.model.Transaction
import com.example.wallet_app.model.Wallet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TransactionHistoryFragment : Fragment() {

    private lateinit var transactionHistoryAdapter: TransactionHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history, container, false)

        recyclerView = view.findViewById(R.id.transactionHistoryRecyclerView)
        transactionHistoryAdapter = TransactionHistoryAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = transactionHistoryAdapter

        fetchTransactionHistory()

        return view
    }

    private fun fetchTransactionHistory() {
        userId?.let { uid ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val walletDoc = firestore
                        .collection("wallets")
                        .document(uid)
                        .get()
                        .await()

                    if (walletDoc.exists()) {
                        val walletData = walletDoc.toObject(Wallet::class.java)
                        val transactions = walletData?.transactions.orEmpty()
                        updateAdapterData(transactions)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun updateAdapterData(transactions: List<Transaction>) {
        withContext(Dispatchers.Main) {
            transactionHistoryAdapter.updateData(transactions)
        }
    }
}
