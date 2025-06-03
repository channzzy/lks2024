package com.example.lksmarket

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lksmarket.R
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class InvoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        val tvDate = findViewById<TextView>(R.id.tvDate)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tableProducts = findViewById<TableLayout>(R.id.tableProducts)

        val invoiceString = intent.getStringExtra("invoice")
        val invoiceJson = JSONObject(invoiceString)

        // Tampilkan tanggal
        val rawDate = invoiceJson.getString("date")
        val formattedDate = formatDate(rawDate)
        tvDate.text = "Tanggal: $formattedDate"

        // Tampilkan produk
        val productsArray: JSONArray = invoiceJson.getJSONArray("products")
        for (i in 0 until productsArray.length()) {
            val item = productsArray.getJSONObject(i)
            val name = item.getString("name")
            val unitPrice = item.getDouble("unit_price")
            val quantity = item.getInt("quantity")
            val total = item.getDouble("total")
            println(total)

            val row = TableRow(this)
            row.addView(createCell(name,15))
            row.addView(createCell("Rp. ${formatCurrency(unitPrice)}"))
            row.addView(createCell(quantity.toString()))
            row.addView(createCell("Rp. ${formatCurrency(total)}"))
            tableProducts.addView(row)
            val totalPayment = invoiceJson.getDouble("total_payment")
            tvTotal.text = "Total Bayar: Rp. ${formatCurrency(totalPayment)}"
        }
    }

    private fun formatDate(date: String): String {
        val sdfInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val sdfOutput = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
        val parsedDate = sdfInput.parse(date)
        return parsedDate?.let { sdfOutput.format(it) } ?: date
    }
    fun createCell(text: String, maxLength: Int = 20): TextView {
        val trimmedText = if (text.length > maxLength) text.substring(0, maxLength) + "..." else text
        return TextView(this).apply {
            setPadding(8, 8, 8, 8)
            setText(trimmedText)
        }
    }


    fun formatCurrency(amount: Double): String {
        return String.format("%,.0f", amount * 1000)
    }
}
