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

class TransactionHistoryFragment : Fragment() {

    private lateinit var transactionHistoryAdapter: TransactionHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_history, container, false)

        recyclerView = view.findViewById(R.id.transactionHistoryRecyclerView)
        transactionHistoryAdapter = TransactionHistoryAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = transactionHistoryAdapter

        // Fetch transaction history data and update the adapter

        return view
    }
}
