package com.example.wallet_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallet_app.R
import com.example.wallet_app.model.Crypto
import com.example.wallet_app.model.CryptoApiResponse

class CryptoAdapter(private val onItemClick: (CryptoApiResponse) -> Unit) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {
    private var cryptoList: List<CryptoApiResponse> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val symbolTextView: TextView = itemView.findViewById(R.id.symbolTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val changeTextView: TextView = itemView.findViewById(R.id.changePercentageTextView)
//        val logoImageView: ImageView = itemView.findViewById(R.id.logoImageView)
    }

    fun updateData(newCryptoList: List<CryptoApiResponse>) {
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
        holder.priceTextView.text = "$${crypto.current_price}"
        holder.changeTextView.text = String.format("%.2f%%", crypto.price_change_percentage_24h)

        val textColorResId = when {
            crypto.price_change_percentage_24h > 0 -> R.color.green
            crypto.price_change_percentage_24h < 0 -> R.color.red
            else -> R.color.wallet_text_color
        }

        holder.changeTextView.setTextColor(ContextCompat.getColor(holder.changeTextView.context, textColorResId))
        holder.itemView.setOnClickListener {
            onItemClick.invoke(crypto) // Pass the crypto ID instead of symbol
        }
//        // Load logo image using Glide (make sure to add the Glide dependency to your app's build.gradle)
//        Glide.with(holder.logoImageView.context)
//            .load(getLogoUrl(crypto.symbol)) // Use the method to get the logo URL
//            .into(holder.logoImageView)
//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(crypto.id)
//        }
    }

    private fun getLogoUrl(symbol: String): String {
        return "https://example.com/logos/${symbol.toLowerCase()}.png"
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}
