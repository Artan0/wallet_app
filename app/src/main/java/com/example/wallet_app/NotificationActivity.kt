package com.example.wallet_app

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallet_app.adapters.NotificationAdapter
import com.example.wallet_app.databinding.ActivityNotificationBinding
import com.example.wallet_app.model.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var backButton:ImageButton
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }
        notificationAdapter = NotificationAdapter(emptyList())
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notificationRecyclerView.adapter = notificationAdapter

        retrieveUserNotifications()
    }

//    private fun getDummyNotifications(): List<Notification> {
//        return listOf(
//            Notification("New Message", "You have a new message", System.currentTimeMillis()),
//            Notification("Payment Received", "You received a payment", System.currentTimeMillis()),
//            Notification("Friend Request", "You have a new friend request", System.currentTimeMillis())
//        )
//    }

    private fun retrieveUserNotifications() {
        if (userId != null) {
            firestore.collection("notifications")
                .document(userId)
                .collection("userNotifications")
                .orderBy("timestamp", Query.Direction.DESCENDING) // Order by timestamp
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val notifications = mutableListOf<Notification>()
                    for (document in querySnapshot.documents) {
                        val title = document.getString("title") ?: ""
                        val content = document.getString("content") ?: ""
                        val timestamp = document.getTimestamp("timestamp")?.toDate()?.time ?: 0L

                        notifications.add(Notification(title, content, timestamp))
                    }

                    notificationAdapter.updateData(notifications)
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error getting notifications", exception)
                }
        }
    }
}
