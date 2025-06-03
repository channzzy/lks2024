package com.example.lksmarket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lksmarket.adapter.ProductAdapter
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.response.ProductResponse
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment(), ProductAdapter.OnProductChangedListener {
    private var total : Double = 0.0
    private lateinit var totalTextView: TextView
    private var productAdapter: ProductAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root : View =  inflater.inflate(R.layout.fragment_menu, container, false)
        var recyleView = root.findViewById<RecyclerView>(R.id.rv_product)
        var sessionManager = SessionManager(root.context)
        var token = sessionManager.getToken()
        totalTextView = root.findViewById(R.id.tv_total_belanja)

        root.findViewById<Button>(R.id.btn_bayar).setOnClickListener {
            val selectedProducts = productAdapter?.getSelectedProducts() ?: emptyList()
            val invoiceJson = JSONObject()
            val jsonArray = JSONArray()

            var totalPayment = 0.0
            for (product in selectedProducts) {
                val item = JSONObject()
                item.put("name", product.title)
                item.put("unit_price", product.price)
                item.put("quantity", product.quantity)
                item.put("total", product.total)
                totalPayment += product.total
                jsonArray.put(item)
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = dateFormat.format(java.util.Date())


            invoiceJson.put("date", currentDate)
            invoiceJson.put("products", jsonArray)
            invoiceJson.put("total_payment", totalPayment)

            println(invoiceJson)
            println(selectedProducts.toString())
            Toast.makeText(requireContext(), "Selected: ${selectedProducts.size}", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), InvoiceActivity::class.java)
            intent.putExtra("invoice", invoiceJson.toString())
            startActivity(intent)
        }


//        root.findViewById<Button>(R.id.btn_bayar).setOnClickListener {
//            Toast.makeText(requireContext(), "Total bayar: Rp$total", Toast.LENGTH_SHORT).show()
//        }

        ApiConfig.getService().getProducts(token!!).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                println(response.code())
                println(response.body())
                if(response.code() == 200){
                    var response = response.body()
                    var dataInvoice = response?.products
                    productAdapter = ProductAdapter(dataInvoice, this@MenuFragment)
                    recyleView.apply {
                        layoutManager = LinearLayoutManager(root.context)
                        adapter = productAdapter;
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                print(t.localizedMessage)
            }

        })
        return root
    }
    override fun onProductAdded(price: Double) {
        total += price
        updateTotal()
    }

    override fun onProductRemoved(price: Double) {
        total -= price
        if (total < 0) total = 0.0
        updateTotal()
    }

    private fun updateTotal() {
        totalTextView.text = "Total: Rp$total"
    }

}
