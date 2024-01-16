package com.example.wallet_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.R
import com.example.wallet_app.model.Crypto

class CryptoHoldingsAdapter(private var cryptoHoldings: List<Crypto>) : RecyclerView.Adapter<CryptoHoldingsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    fun updateData(newCryptoHoldings: List<Crypto>) {
        cryptoHoldings = newCryptoHoldings
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = cryptoHoldings[position]
        holder.nameTextView.text = crypto.name
        holder.symbolTextView.text = crypto.symbol
        holder.priceTextView.text = "$${crypto.price}"
    }

    override fun getItemCount(): Int {
        return cryptoHoldings.size
    }
}
