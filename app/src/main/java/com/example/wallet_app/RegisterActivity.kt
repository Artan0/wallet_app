package com.example.wallet_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wallet_app.model.User
import com.example.wallet_app.model.Wallet
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.buttonRegister)
        backButton = findViewById(R.id.backButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            registerUser(email,password)
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun registerUser(email: String, password: String) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            return
        }

        val payId = generateUniquePayId() // Function to generate a unique hexadecimal payId
        val user = User(email, password, payId)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful, send verification email
                    Toast.makeText(this, "Registration successful. Please check your email for verification", Toast.LENGTH_SHORT).show()
                    sendEmailVerification()
                    // Create a user document for the user in Firestore
                    createUserDocument(user)
                    createWalletDocument()
                } else {
                    when {
                        task.exception?.message?.contains("email address") == true -> {
                            Toast.makeText(this, "Invalid email address format", Toast.LENGTH_SHORT).show()
                        }
                        task.exception?.message?.contains("password") == true -> {
                            Toast.makeText(this, "Weak password. Please use a stronger password", Toast.LENGTH_SHORT).show()
                        }
                        task.exception?.message?.contains("already in use") == true -> {
                            Toast.makeText(this, "Email address already in use", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    private fun generateUniquePayId(): String {
        // unique hexadecimal payId with length 6
        val random = (0 until 1679616).random()
        return String.format("%06x", random)
    }

    private fun createUserDocument(user: User) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore // check dependencies
            db.collection("users")
                .document(userId)
                .set(user)
                .addOnSuccessListener {
                    Log.d("Firestore", "User document created successfully")
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Error creating user document", it)
                }
        }
    }

    private fun createWalletDocument() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val wallet = Wallet(userId)
            val db = Firebase.firestore //check dependencies
            db.collection("wallets")
                .document(userId)
                .set(wallet)
                .addOnSuccessListener {
                    Log.d("Firestore", "Wallet document created successfully")
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Error creating wallet document", it)
                }
        }
    }


    private fun sendEmailVerification() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Email sent successfully
                    Toast.makeText(
                        this,
                        "Verification email sent. Please check your email.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to send verification email: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}