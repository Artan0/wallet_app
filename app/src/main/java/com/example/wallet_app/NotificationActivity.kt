package com.example.wallet_app

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallet_app.adapters.NotificationAdapter
import com.example.wallet_app.databinding.ActivityNotificationBinding
import com.example.wallet_app.model.Notification

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var backButton:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }
        notificationAdapter = NotificationAdapter(getDummyNotifications())
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notificationRecyclerView.adapter = notificationAdapter
    }

    private fun getDummyNotifications(): List<Notification> {
        return listOf(
            Notification("New Message", "You have a new message", System.currentTimeMillis()),
            Notification("Payment Received", "You received a payment", System.currentTimeMillis()),
            Notification("Friend Request", "You have a new friend request", System.currentTimeMillis())
        )
    }
}
