package com.example.wallet_app.model
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DateAxisFormatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val timestamp = value.toLong()
        val date = Date(timestamp)
        val format = SimpleDateFormat("MM-dd", Locale.getDefault())
        return format.format(date)
    }
}
