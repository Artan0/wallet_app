package com.example.wallet_app
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wallet_app.model.Crypto
import com.example.wallet_app.model.CryptoApiResponse
import com.example.wallet_app.model.CryptoDetails
import com.example.wallet_app.model.DateAxisFormatter
import com.example.wallet_app.model.HistoricalMarketDataResponse
import com.example.wallet_app.model.Wallet
import com.example.wallet_app.objects.RetrofitClient
import com.example.wallet_app.service.CryptoApi
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.android.material.snackbar.Snackbar

class CryptoDetailsActivity : AppCompatActivity() {

    private lateinit var cryptoNameTextView: TextView
    private lateinit var linechart: LineChart
    private lateinit var buyButton: MaterialButton
    private lateinit var sellButton: MaterialButton
    private lateinit var amountText: EditText
    private lateinit var priceText: EditText
    private lateinit var userAmountTextView: TextView
    private lateinit var userBalanceTextView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_details)

        cryptoNameTextView = findViewById(R.id.cryptoNameTextView)
        linechart = findViewById(R.id.lineChart)
        buyButton = findViewById(R.id.buyButton)
        sellButton = findViewById(R.id.sellButton)
        amountText = findViewById(R.id.amountText)
        priceText = findViewById(R.id.priceText)
        userAmountTextView = findViewById(R.id.userAmount)
        userBalanceTextView = findViewById(R.id.userBalance)

        val cryptoId = intent.getStringExtra("crypto_id")
        buyButton.setOnClickListener {
            if (cryptoId != null) {
                buyCrypto(cryptoId)
            }
        }
        sellButton.setOnClickListener {
            if (cryptoId != null) {
                sellCrypto(cryptoId)
            }
        }
        if (cryptoId != null) {
            retrieveUserWallet()
            fetchCryptoDetails(cryptoId)
            fetchHistoricalMarketData(cryptoId)
        }

    }


    private fun buyCrypto(cryptoId: String) {
        // Retrieve the amount from the EditText field
        val amount = amountText.text.toString().toDoubleOrNull() ?: return
        val price = priceText.text.toString().toDoubleOrNull() ?: return
        // Generate a CryptoApiResponse object
        val cryptoApiResponse = Crypto(
            id = cryptoId,
            name = "", // You may need to fetch this information from the API separately
            symbol = "", // You may need to fetch this information from the API separately
            price = price, // You may need to fetch this information from the API separately
            changePercentage = 0.0, // You may need to fetch this information from the API separately
            amount = amount
        )

        // Retrieve the user's wallet
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            val walletRef = db.collection("wallets").document(userId)

            // Update the user's balance and add the crypto to the cryptoHoldings list
            db.runTransaction { transaction ->
                val wallet = transaction.get(walletRef).toObject(Wallet::class.java)

                if (wallet != null) {
                    // Check if the user has enough balance
                    // (You may need to fetch the crypto details from the API to calculate the total cost)
                    val totalCost = amount * cryptoApiResponse.price
                    if (wallet.balance >= totalCost) {
                        // Deduct the cost from the balance
                        wallet.balance -= totalCost

                        // Add the crypto to the cryptoHoldings list
                        if (wallet.cryptoHoldings == null) {
                            wallet.cryptoHoldings = mutableListOf()
                        }
                        wallet.cryptoHoldings?.add(cryptoApiResponse)

                        // Update the wallet in Firestore
                        transaction.set(walletRef, wallet)

                        // Update the UI with the new balance
                        updateBalance(wallet)
                        showSnackbar("Buy successful!")

                    } else {
                        // User doesn't have enough balance
                        // Handle the case accordingly (e.g., show an error message)
                        showSnackbar("Insufficient balance!")
                    }
                }

                null
            }
                .addOnSuccessListener {
                    Log.d("Firestore", "Transaction success!")
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Transaction failure", exception)
                    // Handle the transaction failure accordingly (e.g., show an error message)
                    showSnackbar("Buy failed. Please try again.")
                }
        }
    }

    private fun sellCrypto(cryptoId: String) {
        // Retrieve the amount from the EditText field
        val amount = amountText.text.toString().toDoubleOrNull() ?: return
        val price = priceText.text.toString().toDoubleOrNull() ?: return
        // Generate a CryptoApiResponse object
        val cryptoApiResponse = Crypto(
            id = cryptoId,
            name = "", // You may need to fetch this information from the API separately
            symbol = "", // You may need to fetch this information from the API separately
            price = price, // You may need to fetch this information from the API separately
            changePercentage = 0.0, // You may need to fetch this information from the API separately
            amount = amount
        )

        // Retrieve the user's wallet
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            val walletRef = db.collection("wallets").document(userId)

            // Update the user's balance and remove the sold crypto from the cryptoHoldings list
            db.runTransaction { transaction ->
                val wallet = transaction.get(walletRef).toObject(Wallet::class.java)

                if (wallet != null) {
                    // Check if the user has enough of the crypto to sell
                    val cryptoHolding = wallet.cryptoHoldings?.find { it.id == cryptoId }
                    if (cryptoHolding != null && cryptoHolding.amount >= amount) {
                        // Add the sale amount to the balance
                        val saleAmount = amount * cryptoApiResponse.price
                        wallet.balance += saleAmount

                        // Deduct the sold crypto from the cryptoHoldings list
                        cryptoHolding.amount -= amount

                        // If the crypto amount becomes zero, remove it from the list
                        if (cryptoHolding.amount == 0.0) {
                            wallet.cryptoHoldings?.remove(cryptoHolding)
                        }

                        // Update the wallet in Firestore
                        transaction.set(walletRef, wallet)

                        // Update the UI with the new balance
                        updateBalance(wallet)
                        showSnackbar("Sell successful!")

                    } else {
                        // User doesn't have enough of the crypto to sell
                        // Handle the case accordingly (e.g., show an error message)
                        showSnackbar("Insufficient crypto holdings for sale!")
                    }
                }

                null
            }
                .addOnSuccessListener {
                    Log.d("Firestore", "Transaction success!")
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Transaction failure", exception)
                    // Handle the transaction failure accordingly (e.g., show an error message)
                    showSnackbar("Sell failed. Please try again.")
                }
        }
    }

    private fun showSnackbar(message: String) {
            val view = findViewById<View>(android.R.id.content)
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
        }

    private fun fetchCryptoDetails(cryptoId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cryptoDetails = RetrofitClient.cryptoApi.getCryptoById(cryptoId)

                withContext(Dispatchers.Main) {
                    updateCryptoDetails(cryptoDetails)
                    priceText.setText(cryptoDetails.currentPrice.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun retrieveUserWallet() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            db.collection("wallets")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val wallet = document.toObject(Wallet::class.java)
                        // Update the UI with the user's balance
                        updateBalance(wallet)
                    } else {
                        Log.d("Firestore", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error getting wallet document", exception)
                }
        }
    }
    private fun updateBalance(wallet: Wallet?) {
        if (wallet != null) {
            // Assuming you want to display the balance with 2 decimal places
            val formattedBalance = String.format("%.2f", wallet.balance)

            // Set the formatted balance to the userBalance TextView
            userBalanceTextView.text = "Available$formattedBalance$"

            // Find the crypto holding for the selected crypto (Assuming you have a variable to store the selected cryptoId)
//            val selectedCryptoId = cryptoId // Replace with the actual selected cryptoId
            val selectedCryptoId  = intent.getStringExtra("crypto_id")
            val cryptoHolding = wallet.cryptoHoldings?.find { it.id == selectedCryptoId }

            if (cryptoHolding != null) {
                // Set the formatted crypto amount to the userAmount TextView
                val formattedCryptoAmount = String.format("%.2f", cryptoHolding.amount)
                userAmountTextView.text = "Crypto $formattedCryptoAmount"
            } else {
                // Crypto holding not found, set userAmount to default value or handle accordingly
                userAmountTextView.text = "Crypto: N/A"
            }
        } else {
            // Handle the case when wallet is null (e.g., user not logged in)
            userBalanceTextView.text = "Available N/A"
            userAmountTextView.text = "Crypto N/A"
        }
    }


    private fun fetchHistoricalMarketData(cryptoId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val historicalMarketData =
                    RetrofitClient.cryptoApi.getHistoricalMarketData(cryptoId, days = 1)

                withContext(Dispatchers.Main) {
                    updateHistoricalMarketData(historicalMarketData)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateCryptoDetails(cryptoDetails: CryptoDetails) {
        runOnUiThread {
            cryptoNameTextView.text = cryptoDetails.name
        }
    }

    private fun updateHistoricalMarketData(historicalMarketData: HistoricalMarketDataResponse) {
        val entries = ArrayList<Entry>()
        val yAxisLeft = linechart.axisLeft
        val yAxisRight = linechart.axisRight

        historicalMarketData.prices.forEach { priceData ->
            val timestamp = priceData[0].toLong()
            val price = priceData[1].toFloat()
            val entry = Entry(timestamp.toFloat(), price)
            entries.add(entry)
        }

        val dataSet = LineDataSet(entries, "Price Data")
        dataSet.color = resources.getColor(R.color.white)
        dataSet.setDrawFilled(true)
        dataSet.fillDrawable = resources.getDrawable(R.drawable.gradient_background)
        dataSet.fillAlpha = 200

        val legend = linechart.legend
        legend.textColor = resources.getColor(android.R.color.white)
        // Custom value formatter to change text color
        val valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toString()
            }

            override fun getPointLabel(entry: Entry?): String {
                return entry?.y.toString()
            }
        }

        dataSet.valueFormatter = valueFormatter

        val lineData = LineData(dataSet)
        linechart.data = lineData

        val description = Description()
        description.text = "Daily Price"
        linechart.description = description

        // Format x-axis timestamps
        val xAxis = linechart.xAxis
        xAxis.valueFormatter = DateAxisFormatter()

        xAxis.textColor = resources.getColor(android.R.color.white)

        yAxisLeft.textColor = resources.getColor(android.R.color.white)
        yAxisRight.setDrawLabels(false)
        xAxis.setDrawLabels(false)

        linechart.setScaleEnabled(true)
        linechart.setPinchZoom(true)
        dataSet.setDrawCircles(false)
        dataSet.setDrawCircleHole(false)
        linechart.setTouchEnabled(true)

        // Set up MarkerView
        val markerView = object : MarkerView(this, R.layout.custom_marker_view) {
            private val tvContent: TextView = findViewById(R.id.tvContent)

            override fun refreshContent(e: Entry?, highlight: Highlight?) {
                if (e != null) {
                    val price = e.y
                    val timestamp = e.x.toLong()
                    val formattedTimestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
                    tvContent.text = "Price: $price\nTime: $formattedTimestamp"
                }

                super.refreshContent(e, highlight)
            }
        }

        linechart.marker = markerView

        linechart.invalidate()
    }


}
