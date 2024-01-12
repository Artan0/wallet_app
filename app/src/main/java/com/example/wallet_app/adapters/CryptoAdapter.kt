package com.example.wallet_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallet_app.R
import com.example.wallet_app.model.Crypto

class CryptoAdapter(private var cryptoList: List<Crypto>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
//        val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
    }

    fun updateData(newCryptoList: List<Crypto>) {
        cryptoList = newCryptoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = cryptoList[position]
        holder.nameTextView.text = crypto.name
        holder.symbolTextView.text = crypto.symbol
        holder.priceTextView.text = "$${crypto.price}"

//        // Load logo image using Glide (make sure to add the Glide dependency to your app's build.gradle)
//        Glide.with(holder.logoImageView.context)
//            .load(getLogoUrl(crypto.symbol)) // Use the method to get the logo URL
//            .into(holder.logoImageView)
    }

    private fun getLogoUrl(symbol: String): String {
        // Assuming logo URLs follow a certain pattern
        return "https://example.com/logos/${symbol.toLowerCase()}.png"
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}
