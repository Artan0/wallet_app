package com.example.wallet_app.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet_app.R
import com.example.wallet_app.model.Notification

class NotificationAdapter(private var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notificationTitle)
        val content: TextView = itemView.findViewById(R.id.notificationContent)
        val timestamp: TextView = itemView.findViewById(R.id.notificationTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]

        holder.title.text = notification.title
        holder.content.text = notification.content
        holder.timestamp.text = notification.timestamp.toString() // Format as needed
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun updateData(newNotifications: List<Notification>) {
        notifications = newNotifications
        notifyDataSetChanged()
    }
}
