package com.example.wallet_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.firestore.FirebaseFirestore

const val channelId = "notification_channel"
const val channelName = "com.example.wallet_app"
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification !=null){
         generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)

            saveNotificationToFirestore(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
        }

    }
    private fun saveNotificationToFirestore(title: String, message: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val notification = hashMapOf(
                "title" to title,
                "content" to message,
                "timestamp" to FieldValue.serverTimestamp()
            )

            FirebaseFirestore.getInstance().collection("notifications")
                .document(userId)
                .collection("userNotifications")
                .add(notification)
                .addOnSuccessListener { documentReference ->
                    Log.d("Firestore", "Notification added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error adding notification", e)
                }
        }
    }

    fun getRemoteView(title: String, message: String):RemoteViews{
        val remoteView = RemoteViews("com.example.wallet_app", R.layout.notification)
        remoteView.setTextViewText(R.id.titleNotification, title)
        remoteView.setTextViewText(R.id.messageNotification, message)
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.wallet_logo)

        return remoteView

    }
    fun generateNotification(title:String, message:String){

        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.wallet_logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }

}