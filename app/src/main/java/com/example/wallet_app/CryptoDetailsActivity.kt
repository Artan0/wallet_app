package com.example.wallet_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wallet_app.model.CryptoDetails
import com.example.wallet_app.model.DateAxisFormatter
import com.example.wallet_app.model.HistoricalMarketDataResponse
import com.example.wallet_app.objects.RetrofitClient
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CryptoDetailsActivity : AppCompatActivity() {

    private lateinit var cryptoNameTextView: TextView
    private lateinit var linechart: LineChart
    private lateinit var buyButton: MaterialButton
    private lateinit var sellButton: MaterialButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto_details)

        cryptoNameTextView = findViewById(R.id.cryptoNameTextView)
        linechart = findViewById(R.id.lineChart)
        buyButton = findViewById(R.id.buyButton)
        sellButton = findViewById(R.id.sellButton)

        val cryptoId = intent.getStringExtra("crypto_id")

        if (cryptoId != null) {
            fetchCryptoDetails(cryptoId)
            fetchHistoricalMarketData(cryptoId)
        }
    }

    private fun fetchCryptoDetails(cryptoId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cryptoDetails = RetrofitClient.cryptoApi.getCryptoById(cryptoId)

                withContext(Dispatchers.Main) {
                    updateCryptoDetails(cryptoDetails)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
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
