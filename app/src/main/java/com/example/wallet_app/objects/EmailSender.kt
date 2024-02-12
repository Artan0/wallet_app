package com.example.wallet_app.objects

import android.util.Log
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailSender {
    private const val USERNAME = "walletxbusiness@gmail.com"
    private const val PASSWORD = "nwdb cwoy ygug ptxd"

    fun sendMoneySentEmail(receiverEmail: String) {
        val subject = "Money Sent Successfully"
        val message = "You have successfully sent money."

        sendEmail(receiverEmail, subject, message)
    }

    fun sendMoneyReceivedEmail(receiverEmail: String, amount: Double, senderPayId: String) {
        val subject = "Money Received"
        val message = "You have received $$amount from $senderPayId."

        sendEmail(receiverEmail, subject, message)
    }

    private fun sendEmail(receiverEmail: String, subject: String, message: String) {
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.port"] = "587"

        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(USERNAME, PASSWORD)
            }
        })

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(USERNAME))
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail))
            mimeMessage.subject = subject
            mimeMessage.setText(message)

            Transport.send(mimeMessage)
            Log.d("EmailSender", "Email sent successfully.")

        } catch (e: MessagingException) {
            e.printStackTrace()
            Log.e("EmailSender", "Error sending email: ${e.message}")

        }
    }
}
