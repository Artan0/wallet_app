package com.example.wallet_app
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
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
    private lateinit var backButton: ImageButton

    private var cryptoApiResponse: Crypto = Crypto()


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
        backButton = findViewById(R.id.backButton)

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

        backButton.setOnClickListener {
            finish()
        }

    }


    private fun buyCrypto(cryptoId: String) {
        val amount = amountText.text.toString().toDoubleOrNull() ?: return
        val price = priceText.text.toString().toDoubleOrNull() ?: return

        // Fetch additional information from the API
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cryptoDetails = RetrofitClient.cryptoApi.getCryptoById(cryptoId)

                Log.d("BuyCrypto", "CryptoDetails: $cryptoDetails")

                cryptoApiResponse.id = cryptoId
                cryptoApiResponse.name = cryptoDetails.name
                cryptoApiResponse.symbol = cryptoDetails.symbol
                cryptoApiResponse.price = price
                cryptoApiResponse.changePercentage = cryptoDetails.priceChangePercentage24h
                cryptoApiResponse.amount = amount

                // Retrieve the user's wallet
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                if (userId != null) {
                    val db = Firebase.firestore
                    val walletRef = db.collection("wallets").document(userId)

                    db.runTransaction { transaction ->
                        val wallet = transaction.get(walletRef).toObject(Wallet::class.java)

                        if (wallet != null) {
                            // Check if the user has enough balance
                            val totalCost = amount * cryptoApiResponse.price
                            if (wallet.balance >= totalCost) {
                                wallet.balance -= totalCost

                                if (wallet.cryptoHoldings == null) {
                                    wallet.cryptoHoldings = mutableListOf()
                                }
                                wallet.cryptoHoldings?.add(cryptoApiResponse)

                                transaction.set(walletRef, wallet)

                                runOnUiThread {
                                    updateBalance(wallet)
                                    showSnackbar("Buy successful!")
                                }
                            } else {
                                  runOnUiThread {
                                    showSnackbar("Insufficient balance!")
                                }
                            }
                        }

                        null
                    }
                        .addOnSuccessListener {
                            Log.d("Firestore", "Transaction success!")
                        }
                        .addOnFailureListener { exception ->
                            Log.e("Firestore", "Transaction failure", exception)
                            runOnUiThread {
                                showSnackbar("Buy failed. Please try again.")
                            }
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    showSnackbar("Error fetching crypto details. Please try again.")
                }
            }
        }
    }


    private fun sellCrypto(cryptoId: String) {
        val amount = amountText.text.toString().toDoubleOrNull() ?: return
        val price = priceText.text.toString().toDoubleOrNull() ?: return

        // Retrieve the user's wallet
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = Firebase.firestore
            val walletRef = db.collection("wallets").document(userId)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val cryptoDetails = RetrofitClient.cryptoApi.getCryptoById(cryptoId)

                    db.runTransaction { transaction ->
                        val wallet = transaction.get(walletRef).toObject(Wallet::class.java)

                        if (wallet != null) {
                            val cryptoHolding = wallet.cryptoHoldings?.find { it.id == cryptoId }
                            if (cryptoHolding != null && cryptoHolding.amount >= amount) {
                                val saleAmount = amount * cryptoDetails.currentPrice
                                wallet.balance += saleAmount

                                cryptoHolding.amount -= amount

                                if (cryptoHolding.amount == 0.0) {
                                    wallet.cryptoHoldings?.remove(cryptoHolding)
                                }

                                transaction.set(walletRef, wallet)

                                runOnUiThread {
                                    updateBalance(wallet)
                                    showSnackbar("Sell successful!")
                                }
                            } else {
                                runOnUiThread {
                                    showSnackbar("Insufficient crypto holdings for sale!")
                                }
                            }
                        }

                        null
                    }
                        .addOnSuccessListener {
                            Log.d("Firestore", "Transaction success!")
                        }
                        .addOnFailureListener { exception ->
                            Log.e("Firestore", "Transaction failure", exception)
                            showSnackbar("Sell failed. Please try again.")
                        }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
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
            val formattedBalance = String.format("%.2f", wallet.balance)

            userBalanceTextView.text = "Available $formattedBalance$"


            val selectedCryptoId  = intent.getStringExtra("crypto_id")
            val cryptoHolding = wallet.cryptoHoldings?.find { it.id == selectedCryptoId }

            if (cryptoHolding != null) {
                val formattedCryptoAmount = String.format("%.2f", cryptoHolding.amount)
                userAmountTextView.text = "Crypto $formattedCryptoAmount"
            } else {
                userAmountTextView.text = "Crypto: N/A"
            }
        } else {
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
